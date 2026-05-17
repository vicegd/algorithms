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

/**
 * <h1>Maximum Pairwise Product (Ascending Sort Strategy)</h1>
 * <p>
 * Evaluates the maximum pairwise product from a dataset of integers loaded 
 * from an external text file. This implementation achieves the same 
 * <code>O(N log N)</code> efficiency as the descending sort method, but utilizes 
 * the default ascending order, extracting the maximum candidates directly 
 * from the tail of the collection.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N log N)</code> - The time required is strictly dominated by the native sorting algorithm (TimSort). This avoids the quadratic bottleneck of a brute-force search.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Requires linear memory to store the dataset in the collection prior to sorting.</li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct5 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct5.class);
    private final List<Integer> numbers = new ArrayList<>();
    
    /**
     * Initializes the computational context by reading a single-line dataset of 
     * space-separated integers from the filesystem into memory.
     */
    public MaxPairWiseProduct5() {
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
     * Computes the maximum mathematical product by sorting the array in its 
     * default ascending order and multiplying the two final elements.
     * <p>
     * <i>Note: Like previous iterations, this specific logic assumes a dataset 
     * where the largest product stems from two positive integers.</i>
     * </p>
     *
     * @return The highest mathematical pairwise product found in the dataset.
     * @throws IllegalStateException if the dataset contains fewer than two elements.
     */
    public long compute() {
        if (numbers.size() < 2) {
            throw new IllegalStateException("Dataset must contain at least two numbers to compute a pairwise product.");
        }

        // Sort ascending (default behavior) to place the largest integers at the end of the list
        Collections.sort(numbers);
        
        // Extract the two largest candidates from the tail using 64-bit promotion to prevent overflow
        long candidate1 = numbers.get(numbers.size() - 1); 
        long candidate2 = numbers.get(numbers.size() - 2); 

        long maxProduct = candidate1 * candidate2;
        
        log.info("The computed result is = {}", maxProduct);
        return maxProduct;
    }
}