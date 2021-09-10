import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LZWCompressor {
	
	//creating variable fileName for .txt file needed to be compressed
	String fileName = "";
	public LZWCompressor(String inputFileName){
		this.fileName = inputFileName;
	}
	
	//creating Dictionary dictionary
	public static HashMap<String, Integer> dictionary;
	
	//adding existing ASCII characters to dictionary
	{
		for (int i = 0; i < 256; i++)
			dictionary.put((""+((char)i)), i);
	}
	
	//building dictionary, reading from filename.txt
	String current;//current character
	String next;//next character
	public String readText() throws IOException{
		String output = "";
		int dictSize = 256;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		//sets current to 1st character in fileName.txt
		current = ""+(char)(reader.read());
		
		//go through fileName.txt, adds to dictionary & output
		while (reader.ready()){
			next = ""+(char)(reader.read());
			if (dictionary.containsKey(current+next))
				current = current+next;
			else{
				output+=current;
				dictionary.put(current+next, dictSize++);
				current = next;
			}
		}
		if (!current.equals(""))
			output+=current;
		reader.close();
		return output;
	}
}
