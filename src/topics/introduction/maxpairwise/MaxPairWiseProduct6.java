package topics.introduction.maxpairwise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Maximum Pairwise Product (Optimal Linear Scan)</h1>
 * <p>
 * Evaluates the maximum pairwise product from a dataset of integers loaded 
 * from an external text file. This implementation represents the mathematically 
 * optimal approach, locating the two maximum values in a single pass without 
 * the overhead of sorting the entire collection.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Requires only a single linear traversal of the dataset to identify the two largest elements. This provides a massive performance leap over <code>O(N log N)</code> sorting for exceptionally large datasets.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Requires linear memory to store the dataset in the collection. <i>(Note: This could be optimized to <code>O(1)</code> if the file was streamed and evaluated on-the-fly rather than stored).</i></li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct6 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct6.class);
    private final List<Integer> numbers = new ArrayList<>();
    
    /**
     * Initializes the computational context by reading a single-line dataset of 
     * space-separated integers from the filesystem into memory.
     */
    public MaxPairWiseProduct6() {
        Path path = Paths.get("src/topics/introduction/maxpairwise/MaxPairWiseProductRandomNumbers.txt");
        
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
     * Computes the maximum mathematical product natively by tracking the top two 
     * largest integers during a single iteration sequence.
     *
     * @return The highest mathematical pairwise product found in the dataset.
     * @throws IllegalStateException if the dataset contains fewer than two elements.
     */
    public long compute() {
        if (numbers.size() < 2) {
            throw new IllegalStateException("Dataset must contain at least two numbers to compute a pairwise product.");
        }

        long max1 = -1; // The absolute maximum
        long max2 = -1; // The second maximum

        // Single linear pass to discover the two largest candidates
        for (int currentNumber : numbers) {
            if (currentNumber > max1) {
                max2 = max1;         // Demote previous max1 to max2
                max1 = currentNumber; // Promote current to max1
            } else if (currentNumber > max2) {
                max2 = currentNumber; // Only update max2
            }
        }

        long maxProduct = max1 * max2;
        
        log.info("The computed result is = {}", maxProduct);
        return maxProduct;
    }
}