import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.text.BreakIterator;
import java.util.Arrays;

/**
 *
 * @author Tryti, AntiChristoffer
 * @version 2014-10-27
 */
public class Parser {
	private final static String REGEX_SENTENCE_SPLIT = "\\s+";

	private LinkedList<String[]> sentences;
	private String filePath;

	public Parser(String inputFile) throws IOException{
		filePath = inputFile;
	}

	/** */
	public void parseFile() throws IOException{
		try{
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			LinkedList<String> messages = new LinkedList<String>();
			sentences = new LinkedList<String[]>();

			//Push each massage to messages
			String tempString = br.readLine();
			while(tempString != null){
				String[] tempStringArray = tempString.split("\",\"");
				messages.addLast(tempStringArray[5].substring(0, tempStringArray[5].length()-1));
				tempString = br.readLine();
			}

			//Split each message into sentences
			BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
			for(String message: messages){
				sentenceIterator.setText(message);
				int start = sentenceIterator.first();
				int end = sentenceIterator.next();
				while(end != BreakIterator.DONE){
					sentences.add(message.substring(start,end).split(REGEX_SENTENCE_SPLIT));
					start=end;
					end=sentenceIterator.next();
				}

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

	public void debugPrint(){
		System.out.println("Sentences:");
		Iterator<String[]> it = sentences.iterator();
		while(it.hasNext()){
			String[] sentence = it.next();
			for(int i = 0; i < sentence.length; i++){
				System.out.print(sentence[i]);
				System.out.print(" ");
			}
			System.out.println();
		}

	}

	public LinkedList<NGram> getNGrams(int order){
		LinkedList<NGram> nGrams = new LinkedList<NGram>();
		Iterator<String[]> it = sentences.iterator();
		while(it.hasNext()){//For each sentence
			String[] sentence = it.next();
			if(sentence.length > order + 1){//If sentence long enough for order
				for(int i = 0; i < sentence.length-1-order; i++){//Create nGrams
					String[] subSentence = Arrays.copyOfRange(sentence, i, i+order);
					//TODO handle subSentence
					NGram nGram = new NGram(subSentence);
					nGrams.add(nGram);
				}
			}
		}
		return nGrams;
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
