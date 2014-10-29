import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashMap;
/**
 *
 * @author Tryti
 * @version 2014-10-27
 */
public class Main {

	/** */
	public Main(String inputFile, String outputFile){
	}

	public static void main(String[] args) throws IOException {
		String fileToRead = "C:/Users/Sebbe/Documents/GitHub/IRTwitter/IRTwitter/data/testdata.mini.csv";

		Parser parser = new Parser(fileToRead);
		parser.parseFile();
		
		HashMap<String, LinkedList<NGram>> tri = parser.getNGrams(3);
		HashMap<String, LinkedList<NGram>> bi = parser.getNGrams(2);
		Iterator<String> bit = bi.keySet().iterator();
		while(bit.hasNext()){
			LinkedList<NGram> tempList = bi.get(bit.next());
			System.out.print("Key: "+tempList.getFirst().getKey() + ", [");
			for(NGram n: tempList){
				System.out.print("{Next: " + n.getNext() + ", weight: "+ n.getWeight()+"}, ");
			}
			System.out.println("]");
		}
		
		Corpus c = new Corpus();
		c.addBigrams(bi);
		c.addTrigrams(tri);
		Generator g = new Generator(c);
		g.createSentence("No$");
	}

}
