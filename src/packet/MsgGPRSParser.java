package packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



public class MsgGPRSParser {
	
	public static short MSG_SEND = 0x01;
	public static short MSG_RECV = 0x02;
	
	
	public String msgHead;
	public short msgLength;
	public String msgTermID;
	public short msgType;
	public short msgArg;
	public String msgContent;
	public short msgReply;
	public short msgCrc;
	public short msgEnd;	
	public byte[] msgByteBuf;
	
	//�ϲ��ֽ�����
	private ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	
	
	MsgGPRSParser(byte[] msgByteBuf){
		
	}//end of MsgParser
		
		
		
		
	
	
	
}
