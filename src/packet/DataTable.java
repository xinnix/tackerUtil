package packet;

import java.util.Arrays;

public class DataTable{
	public Object[][] table;
	
	DataTable(int dataRow,int dataColumn,int[] dataColumnType,int[] dataColumnLength,byte[] data){
		int head = 0; //ָ��
		table = new Object[dataRow][dataColumn];
		for (int ii=0;ii<dataRow;ii++){
			for (int jj=0;jj<dataColumn;jj++){
				switch (dataColumnType[jj]){
				case DPacketParser.DATA_TYPE_STRING:
					table[ii][jj]=new String(Arrays.copyOfRange(data,head,head+=dataColumnLength[jj]));
					break;
				case DPacketParser.DATA_TYPE_INTEGER:
					table[ii][jj]=(int)ByteHexUtil.bytesToInt(Arrays.copyOfRange(data,head,head+=dataColumnLength[jj]));
					break;
				case DPacketParser.DATA_TYPE_BYTE:
					table[ii][jj]=(byte)data[head++];
					break;
				case DPacketParser.DATA_TYPE_BOOLEAN:
					table[ii][jj]=(boolean)(ByteHexUtil.bytesToInt(Arrays.copyOfRange(data,head,head+=dataColumnLength[jj]))==0?false:true);
					break;
				case DPacketParser.DATA_TYPE_DOUBLE:
					byte[] srcdata = Arrays.copyOfRange(data,head,head+=dataColumnLength[jj]);
					table[ii][jj]=(double)(ByteHexUtil.bytesToDouble(srcdata));
					break;
				
				}
				
			}
		}
		
	}
}
