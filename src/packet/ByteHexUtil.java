package packet;

public class ByteHexUtil {
	
	/* Convert byte[] to hex string.�������ǿ��Խ�byteת����int��Ȼ������Integer.toHexString(int)��ת����16�����ַ�����  
	 * @param src byte[] data  
	 * @return hex string  
	 */     
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  
	/** 
	 * Convert hex string to byte[] 
	 * @param hexString the hex string 
	 * @return byte[] 
	 */  
	public static byte[] hexStringToBytes(String hexString) {  
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }  
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++) {  
	        int pos = i * 2;  
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }  
	    return d;  
	}  
	/** 
	 * Convert char to byte 
	 * @param c char 
	 * @return byte 
	 */  
	 private static byte charToByte(char c) {  
	    return (byte) "0123456789ABCDEF".indexOf(c);  
	}  
	 
	 
	 public static void printHexString( byte[] b) {    
		   for (int i = 0; i < b.length; i++) {   
		     String hex = Integer.toHexString(b[i] & 0xFF);   
		     if (hex.length() == 1) {   
		       hex = '0' + hex;   
		     }   
		     System.out.print(hex.toUpperCase() );   
		   }   
		  
	}  
	 
	 /** 
	  * ����λ�Ƶ�intת����byte[] 
	  * @param int  number 
	  * @return byte[] 
	  */  
	   
	 public static byte[] intToByte(int number) {  
	     byte[] abyte = new byte[4];  
	     // "&" �루AND�������������Ͳ������ж�Ӧλִ�в�������������λ��Ϊ1ʱ���1������0��  
	     abyte[0] = (byte) (0xff & number);  
	     // ">>"����λ����Ϊ�������λ��0����Ϊ�������λ��1  
	     abyte[1] = (byte) ((0xff00 & number) >> 8);  
	     abyte[2] = (byte) ((0xff0000 & number) >> 16);  
	     abyte[3] = (byte) ((0xff000000 & number) >> 24);  
	     return abyte;  
	 }  
	   
	 /** 
	  *����λ�Ƶ� byte[]ת����int 
	  * @param byte[] bytes 
	  * @return int  number 
	  */  
	   
	 public static int bytesToInt(byte[] bytes) {  
	     int number = bytes[0] & 0xFF;  
	     // "|="��λ��ֵ��  
	     number |= ((bytes[1] << 8) & 0xFF00);  
	     number |= ((bytes[2] << 16) & 0xFF0000);  
	     number |= ((bytes[3] << 24) & 0xFF000000);  
	     return number;  
	 }  
	 

}
