package tackerUtil;


public class Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MsgParser mp = new MsgParser(MsgParser.MSG_SEND,"123",(short)0x40,(short)0x20);
		ByteHexUtil.printHexString(mp.msgByteBuf);

	}

}
