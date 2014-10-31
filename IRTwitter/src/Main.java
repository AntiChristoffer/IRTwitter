import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
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
		String fileToRead = "C:/Users/Sebbe/Documents/GitHub/IRTwitter/IRTwitter/data/test_set_tweets.mini.txt";

		Corpus c = new Corpus(new Parser(fileToRead));

		System.out.println("CORPUS CREATED");

		Generator g = new Generator(c);

		ArrayList<Result> results = new ArrayList<Result>();
		for(int i = 0; i < 500; i++){
			Result r = g.createSentence();
			results.add(r);
		}

		Collections.sort(results);

		for(int i = 0; i < 10; i++){
			System.out.println("-----------------");
			System.out.println("Weight: " + results.get(i).getWeight());
			System.out.println(results.get(i).getMessage());
		}

	}

}
