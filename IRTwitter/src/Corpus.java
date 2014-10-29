import java.util.HashMap;
import java.util.LinkedList;

/**
 * Corpus containing several ngram collections
 * @author Tryti
 * @version 2014-10-27
 */
public class Corpus {
	private LinkedList<String> userNames;
	public HashMap<String, LinkedList<NGram>> bigrams;
	public HashMap<String, LinkedList<NGram>> trigrams;

	/**
	 *
	 */
	public Corpus() {
		bigrams = new HashMap<String, LinkedList<NGram>>();
		trigrams = new HashMap<String, LinkedList<NGram>>();
	}

	public void addUserNames(LinkedList<String> newUserNames){
		userNames = newUserNames;
	}

	/**
	 *
	 */
	public void addBigrams(HashMap<String, LinkedList<NGram>> newBigrams){
		bigrams = newBigrams;
	}

	/**
	 *
	 */
	public void addTrigrams(HashMap<String, LinkedList<NGram>> newTrigrams){
		trigrams = newTrigrams;
	}
}
