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
	
	//�洢�ֽ���
	public byte[] pktByteBuf;
	
	/*
	 * ����ʱָ��������
	 */
	CPacketParser(short pktSignal,short pktFakeIP){
		
	}
	
	
	/*
	 * ���հ�������
	 */
	CPacketParser(byte[] pktByteBuf){
		
	}

}
