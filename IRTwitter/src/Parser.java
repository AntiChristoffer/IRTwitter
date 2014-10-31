import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

import java.text.BreakIterator;

/**
 *
 * @author Tryti, AntiChristoffer
 * @version 2014-10-27
 */
public class Parser {

	private LinkedList<String[]> sentences;
	private String filePath;
	private String[] userNames;


	public Parser(String inputFile) throws IOException{
		filePath = inputFile;
		parseFile();
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
				if(tempString.length()>40){
					messages.addLast(tempString.substring(20, tempString.length()-20));
				}
				tempString = br.readLine();
			}

			//Split each message into sentences
			BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
			for(String message: messages){
				sentenceIterator.setText(message);
				int start = sentenceIterator.first();
				int end = sentenceIterator.next();
				while(end != BreakIterator.DONE){
					String[] sentence = message.substring(start,end).split(Constants.REGEX_SENTENCE_SPLIT);
					//TODO add for loop to improve sentence stuff
					for(String word: sentence){
						
					}
					sentences.add(sentence);
					start=end;
					end=sentenceIterator.next();
				}
			}
			br.close();
		} catch(FileNotFoundException e){
			System.out.println("Exception! " + e);
		}

		System.out.println("PARSED FILES");
	}

	public HashMap<String, LinkedList<NGram>> getNGrams(int order){
		HashMap<String, LinkedList<NGram>> nGrams = new HashMap<String, LinkedList<NGram>>();
		Iterator<String[]> it = sentences.iterator();
		while(it.hasNext()){//For each sentence
			String[] sentence = it.next();
			if(sentence.length > order + 1){//If sentence long enough for order
				for(int i = 0; i < sentence.length-1-order; i++){//Create nGrams
					String[] subSentence = Arrays.copyOfRange(sentence, i, i+order);
					NGram nGram = new NGram(subSentence[subSentence.length-1]);
					String key = getKey(subSentence);
					LinkedList<NGram> list = nGrams.get(key);
					if(list != null){
						boolean found = false;
						for(NGram n: list){
							if(n.getNext().equals(nGram.getNext())){
								n.incWeight();
								found = true;
								break;
							}
						}
						if(!found){
							list.add(nGram);
						}
					}else{
						LinkedList<NGram> newList = new LinkedList<NGram>();
						newList.add(nGram);
						nGrams.put(key, newList);
					}
				}
			}
		}
		//Sort lists on weights
		Iterator<String> listIt = nGrams.keySet().iterator();
		while(listIt.hasNext()){
			Collections.sort(nGrams.get(listIt.next()));
		}
		System.out.println("CREATED NGRAMS OF ORDER "+order);
		return nGrams;
	}

	public String[] getStartWords(){
		String[] sw = new String[sentences.size()];
		Iterator<String[]> it = sentences.iterator();
		int index = 0;
		while(it.hasNext()){//For each sentence
			String[] sentence = it.next();
			if(sentence.length > 0){
				sw[index++] = sentence[0];
			}
		}
		return sw;
	}

	public String[] getUserNames(){
		return userNames;
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

	private String getKey(String[] words){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < words.length-1; i++){
			sb.append(words[i]);
			sb.append(Constants.KEYSEPATOR);
		}
		return sb.toString();
	}
}