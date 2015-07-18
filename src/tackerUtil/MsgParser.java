package tackerUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



public class MsgParser {
	
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
	
	
	MsgParser(int type,String termID,short msgType,short msgArg){
		if (type==MsgParser.MSG_SEND){
			this.msgHead="$$";
			this.msgLength = 0x0011; //��ʱ�̶�����
			this.msgTermID = termID;
			this.msgType = msgType;
			this.msgArg = msgArg;
			this.msgContent = "";
			this.msgEnd = 0x0d0a;
			try{
				bos.write(this.msgHead.getBytes());
				bos.write(this.msgLength);
				bos.write(this.msgTermID.getBytes());
				bos.write(this.msgType);
				
				
				
				this.msgByteBuf = bos.toByteArray();
				
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
					bos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
				
			}
		}//end of MsgParser
		
		
		
		
	}
	
	
}
