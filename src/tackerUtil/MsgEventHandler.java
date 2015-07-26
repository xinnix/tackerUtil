package tackerUtil;

import network.NetworkAdapter;
import packet.DPacketParser;

public class MsgEventHandler {
	public static NetworkAdapter na;
	public static void config(NetworkAdapter nwa){
		na = nwa;
	}
	public static void login(String username,String password){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_STRING,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {username.length()*2,password.length()*2};
		byte[] pktData = new byte[username.length()*2+password.length()*2];
		
		byte[] userbyte=username.getBytes();
		byte[] passbyte=password.getBytes();
		
		System.arraycopy(userbyte, 0, pktData, 0, userbyte.length);
		System.arraycopy(passbyte, 0, pktData, userbyte.length*2, passbyte.length);
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_LOGIN,1,2,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	public static void rLogin(String username,String password){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_STRING,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {username.length()*2,password.length()*2};
		byte[] pktData = new byte[username.length()*2+password.length()*2];
		
		byte[] userbyte=username.getBytes();
		byte[] passbyte=password.getBytes();
		
		System.arraycopy(userbyte, 0, pktData, 0, userbyte.length);
		System.arraycopy(passbyte, 0, pktData, userbyte.length*2, passbyte.length);
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_LOGIN,1,2,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}

}
