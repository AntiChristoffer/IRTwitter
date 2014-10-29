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
		sb.append(start+" ");
		LinkedList<NGram> ngrams = new LinkedList<NGram>();
		while(sb.length() < MAX_LENGTH){
			Iterator<String> listIt = corpus.bigrams.keySet().iterator();
			ngrams = corpus.bigrams.get(listIt.next());
			System.out.println(ngrams.size());
			int bestWeight = ngrams.getLast().getWeight();
			String bestWord = ngrams.getLast().getNext();

			sb.append(bestWord+" ");
			start = bestWord;
		}
		System.out.println(sb.toString());
	}


}
