package tackerUtil;

import model.CarGroup;
import network.NetworkAdapter;
import packet.ByteHexUtil;
import packet.DPacketParser;

public class MsgEventHandler {
	public static NetworkAdapter na;
	public static void config(NetworkAdapter nwa){
		na = nwa;
	}
	public static void sLogin(String username,String password){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_STRING,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {username.length()*2,password.length()*2};
		byte[] pktData = new byte[username.length()*2+password.length()*2];
		
		byte[] busername=username.getBytes();
		byte[] bpassword=password.getBytes();
		
		System.arraycopy(busername, 0, pktData, 0, busername.length);
		System.arraycopy(bpassword, 0, pktData, busername.length*2, bpassword.length);
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_LOGIN,1,2,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	public static int rLogin(DPacketParser dp){
		
		int userid =(int) dp.dataTable.table[0][1];
		return userid;
		
	}
	
	public static void sHeartBeat(){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_INTEGER};
		int[] pktDataColumnLength = {4};
		byte[] pktData = new byte[4];
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_HEARTBEAT,1,1,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	public static void sGetCarGroup(int userid,String date){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_INTEGER,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {4,date.length()*2};
		byte[] pktData = new byte[4+date.length()*2];
		
		byte[] buserid = ByteHexUtil.intToByte(userid);
		byte[] bdate = date.getBytes();
		
		System.arraycopy(buserid, 0, pktData, 0, buserid.length);
		System.arraycopy(bdate, 0, pktData, buserid.length, bdate.length*2);
		
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_GETUSERCARGROUP,1,2,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	public static void rGetCarGroup(DPacketParser dp){
		 
		CarGroup[] cg = new CarGroup[dp.dataTable.table.length];
		for (int ii=0;ii<cg.length;ii++){
			cg[ii] = new CarGroup((int)dp.dataTable.table[ii][0],
					(String)dp.dataTable.table[ii][1],
					(String)dp.dataTable.table[ii][2],
					(String)dp.dataTable.table[ii][3],
					(String)dp.dataTable.table[ii][4],
					(String)dp.dataTable.table[ii][5],
					(int)dp.dataTable.table[ii][6],
					(String)dp.dataTable.table[ii][7]);
		}
		
		
		for (int ii=0;ii<cg.length;ii++){
			System.out.print(""+cg[ii].vehGroupID+'#'+cg[ii].vehGroupName.trim()+'|'+cg[ii].fVehGroupID);
			System.out.println("");	
		}
		
		
		
	}

}
