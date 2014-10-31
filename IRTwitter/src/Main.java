import java.io.IOException;
import java.util.ArrayList;
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
		String fileToRead = "./data/test_set_tweets.mini.txt";

		Corpus c = new Corpus(new Parser(fileToRead));

		System.out.println("CORPUS CREATED");

		Generator g = new Generator(c);


		for(int i = 0; i < 500; i++){
			Result r = g.createSentence();
			System.out.println(r.getMessage());
			System.out.println();
		}

	}

}
