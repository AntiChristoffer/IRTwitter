/**
 * An n-gram datastruct
 * @author tryti
 * @version 2014-10-27
 */
public class NGram implements Comparable<NGram>{

	private String word;
	private int weight;

	public NGram(String word){
		this.word = word;
		weight = 1;
	}
	/** */
	public String getNext(){return word;}

	/** */
	public int getWeight(){return weight;}
	
	public void setWeight(int w){
		weight = w;
	}

	/** */
	public void incWeight(){weight++;}

	/**
	 * Compares this NGram's weight with another NGram's weight
	 */
	public int compareTo(NGram other){
		if(weight > other.getWeight()) return -1;
		else if(weight < other.getWeight()) return 1;
		else return 0;
	}
}