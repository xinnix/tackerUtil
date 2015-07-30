package network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import model.CarState;
import packet.ByteHexUtil;
import packet.CPacketParser;
import packet.DPacketParser;
import tackerUtil.MsgEventHandler;

public class CNetworkAdapter extends Thread {
	
	public static Socket socket;
	public static OutputStream outputStream;
	public static InputStream inputStream;
	public byte[] sendBuffer;
	public byte[] recieveBuffer = new byte[4096];
	
	 public CNetworkAdapter(String serverIP,int port){
		 super();
		 try{
			 socket = new Socket(InetAddress.getByName(serverIP),port);
			 outputStream = socket.getOutputStream();
			 inputStream = socket.getInputStream();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 public CNetworkAdapter(byte[] packet){
		 super();
		 this.sendBuffer= packet;
	 }
	 
	 
	 public void sendPacket(byte[] packet){
		 try{
			 this.sendBuffer = packet;
			 outputStream.write(packet);
			 outputStream.flush();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 
	 public void run(){
		 while(true){
			 try{		  			 			 
				 int len = inputStream.read(recieveBuffer);
				// System.out.println(len);
				 
				 //
				 if(len>0){
					 System.out.println(ByteHexUtil.bytesToHexString(Arrays.copyOfRange(recieveBuffer,0,len)));
					 
					 CPacketParser cp = new CPacketParser(Arrays.copyOfRange(recieveBuffer,0,len));
					 switch (cp.pktSignal){
					 case CPacketParser.SIGNAL_RE_LOGIN:
						 int userid = MsgEventHandler.c_rlogin(cp);	
						 break;
					 case CPacketParser.SIGNAL_RE_LOCATE:
						 CarState cs =MsgEventHandler.c_rGetCarPosition(cp);
						 System.out.println(""+cp.pktFakeIP+cs.gprmc.latitude+cs.gprmc.EorW);
	
					 
					 }
				
				 
				 }
				 

			 }catch(Exception e ){
				e.printStackTrace(); 
			 }
			 	  
		 }
			
	 }
	


}
