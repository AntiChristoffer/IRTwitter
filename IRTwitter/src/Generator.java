import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author segrahn
 * @version 2014-10-29
 */
public class Generator {
	private final int MAX_LENGTH = 100;
	private final int MINCHAR = 50;
	private Corpus corpus;
	private StringBuilder sb;
	private Random rnd;


	public Generator(Corpus c){
		corpus = c;
		sb = new StringBuilder();
		rnd = new Random();
	}

	public String createSentence(){
		sb.setLength(0);
		LinkedList<NGram> ngrams = new LinkedList<NGram>();
		//Iterator<String> listIt = corpus.bigrams.keySet().iterator();
		//start = listIt.next();
		String first = "";
		String second = "";
		String third = corpus.getRandomStartWord()+"$";
		
		int andCount = 0;
		boolean looping = true;

		while(sb.length() < MAX_LENGTH && looping){
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
						sb.append(ngram.getNext() +  " ");
						break;
					}
				}
				default:{
					andCount = 0;
					int sbl = sb.length()-1;
					if(sbl > 0) sb.setCharAt(sbl, '.');
					sb.append(" ");
					if((MAX_LENGTH - sb.length()) > MINCHAR){
						first = "";
						second = "";
						third = corpus.getRandomStartWord()+"$";
					}else looping = false;
				}
			}

		}
		return sb.toString();
	}


}
