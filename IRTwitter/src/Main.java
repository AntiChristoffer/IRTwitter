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
		String fileToRead = "../data/testdata.manual.2009.06.14.csv";


		Corpus c = new Corpus(new Parser(fileToRead));
		Generator g = new Generator(c);

		g.createSentence("No$");


		/**
		Iterator<String> bit = bi.keySet().iterator();
		while(bit.hasNext()){
			LinkedList<NGram> tempList = bi.get(bit.next());
			System.out.print("Key: "+tempList.getFirst().getKey() + ", [");
			for(NGram n: tempList){
				System.out.print("{Next: " + n.getNext() + ", weight: "+ n.getWeight()+"}, ");
			}
			System.out.println("]");
		}
		*/
	}

}
