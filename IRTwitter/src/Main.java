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
	}
}
