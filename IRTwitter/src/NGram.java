
/**
 * An n-gram datastruct
 * @author tryti
 * @version 2014-10-27
 */
public class NGram {
	private final static String KEYSEPATOR = "$";
	
	private String[] words;
	private String key;
	private int weight;
	
	public NGram(String[] words){
		this.words = words;
		updateKey();
		weight = 1;
	}
	
	/** Returns the key for this n-gram*/
	public String getKey(){return key;}
	
	/** */
	public String getNext(){return words[words.length-1];}
	
	/** */
	public int getWeight(){return weight;}
	
	/** */
	public void incWeight(){weight++;}
	
	/** Update the key value*/
	private void updateKey(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < words.length-1; i++){
			sb.append(words[i]);
			sb.append(KEYSEPATOR);
		}
		key = sb.toString();
	}
}
