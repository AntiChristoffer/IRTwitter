import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * 
 * @author Tryti, AntiChristoffer
 * @version 2014-10-27
 */
public class Parser {
	private final static String REGEX_SENTENCE = "/(?<=[.?!])\\S+(?=[a-z])/i";
	
	private LinkedList<String> sentences;
	private String filePath;

	public void parser(String inputFile) throws IOException{
		filePath = inputFile;
	}
	
	/** */
	public void parseFile() throws IOException{
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			LinkedList<String> messages = new LinkedList<String>();
			sentences = new LinkedList<String>();
			
			//Push each massage to messages
			String tempString = br.readLine();
			while(tempString != null){
				String[] tempStringArray = tempString.split("\",\"");
				messages.addLast(tempStringArray[5].substring(0, tempStringArray[5].length()-1));
				tempString = br.readLine();
			}
			
			//Split each message into sentences
			for(String message: messages){
				
			}
			br.close();
		} catch(FileNotFoundException e){
			System.out.println("Exception! " + e);
		}
	}

	
	/** 
	 * Prints sentences to file
	 */
	public void printToFile(){
		
	}
	
	/** */
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
