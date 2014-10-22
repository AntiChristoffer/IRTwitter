import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Parser parser = new Parser();
		String fileToRead = "data/testdata.manual.2009.06.14.csv";
		String fileToWrite = "data/testdataPurged.txt";
		parser.run(fileToRead, fileToWrite);
		String split3File = "data/testDataSplit3gram.txt";
		parser.nGramSplitter(fileToWrite, split3File, 3);
		String split2File = "data/testDataSplit2gram.txt";
		parser.nGramSplitter(fileToWrite, split2File, 2);
	}
	public void parser() throws IOException{
		
		
	}
	
	public void run(String fileToRead, String fileToWrite) throws IOException{
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			FileWriter out = new FileWriter(fileToWrite); 
			String tempString = br.readLine();
			while(tempString != null){
				String[] tempStringArray = tempString.split("\",\"");
				out.write(tempStringArray[5].substring(0, tempStringArray[5].length()-1));
				out.write("\n");
				tempString = br.readLine();
			}
			br.close();
			out.close();
				
		} catch(FileNotFoundException e){
			System.out.println("Exception! " + e);
		}
	}
	
	public void nGramSplitter(String fileToRead, String fileToWrite, int orderGram) throws IOException{
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileToRead));
			FileWriter out = new FileWriter(fileToWrite); 
			String tempString = br.readLine();
			while(tempString != null){
				tempString = tempString.replaceAll("[.]{2,}|[,]", "");
				String[] tempStringArray = tempString.split("\\s{1,}");
				for(int i = 0; i<tempStringArray.length; i++){
					System.out.println(tempStringArray[i]);
					String tempGram = "";
					for(int j = 0; j<orderGram;j++){
						if(i+j < tempStringArray.length){
							tempGram += tempStringArray[i+j] + " ";
							System.out.println("Inne i while loop");
							if(tempStringArray[i+j].endsWith(".")){
								break;
							}
						}
					}
					out.write(tempGram);
					out.write("\n");
				}
				tempString = br.readLine();
			}
			br.close();
			out.close();
				
		} catch(FileNotFoundException e){
			System.out.println("Exception! " + e);
		}
	}
}
