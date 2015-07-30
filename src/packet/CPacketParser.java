package packet;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class CPacketParser {
	
	
	public final static byte SIGNAL_LOGIN = (byte)0xa3; 
	public final static byte SIGNAL_RE_LOGIN = (byte)0xe3; 
	public final static byte SIGNAL_RE_LOCATE = (byte)0xaa; 
	
	
	public short pktHead=0x2929;
	public byte pktSignal;
	public short pktLength;
	public int pktFakeIP;
	public byte[] pktData;
	public byte pktCheck;
	public byte pktEnd=0x0d;
	
	//�洢�ֽ���
	public byte[] pktBuffer;
	
	/*
	 * ����ʱָ��������
	 */
	public CPacketParser(byte pktSignal,byte[] data){
		
		
		
		
		
		
		this.pktSignal = pktSignal;
		this.pktData = data;
		this.pktLength = (short)(6+data.length);
		this.pktFakeIP = 0;
		
		ByteArrayOutputStream  bis = new ByteArrayOutputStream();
		
		try{
			bis.write(ByteHexUtil.shortToByte(this.pktHead));
			bis.write(this.pktSignal);
			bis.write(ByteHexUtil.shortToByte(this.pktLength));
			bis.write(ByteHexUtil.intToByte(this.pktFakeIP));
			bis.write(this.pktData);
			
			this.pktCheck =packetCheck(bis.toByteArray());
			
			bis.write(this.pktCheck);
			bis.write(this.pktEnd);
			
			
			
			this.pktBuffer = bis.toByteArray();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	/*
	 * ���հ�������
	 */
	public CPacketParser(byte[] pktBuffer){
		
		System.out.println(ByteHexUtil.bytesToHexString(pktBuffer));
		int head = 0;
		
		this.pktHead = ByteHexUtil.byteToShort(Arrays.copyOfRange(pktBuffer,head,head+=2));
		this.pktSignal = pktBuffer[head];
		head++;
		this.pktLength = ByteHexUtil.byteToShort(Arrays.copyOfRange(pktBuffer,head,head+=2));
		this.pktFakeIP = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head,head+=4));
		
		int datalen = this.pktLength - 6;
		this.pktData =  Arrays.copyOfRange(pktBuffer, head, head+=datalen);
		this.pktCheck = pktBuffer[head];
		head++;
		this.pktEnd = pktBuffer[head];
		this.pktBuffer = pktBuffer;
	
	}
	
	public static byte packetCheck(byte[] data){
		byte res=data[0];
	    for (int ii=1; ii<data.length;ii++)
	    { 
	    	   res = (byte) (res ^ data[ii]);
	     }
		return res;
	}

}
