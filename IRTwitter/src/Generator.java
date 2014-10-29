import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author segrahn
 * @version 2014-10-29
 */
public class Generator {
	private final int MAX_LENGTH = 140;
	private Corpus corpus;
	private StringBuilder sb;


	public Generator(Corpus c){
		corpus = c;
		sb = new StringBuilder();
	}

	public void createSentence(String start){
		sb.setLength(0);
		LinkedList<NGram> ngrams = new LinkedList<NGram>();

		Iterator<String> listIt = corpus.bigrams.keySet().iterator();
		start = listIt.next();
		String first = "";
		String second = start;
		
		while(sb.length() < MAX_LENGTH){
			if(corpus.trigrams.containsKey(first+second)){
				ngrams = corpus.trigrams.get(first+second);
			}
			else{
				ngrams = corpus.bigrams.get(second);
			}
			
			if(ngrams == null){
				break;
			}else{
				first = second;
				NGram ngram = ngrams.getLast();
				second = ngram.getNext() + "$";
				sb.append(ngram.getNext() +  " ");
			}


		}
		System.out.println(sb.toString());
	}


}
