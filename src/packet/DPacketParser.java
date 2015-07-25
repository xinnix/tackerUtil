package packet;

import java.io.*;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DPacketParser {
	
	
	
	
	
	
	public int pktHead=0x12345678;	//数据库报文头
	public int pktLength;
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
	
	
	public DPacketParser(int pktSingal,int pktDataRow,int pktDataColumn,int[] pktDataColumnType,int[] pktDataColumnLength,byte[] pktData){
		this.pktSingal = pktSingal;
		this.pktDataRow = pktDataRow;
		this.pktDataColumn = pktDataColumn;
		this.pktDataColumnType = pktDataColumnType;
		this.pktDataColumnLength = pktDataColumnLength;
	//	System.out.println(ByteHexUtil.bytesToHexString(pktData));
		this.pktData = JzilbHelp.jzlib(pktData);
		
		this.pktLength = 5*4+this.pktDataColumn*4*2+this.pktData.length+6;
		
		//System.out.println(ByteHexUtil.bytesToHexString(this.pktData));
		//this.pktData = pktData;
		ByteArrayOutputStream  bis = new ByteArrayOutputStream();
		
		try{
			bis.write(ByteHexUtil.intToByte(this.pktHead));
			bis.write(ByteHexUtil.intToByte(this.pktLength));
			bis.write(ByteHexUtil.intToByte(this.pktSingal));
			bis.write(ByteHexUtil.intToByte(this.pktDataRow));
			bis.write(ByteHexUtil.intToByte(this.pktDataColumn));
			for (int ii=0;ii<this.pktDataColumn;ii++){
				bis.write(ByteHexUtil.intToByte(pktDataColumnType[ii]));
			}
			for (int ii=0;ii<this.pktDataColumn;ii++){
				bis.write(ByteHexUtil.intToByte(pktDataColumnLength[ii]));
			}
			bis.write(this.pktData);
			this.pktCheck =packetCheck(bis.toByteArray());
			
			bis.write(this.pktCheck);
			bis.write(this.pktVersion);
			bis.write(ByteHexUtil.intToByte(this.pktEnd));
			
			
			this.pktBuffer = bis.toByteArray();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public DPacketParser(byte[] pktBuffer){
		
		int head = 0;
		
		
		this.pktHead = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head,head+=4));
		this.pktLength = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		byte[] pkt =  Arrays.copyOfRange(pktBuffer, 0, this.pktLength);
		
		this.pktSingal = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		this.pktDataRow = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		this.pktDataColumn = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		for (int ii=0;ii<this.pktDataColumn;ii++){
			this.pktDataColumnType[ii] = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		}
		for (int ii=0;ii<this.pktDataColumn;ii++){
			this.pktDataColumnType[ii] = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pktBuffer,head++,head+=4));
		}
		
		int datalen = this.pktLength-4*5-this.pktDataColumn*4*2-2-2-4;
		
		this.pktData = JzilbHelp.unjzlib(Arrays.copyOfRange(pkt,head++,head+=(datalen-1)));
		this.pktCheck = pkt[head++];
		this.pktVersion = pkt[head++];
		
		this.pktHead = ByteHexUtil.bytesToInt(Arrays.copyOfRange(pkt,head++,head+=3));
	}
	
	private byte packetCheck(byte[] data){
		byte res=data[0];
	    for (int ii=1; ii<data.length;ii++)
	    { 
	    	   res = (byte) (res ^ data[ii]);
	     }
		return res;
	}

}
