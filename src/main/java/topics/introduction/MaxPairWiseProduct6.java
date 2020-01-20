package topics.introduction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Computes the max pairwise product among different numbers
 * E.g.: 7 3 6 => 42
 * We will load the numbers from MaxPairWiseProductRandomNumbers.txt
 * @author viceg
 *
 */	
public class MaxPairWiseProduct6 {
	private static Logger log = LoggerFactory.getLogger(MaxPairWiseProduct6.class);
	private int[] numbers;

	public MaxPairWiseProduct6() {
		numbers = new int[100000];
		Path path = Paths.get("src/main/java/topics/introduction/MaxPairWiseProductRandomNumbers.txt");
		int i = 0;
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    for (String number : reader.readLine().split(" ")) {
		    	numbers[i] = Integer.valueOf(number);
		    	i++;
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long compute() {	
		long candidate1 = 0;
		long candidate2 = 0;
		for (int i = 0; i < numbers.length; i++) {
			if ((numbers[i] > candidate1)||numbers[i] > candidate2) {
				if (candidate1 < candidate2)
					candidate1 = numbers[i];
				else candidate2 = numbers[i];
			}
		}

		log.info("The result is = " + candidate1 * candidate2);
		return candidate1 * candidate2;
	}
}
