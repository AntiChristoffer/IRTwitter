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
	private LinkedList<String> usernames;
	private LinkedList<String> hashtags;
	private LinkedList<String> startwords;

	private String filePath;

	public Parser(String inputFile) throws IOException{
		filePath = inputFile;
		usernames = new LinkedList<String>();
		hashtags = new LinkedList<String>();
		startwords = new LinkedList<String>();
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
					if(sentence.length > 0){
						startwords.addLast(sentence[0]);
						for(int i = 0; i < sentence.length; i++){
							if(sentence[i].startsWith("@")){
								usernames.addLast(sentence[i]);
								sentence[i] = Constants.USERNAME;
							}else if(sentence[i].startsWith("#")){
								hashtags.addLast(sentence[i]);
								sentence[i] = Constants.HASHTAG;
							}else{
								sentence[i] = sentence[i].toLowerCase();
							}
						}
						sentences.add(sentence);
					}
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

	public LinkedList<String> getStartWords(){
		return startwords;
	}

	public LinkedList<String> getUserNames(){
		return usernames;
	}

	public LinkedList<String> getHashtags(){
		return hashtags;
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
