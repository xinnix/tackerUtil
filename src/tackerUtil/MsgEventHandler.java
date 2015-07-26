package tackerUtil;

import network.NetworkAdapter;
import packet.DPacketParser;

public class MsgEventHandler {
	
	public static void login(String username,String password,NetworkAdapter na){
		int[] pktDataColumnType  = {0x00000001,0x00000001};
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
