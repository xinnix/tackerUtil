package network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

import packet.ByteHexUtil;
import packet.DPacketParser;

public class NetworkAdapter extends Thread {
	public static Socket socket;
	public static OutputStream outputStream;
	public static InputStream inputStream;
	public byte[] sendBuffer;
	public byte[] recieveBuffer = new byte[256];
	
	 public NetworkAdapter(String serverIP,int port){
		 super();
		 try{
			 socket = new Socket(InetAddress.getByName(serverIP),port);
			 outputStream = socket.getOutputStream();
			 inputStream = socket.getInputStream();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
	 }
	 public NetworkAdapter(byte[] packet){
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
				 System.out.println(len);
				 ByteHexUtil.printHexString(Arrays.copyOfRange(recieveBuffer,0,len));
				 DPacketParser dp = new DPacketParser(Arrays.copyOfRange(recieveBuffer,0,len));
				 System.out.println("");
				 System.out.println("登录成功？"+dp.dataTable.table[0][0]);
				 System.out.println("用户id："+dp.dataTable.table[0][1]);
				 System.out.println("GPRS地址："+dp.dataTable.table[0][2]);
				 System.out.println("GPRS端口："+dp.dataTable.table[0][3]);

			 }catch(Exception e ){
				e.printStackTrace(); 
			 }
			 	  
		 }
			
	 }
	

}
