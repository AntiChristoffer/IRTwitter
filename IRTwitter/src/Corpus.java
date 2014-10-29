import java.util.HashMap;
import java.util.LinkedList;

/**
 * Corpus containing several ngram collections
 * @author Tryti
 * @version 2014-10-27 
 */
public class Corpus {
	private HashMap<String, LinkedList<NGram>> bigram;
	private HashMap<String, LinkedList<NGram>> trigram;
	
	/**
	 * 
	 */
	public Corpus() {
		bigram = new HashMap<String, LinkedList<NGram>>();
		trigram = new HashMap<String, LinkedList<NGram>>();
	}
	
	/**
	 * 
	 * 
	 */
	public void addBigrams(LinkedList<NGram> newBigrams){
		for(NGram n: newBigrams){
			if(bigram.get(n.getKey()) == null){
				bigram.put(n.getKey(), new LinkedList<NGram>());
			}
			bigram.get(n.getKey()).addLast(n);
		}
	}
	
	/**
	 * 
	 * 
	 */
	public void addTrigrams(LinkedList<NGram> newTrigrams){
		for(NGram n: newTrigrams) {
			if(trigram.get(n.getKey()) == null){
				trigram.put(n.getKey(), new LinkedList<NGram>());
			}
			trigram.get(n.getKey()).addLast(n);
		}
	}
}
