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
	private StringBuilder sb;
	private Random rnd;


	public Generator(Corpus c){
		corpus = c;
		rnd = new Random();
		sb = new StringBuilder();
	}

	public Result createSentence(){
		sb.setLength(0);
		int weight = 0;
		LinkedList<NGram> ngrams = new LinkedList<NGram>();

		String first = "";
		String second = "";
		String third = corpus.getRandomStartWord()+"$";
		
		int andCount = 0;
		boolean looping = true;


		while(sb.length() < MAX_LENGTH && looping){
			boolean append = true;
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
					
					if(ngram.getNext().equals(Constants.HASHTAG)){
						String tmphash = corpus.getRandomHashtag();
						sb.append(tmphash);
						append = false;
						ngram.setWeight(50);
					}
					else if(ngram.getNext().equals(Constants.USERNAME)){
						String tmpuname = corpus.getRandomUserName();
						sb.append(tmpuname);
						append = false;
						ngram.setWeight(50);
					}
					
					third = ngram.getNext() + "$";
					
					if(third.equals("and$")){
						andCount++;
					}
					if(andCount < 2){
						if(append)sb.append(ngram.getNext());
						weight += ngram.getWeight();
						break;
					}
				}
				default:{
					andCount = 0;
					if(sb.length() > 0){
						NGram tmp = new NGram(". ");
						tmp.setWeight(2);
						if(append)sb.append(tmp);
						weight += tmp.getWeight();
					}
					
					if((MAX_LENGTH - sb.length()) > MINCHAR){
						first = "";
						second = "";
						third = corpus.getRandomStartWord()+"$";
					}else looping = false;
				}
			}

		}
		String message = sb.toString();
		return new Result(message, weight);
	}
}
