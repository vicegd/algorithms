package topics.introduction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Computes the max pairwise product among different numbers
 * E.g.: 7 3 6 => 42
 * We will load the numbers from MaxPairWiseProductRandomNumbers.txt
 * @author viceg
 *
 */
public class MaxPairWiseProduct3 {
	private static Logger log = LoggerFactory.getLogger(MaxPairWiseProduct3.class);
	private List<Integer> numbers = new ArrayList<Integer>();
	
	public MaxPairWiseProduct3() {
		Path path = Paths.get("src/main/java/topics/introduction/MaxPairWiseProductRandomNumbers.txt");
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    for (String number : reader.readLine().split(" ")) {
		    	numbers.add(Integer.valueOf(number));
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long compute() {
		long max = 0;
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (i != j) {
					long result = numbers.get(i) * numbers.get(j);
					if (result > max)
						max = result;
				}
			}
		}
		log.info("The result is = " + max);
		return max;
	}
}
