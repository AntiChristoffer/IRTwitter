import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
/**
 * Corpus containing several ngram collections
 * @author Tryti
 * @version 2014-10-27
 */
public class Corpus {
	private String[] userNames;
	private String[] startWords;
	public HashMap<String, LinkedList<NGram>> bigrams;
	public HashMap<String, LinkedList<NGram>> trigrams;

	/**
	 *
	 */
	public Corpus(Parser p) {
		bigrams = p.getNGrams(2);
		trigrams = p.getNGrams(3);
		userNames = p.getUserNames();
		startWords = p.getStartWords();
	}


	public String getRandomStartWord(){
		Random r = new Random();
		return startWords[r.nextInt(startWords.length)];
	}

	public String getRandomUserName(){
		return "";
	}
}
