package tackerUtil;

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
	
	MsgParser(int type,String termID,short msgType,short msgArg){
		if (type==MsgParser.MSG_SEND){
			this.msgHead="$$";
			this.msgLength = 0x0011; //暂时固定长度
			this.msgTermID = termID;
			this.msgType = msgType;
			this.msgArg = msgArg;
			this.msgContent = "";
			this.msgEnd = 0x0d0a;
		}
		
		
	
		
	}
	
	
}
