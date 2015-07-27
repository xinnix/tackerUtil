package tackerUtil;
import java.io.OutputStream;
import java.util.Arrays;

import network.HeartBeat;
import network.NetworkAdapter;

import packet.ByteHexUtil;
import packet.DPacketParser;
import packet.JzilbHelp;

public class Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
		NetworkAdapter na = new NetworkAdapter("61.145.122.143",4519);
		na.start();
		MsgEventHandler.config(na);
		MsgEventHandler.sLogin("lkk", "lkk");
		HeartBeat hb = new HeartBeat();
		hb.start();
		MsgEventHandler.sGetCarGroup(2,"");
		//MsgEventHandler.sGetUserInfo(2);
		
		
		
		
		//
		//byte [] a = {1,2};
		//String str="123456780000003a00000001000000010000000200000001000000010000000600000006789CCBC9CE666060C8019300142E0285990187654321";
		//String str="123456780000004200000001000000010000000200000001000000010000000c0000000c789c2bae2c2e49cd650083a4e2acf48262081b005111052fce0187654321";
		//byte[] buf  = ByteHexUtil.hexStringToBytes(str);
		//DPacketParser dp = new DPacketParser(buf);
		
		//String ss=(String)dp.dataTable.table[0][0];
		
		//na.sendPacket(buf);
		//na.start();
		/*String s1= "system";
		int num1=s1.length()*2;
		String linshiString1="";
	     for(int i=0;i<num1;i++)
	     {
	    	 linshiString1+="0";
	     }
		 
		s1 =str2HexStr(s1)+linshiString1;
		
		System.out.println(s1);*/
		
		//byte[] ziped = JzilbHelp.jzlib(pktData);
		
		//System.out.println(ByteHexUtil.bytesToHexString(dp.pktBuffer));
		//System.out.println(ByteHexUtil.bytesToHexString(ziped));
		//System.out.println(ss);
		//na.start();

	}
}
