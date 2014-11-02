import java.util.LinkedList;
import java.util.Random;

/**
 * @author segrahn
 * @version 2014-10-29
 */
public class Generator {
	private final int MAX_LENGTH = 110;
	private final int MINCHAR = 60;
	private final int LEAST_WEIGHT = 5;
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
				//ngrams = corpus.quadgrams.get(first+second+third);
				ngrams = corpus.trigrams.get(second+third);
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
					int tmp = 0;
					while(tmp<ngrams.size()){
						if(ngrams.get(tmp).getWeight() > LEAST_WEIGHT) tmp++;
						else break;
					}
					if(tmp > 0){
						int index = rnd.nextInt(tmp);
						ngram = ngrams.get(index);
					}else ngram = ngrams.getFirst();

					if(ngram.getNext().equals(Constants.HASHTAG)){
						String tmphash = corpus.getRandomHashtag();
						sb.append(tmphash+" ");
						append = false;
						weight+=700;
					}
					else if(ngram.getNext().equals(Constants.USERNAME)){
						String tmpuname = corpus.getRandomUserName();
						sb.append(tmpuname+" ");
						append = false;
						weight+=100;
					}

					third = ngram.getNext() + "$";

					if(third.equals("and$")){
						andCount++;
					}
					if(andCount < 2){
						if(append){
							sb.append(ngram.getNext()+" ");
							weight += ngram.getWeight();
						}
						break;
					}
				}
				default:{
					andCount = 0;
					if(sb.length() > 0){
						if(append){
							sb.setCharAt(sb.length()-1, '.');
							sb.append(" ");
							weight += 2500;
						}
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
