import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class LZWCompressor {
	
	//creating variable fileName for .txt file needed to be compressed
	String fileName = "";
	public LZWCompressor(String inputFileName)
	{
		this.fileName = inputFileName;
	}
	
	//creating Dictionary asciiTable
	public static HashMap<String, Integer> asciiTable;
	
	//adding existing characters to asciiIable
	{
		for (int i = 0; i < 256; i++)
		{
			asciiTable.put((""+((char)i)), i);
		}
	}
	
	
	
	
	
}
