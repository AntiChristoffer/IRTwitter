import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Parser parser = new Parser();
		parser.run();
	}
	public void parser() throws IOException{
		
		
	}
	
	public void run() throws IOException{
	try{
		String fileToRead = "data/testdata.manual.2009.06.14.csv";
		String fileToWrite = "data/testdataPurged.txt";
		BufferedReader br = new BufferedReader(new FileReader(fileToRead));
		FileWriter out = new FileWriter(fileToWrite); 
		while(br.read() != -1){
			String tempString = br.readLine();
			String[] tempStringArray = tempString.split("\",\"");
			out.write(tempStringArray[5].substring(0, tempStringArray[5].length()-1));
			out.write("\n");
		}
			
	} catch(FileNotFoundException e){
		System.out.println("Exception! " + e);
	}
}
}