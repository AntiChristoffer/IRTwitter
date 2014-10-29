import java.util.LinkedList;
import java.util.Iterator;

/**
 * @author segrahn
 * @version 2014-10-29
 */
public class Generator {
	private final int MAX_LENGTH = 140;
	private final int MINCHAR = 50;
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
		String second = corpus.getRandomStartWord()+"$";
		int andCount = 0;
		boolean looping = true;

		while(sb.length() < MAX_LENGTH && looping){
			if(corpus.trigrams.containsKey(first+second)){
				ngrams = corpus.trigrams.get(first+second);
			}
			else{
				ngrams = corpus.bigrams.get(second);
			}

			int switchval = 0;
			if(ngrams != null){
				switchval = 1;
			}

			switch(switchval){
				case 1:{
					first = second;
					NGram ngram = ngrams.getLast();
					second = ngram.getNext() + "$";
					if(second.equals("and$")){
						andCount++;
					}
					if(andCount < 2){
						sb.append(ngram.getNext() +  " ");
						break;
					}
				}
				default:{
					andCount = 0;
					sb.setCharAt(sb.length()-1, '.');
					sb.append(" ");
					if((MAX_LENGTH - sb.length()) > MINCHAR){
						first = null;
						second = listIt.next(); //TODO second = one of valid startwords
					}else looping = false;
				}
			}

		}
		System.out.println(sb.toString());
	}


}
