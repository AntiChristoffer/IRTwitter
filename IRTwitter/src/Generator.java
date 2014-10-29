import java.util.LinkedList;

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
			//until comparable is done loop to get highest weight
			/*if(corpus.trigrams.get(start) != null){
				ngrams = corpus.trigrams.get(start);
			}
			else{*/
				ngrams = corpus.bigrams.get(start);
			//}
			System.out.println(ngrams.size());
			int bestWeight = ngrams.getLast().getWeight();
			String bestWord = ngrams.getLast().getNext();
			for(NGram ng : ngrams){
				if(ng.getWeight() > bestWeight){
					bestWeight = ng.getWeight();
					bestWord = ng.getNext();
				}
			}

			sb.append(bestWord+" ");
			start = bestWord;
		}
		System.out.println(sb.toString());
	}


}
