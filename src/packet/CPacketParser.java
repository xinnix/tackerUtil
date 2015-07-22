package packet;

public class CPacketParser {
	
	
	public static short SIGNAL_LOGIN = 0xa3; 
	
	
	public short pktHead=0x2929;
	public short pktSignal;
	public String pktLength;
	public short pktFakeIP;
	public String pktData;
	public short pktCheck;
	public byte pktEnd=0x0d;
	
	//存储字节流
	public byte[] pktByteBuf;
	
	/*
	 * 发送时指定包类型
	 */
	CPacketParser(short pktSignal,short pktFakeIP){
		
	}
	
	
	/*
	 * 接收包并解析
	 */
	CPacketParser(byte[] pktByteBuf){
		
	}

}
