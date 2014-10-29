import java.io.IOException;
import java.util.LinkedList;

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
		String fileToRead = "../data/testdata.mini.csv";

		Parser parser = new Parser(fileToRead);
		parser.parseFile();

		System.out.println("BIGRAMS");
		LinkedList<NGram> bigrams = parser.getNGrams(2);
		for(NGram n: bigrams){
			System.out.println(n);
		}

		System.out.println("TRIGRAMS");
		LinkedList<NGram> trigrams = parser.getNGrams(3);
		for(NGram n: trigrams){
			System.out.println(n);
		}
		//parser.debugPrint();
	}

}
