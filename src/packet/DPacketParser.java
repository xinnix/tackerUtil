package packet;

import java.io.*;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DPacketParser {
	
	public int pktHead=0x12345678;	//数据库报文头
	public int pktLength=0x00000000;
	public int pktSingal;
	public int pktDataRow;
	public int pktDataColumn; 
	public int[] pktDataColumnType;
	public int[] pktDataColumnLength;
	public byte[] pktData;
	public byte pktCheck;
	public byte pktVersion = 0x01;	//版本
	public int pktEnd = 0x87654321;	//数据库报文尾
	
	
	public byte[] pktBuffer;
	
	
	DPacketParser(int pktSingal,int pktDataRow,int pktDataColumn,int[] pktDataColumnType,int[] pktDataColumnLength,byte[] pktData){
		this.pktSingal = pktSingal;
		this.pktDataRow = pktDataRow;
		this.pktDataColumn = pktDataColumn;
		this.pktDataColumnType = pktDataColumnType;
		this.pktDataColumnLength = pktDataColumnLength;
		this.pktData = pktData;
		
		ByteArrayOutputStream  bis = new ByteArrayOutputStream();
		
		try{
			bis.write(this.pktHead);
			bis.write(this.pktLength);
			bis.write(this.pktSingal);
			bis.write(this.pktDataRow);
			bis.write(this.pktDataColumn);
			for (int ii=0;ii<this.pktDataColumn;ii++){
				bis.write(pktDataColumnType[ii]);
			}
			for (int ii=0;ii<this.pktDataColumn;ii++){
				bis.write(pktDataColumnLength[ii]);
			}
			bis.write(this.pktData);
			this.pktCheck =packetCheck( bis.toByteArray());
			
			bis.write(this.pktCheck);
			bis.write(this.pktVersion);
			bis.write(this.pktEnd);
			
			
			this.pktBuffer = bis.toByteArray();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
	
	DPacketParser(byte[] pktBuffer){
		
		int head = 0;
		
		this.pktHead = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head,head+=3));
		this.pktLength = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		this.pktSingal = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		this.pktDataRow = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		this.pktDataColumn = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		for (int ii=0;ii<this.pktDataColumn;ii++){
			this.pktDataColumnType[ii] = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		}
		for (int ii=0;ii<this.pktDataColumn;ii++){
			this.pktDataColumnType[ii] = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=3));
		}
		this.pktData = 
	}
	
	private byte packetCheck(byte[] data){
		byte res=data[0];
	    for (int ii=0; ii<data.length;ii++)
	    {
	       if (ii > 1)
	       {
	    	   res = (byte) (res ^ data[ii+1]);
	       }
	     }
		return res;
	}
	
	
	/** 
     * 压缩 
     *  
     * @param data 
     *            待压缩数据 
     * @return byte[] 压缩后的数据 
     */  
    public static byte[] compress(byte[] data) {  
        byte[] output = new byte[0];  
  
        Deflater compresser = new Deflater();  
  
        compresser.reset();  
        compresser.setInput(data);  
        compresser.finish();  
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);  
        try {  
            byte[] buf = new byte[1024];  
            while (!compresser.finished()) {  
                int i = compresser.deflate(buf);  
                bos.write(buf, 0, i);  
            }  
            output = bos.toByteArray();  
        } catch (Exception e) {  
            output = data;  
            e.printStackTrace();  
        } finally {  
            try {  
                bos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        compresser.end();  
        return output;  
    }
    
    
    
    /** 
     * 解压缩 
     *  
     * @param data 
     *            待压缩的数据 
     * @return byte[] 解压缩后的数据 
     */  
    public static byte[] decompress(byte[] data) {  
        byte[] output = new byte[0];  
  
        Inflater decompresser = new Inflater();  
        decompresser.reset();  
        decompresser.setInput(data);  
  
        ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);  
        try {  
            byte[] buf = new byte[1024];  
            while (!decompresser.finished()) {  
                int i = decompresser.inflate(buf);  
                o.write(buf, 0, i);  
            }  
            output = o.toByteArray();  
        } catch (Exception e) {  
            output = data;  
            e.printStackTrace();  
        } finally {  
            try {  
                o.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
  
        decompresser.end();  
        return output;  
    }  

}
