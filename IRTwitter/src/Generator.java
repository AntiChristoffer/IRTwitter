import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author segrahn
 * @version 2014-10-29
 */
public class Generator {
	private final int MAX_LENGTH = 100;
	private final int MINCHAR = 60;
	private Corpus corpus;
	//private StringBuilder sb;
	private ArrayList<NGram> message;
	private Random rnd;


	public Generator(Corpus c){
		corpus = c;
		rnd = new Random();
	}

	public ArrayList<NGram> createSentence(){
		int messageLength = 0;
		message = new ArrayList<NGram>();
		LinkedList<NGram> ngrams = new LinkedList<NGram>();

		String first = "";
		String second = "";
		String third = corpus.getRandomStartWord()+"$";
		
		int andCount = 0;
		boolean looping = true;

		while(messageLength < MAX_LENGTH && looping){
			if(corpus.quadgrams.containsKey(first+second+third)){
				ngrams = corpus.quadgrams.get(first+second+third);
			}
			else if(corpus.trigrams.containsKey(second+third)){
				ngrams = corpus.trigrams.get(second+third);
			}
			else{
				ngrams = corpus.bigrams.get(third);
			}

			int switchval = 0;
			if(ngrams != null){
				switchval = 1;
			}

			switch(switchval){
				case 1:{
					NGram ngram;
					first = second;
					second = third;
					if(ngrams.size() > 3){
						int index = rnd.nextInt(3);
						ngram = ngrams.get(index);
					}else ngram = ngrams.getFirst();
					
					third = ngram.getNext() + "$";
					if(third.equals("and$")){
						andCount++;
					}
					if(andCount < 2){
						message.add(ngram);
						messageLength += ngram.getNext().length();
						break;
					}
				}
				default:{
					andCount = 0;
					if(messageLength > 0){
						message.add(new NGram(". "));
						messageLength += 2;
					}
					
					if((MAX_LENGTH - messageLength) > MINCHAR){
						first = "";
						second = "";
						third = corpus.getRandomStartWord()+"$";
					}else looping = false;
				}
			}

		}
		return message;
	}

	public int evaluate(ArrayList<NGram> message){
		int weight = 0;
		for(int i=0; i<message.size(); i++){
			weight += message.get(i).getWeight();
		}
		return weight;
	}
	
	public void printMessage(ArrayList<NGram> message){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<message.size(); i++){
			sb.append(message.get(i).getNext());
		}
		System.out.println(sb.toString());
	}
}
