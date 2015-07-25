package tackerUtil;
import java.io.OutputStream;
import java.util.Arrays;

import network.NetworkAdpter;
import packet.ByteHexUtil;
import packet.DPacketParser;
import packet.JzilbHelp;

public class Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		byte[] msg = DataBaseEvent.getLoginPacket("system", "bsjgps");
		
		
		
		//NetworkAdpter na = new NetworkAdpter("61.145.122.143",4519);
		//byte [] a = {1,2};
		//String str="123456780000003a00000001000000010000000200000001000000010000000c0000000c789CCBC9CE666060C8019300142E0285990187654321";
		String str="123456780000004200000001000000010000000200000001000000010000000c0000000c789c2bae2c2e49cd650083a4e2acf48262081b005111052fce0187654321";
		//na.sendPacket(dp.pktBuffer);
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
		System.out.println(str.equals(ByteHexUtil.bytesToHexString(msg)));
		//na.start();

	}
	public static String str2HexStr(String str) {  
		  
	    char[] chars = "0123456789ABCDEF".toCharArray();  
	    StringBuilder sb = new StringBuilder("");  
	    byte[] bs = str.getBytes();  
	    int bit;  
	    for (int i = 0; i < bs.length; i++) {  
	        bit = (bs[i] & 0x0f0) >> 4;  
	        sb.append(chars[bit]);  
	        bit = bs[i] & 0x0f;  
	        sb.append(chars[bit]);  
	    }  
	    return sb.toString();  
	}

}
