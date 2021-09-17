import java.util.ArrayList;
import java.io.*;

public class BinaryConverter {
//	private static final int BUFFER_SIZE = 4096; // 4KB
	String input;
	String output;
	int sum;
//	
	public void convertToBinary() throws IOException{
	DataOutputStream dos = new DataOutputStream(new FileOutputStream("binaryOutput.txt"));
	
	ArrayList<Integer> list = new ArrayList<Integer>();{
	
	for(int i = 0; i < list.size(); i++ ) {
	    if(i%2!=0){
	        sum |= list.get( i ).intValue()<<16;
	        dos.writeInt( sum );
	    } else {
	        sum = list.get( i ).intValue();
	    }
	}
	
}}}
