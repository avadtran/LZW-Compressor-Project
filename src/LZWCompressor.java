import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LZWCompressor {
	public HashMap<String, Integer> dictionary=new HashMap<String,Integer>();
	
	//creating variable fileName for .txt file needed to be compressed
	String fileName = "";
	ArrayList<Integer> outputInts = new ArrayList<Integer>();
	public LZWCompressor(String inputFileName){
		this.fileName = inputFileName;
		for (int i = 0; i < 256; i++)
			dictionary.put((""+((char)i)), i);
	}
	
	//creating Dictionary dictionary
	
	//adding existing ASCII characters to dictionary
	
	//building dictionary, reading from filename.txt
	String current;//current character
	String next;//next character
	public ArrayList<Integer> encode() throws IOException{
		//String output = "";
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
				//output+=current;
				dictionary.put(current+next, dictSize++);
				outputInts.add(dictionary.get(current));
				current = next;
			}
		}
		
		//return identical text
		if (!current.equals("")) {
			outputInts.add(dictionary.get(current));
			//output+=current;
		}
		reader.close();
		return outputInts;
	}
	
	public void decode() throws FileNotFoundException{
		String c = ""; //current
		String n = ""; //next
		
		int dictSize=256;
        HashMap<Integer,String> dict = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++) { //adding ASCII values 0-255
        	dict.put(i, "" + (char)i);
        }
        c = "" + (char)(int)outputInts.remove(0);
        StringBuilder output = new StringBuilder(c); //a StringBuilder object to which characters will be added to. will be outputted at the end of the method.
        
        for (int integerKey : outputInts) { //integerKey is the integer key associated with a string of characters in the dictionary
            if (dict.containsKey(integerKey)) { //checks if integer value already exists in dictionary. if it does, set n equal to the string associated in order to append onto StringBuilder.
            	n = dict.get(integerKey);
            }else if (integerKey == dictSize) { //if the integer equals dictionary size, need to append first character to the string before adding to the dictionary.
            	n = c + c.charAt(0);
            }
            output.append(n); //constructing output
            dict.put(dictSize++, c + n.charAt(0)); //add new value to dictionary
            c=n; //prepare for next iteration of for loop by changing c
        }
        PrintWriter out = new PrintWriter("/Users/zanedanton/Documents/GitHub/LZW-Compressor-Project/src/decompressed.txt"); //outputting to file.
		out.println(output.toString());
		out.close();
	}
	
	public static void main (String [] args) throws IOException { //main to test that it works.
		LZWCompressor w = new LZWCompressor("/Users/zanedanton/Documents/GitHub/LZW-Compressor-Project/src/lzw-file3.txt");
		w.encode();
		w.decode();
	}
}
