package topics.introduction.maxpairwise;

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

/**
 * <h1>Maximum Pairwise Product (Sorting Strategy)</h1>
 * <p>
 * Evaluates the maximum pairwise product from a dataset of integers loaded 
 * from an external text file. This implementation drastically improves upon 
 * the <code>O(N&sup2;)</code> brute-force method by leveraging a sorting algorithm 
 * to instantly identify the two largest values in the sequence.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N log N)</code> - The time required is strictly dominated by the sorting operation (typically TimSort in Java). This completely eliminates the quadratic bottleneck, allowing the algorithm to handle massive datasets efficiently.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Requires linear memory to store the dataset in the collection prior to sorting.</li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct4 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct4.class);
    private final List<Integer> numbers = new ArrayList<>();
    
    /**
     * Initializes the computational context by reading a single-line dataset of 
     * space-separated integers from the filesystem into memory.
     */
    public MaxPairWiseProduct4() {
        Path path = Paths.get("src/main/java/topics/introduction/MaxPairWiseProductRandomNumbers.txt");
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            if (line != null) {
                for (String number : line.split(" ")) {
                    numbers.add(Integer.valueOf(number));
                }
            }
        } catch (IOException e) {
            log.error("Failed to load the random numbers dataset.", e);
        }
    }
    
    /**
     * Computes the maximum mathematical product by sorting the array descending 
     * and multiplying the top two absolute maximums.
     * <p>
     * <i>Note: This specific logic assumes a dataset where the largest product 
     * stems from two positive integers.</i>
     * </p>
     *
     * @return The highest mathematical pairwise product found in the dataset.
     * @throws IllegalStateException if the dataset contains fewer than two elements.
     */
    public long compute() {
        if (numbers.size() < 2) {
            throw new IllegalStateException("Dataset must contain at least two numbers to compute a pairwise product.");
        }

        // Sort descending to place the largest integers at indices 0 and 1
        numbers.sort(Collections.reverseOrder());
        
        // Extract the two largest candidates using 64-bit promotion to prevent overflow
        long candidate1 = numbers.get(0);
        long candidate2 = numbers.get(1);

        long maxProduct = candidate1 * candidate2;
        
        log.info("The computed result is = {}", maxProduct);
        return maxProduct;
    }
}