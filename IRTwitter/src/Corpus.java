import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
/**
 * Corpus containing several ngram collections
 * @author Tryti
 * @version 2014-10-27
 */
public class Corpus {
	private LinkedList<String> usernames;
	private LinkedList<String> startwords;
	private LinkedList<String> hashtags;
	private Random random;

	public HashMap<String, LinkedList<NGram>> bigrams;
	public HashMap<String, LinkedList<NGram>> trigrams;
	public HashMap<String, LinkedList<NGram>> quadgrams;


	/**
	 *
	 */
	public Corpus(Parser p) {
		random = new Random();
		bigrams = p.getNGrams(2);
		trigrams = p.getNGrams(3);
		quadgrams = p.getNGrams(4);
		usernames = p.getUserNames();
		startwords = p.getStartWords();
		hashtags = p.getHashtags();
	}


	public String getRandomStartWord(){
		return startwords.get(random.nextInt(startwords.size()));
	}

	public String getRandomUserName(){
		return usernames.get(random.nextInt(usernames.size()));
	}

	public String getRandomHashtag(){
		return hashtags.get(random.nextInt(hashtags.size()));
	}
}
