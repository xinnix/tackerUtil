package tackerUtil;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import model.Car;
import model.CarGroup;
import model.CarState;
import model.Fail;
import model.Track;
import model.User;
import network.CNetworkAdapter;
import network.NetworkAdapter;
import packet.ByteHexUtil;
import packet.CPacketParser;
import packet.DPacketParser;
import packet.MsgGPRSParser;

public class MsgEventHandler {
	public static NetworkAdapter na;
	public static CNetworkAdapter cna;
	public static void config(NetworkAdapter nwa,CNetworkAdapter cwa){
		na = nwa;
		cna = cwa;
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
	
	
	public static Fail rFail(DPacketParser dp){
		
		Fail f = new Fail((int)dp.dataTable.table[0][0],(String)dp.dataTable.table[0][1]);
		
		System.out.print(""+f.signal+'#'+f.reason.trim());
		System.out.println("");	
	
		return f;
		
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
	
	public static CarGroup[] rGetCarGroup(DPacketParser dp){
		 
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
			System.out.print(""+cg[ii].vehGroupID+'#'+cg[ii].vehGroupName.trim()+'|'+cg[ii].updateTime);
			System.out.println("");	
		}
		
		return cg;
		
	}
	
	public static void sGetUserInfo(int userid){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_INTEGER};
		int[] pktDataColumnLength = {4};
		byte[] pktData = new byte[4];
		
		byte[] buserid = ByteHexUtil.intToByte(userid);
		
		
		System.arraycopy(buserid, 0, pktData, 0, buserid.length);
		
		
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_GETUSERINFO,1,pktDataColumnType.length,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	public static User rGetUserInfo(DPacketParser dp){
		
		User[] u = new User[dp.dataTable.table.length];
		for (int ii=0;ii<u.length;ii++){
			u[ii] = new User((int)dp.dataTable.table[ii][0],
					(String)dp.dataTable.table[ii][1],
					(String)dp.dataTable.table[ii][2],
					(int)dp.dataTable.table[ii][3],
					(String)dp.dataTable.table[ii][4],
					(String)dp.dataTable.table[ii][5],
					(String)dp.dataTable.table[ii][6],
					(String)dp.dataTable.table[ii][7],
					(String)dp.dataTable.table[ii][8],
					(String)dp.dataTable.table[ii][9],
					(int)dp.dataTable.table[ii][10],
					(String)dp.dataTable.table[ii][11]);
		}
		
		
		for (int ii=0;ii<u.length;ii++){
			System.out.print(""+u[ii].username+'#'+u[ii].password.trim()+'|'+u[ii].birthday);
			System.out.println("");	
		}
		
		return u[0];
		
	}
	
	
	public static void sGetCarInfo(int userid,String date){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_INTEGER,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {4,date.length()*2};
		byte[] pktData = new byte[4+date.length()*2];
		
		byte[] buserid = ByteHexUtil.intToByte(userid);
		byte[] bdate = date.getBytes();
		
		System.arraycopy(buserid, 0, pktData, 0, buserid.length);
		System.arraycopy(bdate, 0, pktData, buserid.length, bdate.length*2);
		
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_GETCARINFO,1,2,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
		
	}
	
	
	public static Car[] rGetCarInfo(DPacketParser dp){
		 
		Car[] cars = new Car[dp.dataTable.table.length];
		for (int ii=0;ii<cars.length;ii++){
			cars[ii] = new Car((String)dp.dataTable.table[ii][0],
					(String)dp.dataTable.table[ii][1],
					(String)dp.dataTable.table[ii][2]
					);
		}
		
		
		for (int ii=0;ii<cars.length;ii++){
			System.out.print(""+cars[ii].id.trim()+'#'+cars[ii].deviceId.trim()+"$"+cars[ii].ipAddress.trim());
			System.out.println("");	
		}
		
		return cars;
		
	}
	
	
	public static void sGetCarTrack(int carid,String sdate,String edate){
		int[] pktDataColumnType  = {DPacketParser.DATA_TYPE_INTEGER,DPacketParser.DATA_TYPE_STRING,DPacketParser.DATA_TYPE_STRING};
		int[] pktDataColumnLength = {4,sdate.length()*2,edate.length()*2};
		byte[] pktData = new byte[4+sdate.length()*2+edate.length()*2];
		
		byte[] bcarid =  ByteHexUtil.intToByte(carid);
		byte[] bsdate=sdate.getBytes();
		byte[] bedate=edate.getBytes();
		
		
		System.arraycopy(bcarid, 0, pktData, 0, bcarid.length);
		System.arraycopy(bsdate, 0, pktData, bcarid.length, bsdate.length);
		System.arraycopy(bedate, 0, pktData, bcarid.length+bsdate.length*2, bedate.length);
		DPacketParser dp = new DPacketParser(DPacketParser.SIGNAL_GETCARTRACK,1,3,pktDataColumnType, pktDataColumnLength, pktData);	
		na.sendPacket(dp.pktBuffer);
	}
	public static Track[] rGetCarTrack(DPacketParser dp){
		
		System.out.println("got track info");
		Track[] t = new Track[dp.dataTable.table.length];

			for (int ii=0;ii<t.length;ii++){
				t[ii] = new Track((int)dp.dataTable.table[ii][0],
						(double)dp.dataTable.table[ii][1],
						(double)dp.dataTable.table[ii][2],
						(int)dp.dataTable.table[ii][3],
						(int)dp.dataTable.table[ii][4],
						(boolean)dp.dataTable.table[ii][5],
						(String)dp.dataTable.table[ii][6],
						(String)dp.dataTable.table[ii][7],
						(boolean)dp.dataTable.table[ii][8],
						(String)dp.dataTable.table[ii][9]
						);
			}
			
			
			for (int ii=0;ii<t.length;ii++){
				System.out.print(""+t[ii].carId+'#'+t[ii].latitude+'|'+t[ii].longitude+"$"+t[ii].sdate);
				System.out.println("");	
			}
		
		return t;
	}
	
	
	
	/*
	 * 以下是中心报文相关控制函数
	 */
	
	
	public static void c_slogin(String username,String password){
		byte signal = (byte)0xa3;
		int fakeip = 0;
		byte[] busername = username.getBytes();
		byte[] bpassword = password.getBytes();
		byte[] data = new byte[40];
		for (int ii=0;ii<data.length;ii++){
			data[ii]=(byte)0x20;
		}
		
		System.arraycopy(busername, 0, data, 0, busername.length);
		System.arraycopy(bpassword, 0, data, 20, bpassword.length);
		
		CPacketParser cp = new CPacketParser(signal, data);
		System.out.println(ByteHexUtil.bytesToHexString(cp.pktBuffer));
		cna.sendPacket(cp.pktBuffer);
			
	}
	
	public static int c_rlogin(CPacketParser cp){
		
		byte sig = ByteHexUtil.intToByte(cp.pktFakeIP)[0];
		
		if (sig==(byte)0x01){
			System.out.println("login complete");
			c_sGetCarPosition();
			return 0;
		}else{
			return 1;
		}
	
	}
	
	
	public static void c_sGetCarPosition(){
		String hexPacket  = "2929a400000000";
		String end = "0d";
		byte[] packet = ByteHexUtil.hexStringToBytes(hexPacket);
		byte check = CPacketParser.packetCheck(packet);
		ByteArrayOutputStream  bis = new ByteArrayOutputStream();
		try{
			bis.write(packet);
			bis.write(check);
			bis.write(ByteHexUtil.hexStringToBytes(end));
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(ByteHexUtil.bytesToHexString(bis.toByteArray()));
		
		cna.sendPacket(bis.toByteArray());
		
			
	}
	
	
	public static CarState c_rGetCarPosition(CPacketParser cp){
		MsgGPRSParser mgp =  new MsgGPRSParser(Arrays.copyOfRange(cp.pktData, 4, cp.pktData.length));
		CarState cs = new CarState(mgp.msgData);
		return cs;
		
	}
	

}
