package topics.introduction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Computes the max pairwise product among different numbers
 * E.g.: 7 3 6 => 42
 * We will load the numbers from MaxPairWiseProductRandomNumbers.txt
 * @author vicegd
 *
 */
public class MaxPairWiseProduct5 {
  private static Logger log = LoggerFactory.getLogger(MaxPairWiseProduct5.class);
  private List<Integer> numbers = new ArrayList<Integer>();
  
  public MaxPairWiseProduct5() {
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
    Collections.sort(numbers);
    long candidate1 = numbers.get(numbers.size()-1); 
    long candidate2 = numbers.get(numbers.size()-2); 

    log.info("The result is = " + candidate1 * candidate2);
    
    return candidate1 * candidate2;
  }
}

