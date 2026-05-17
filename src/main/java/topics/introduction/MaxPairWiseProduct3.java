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

/**
 * <h1>Maximum Pairwise Product (Brute-Force File Evaluation)</h1>
 * <p>
 * Evaluates the maximum pairwise product from a dataset of integers loaded 
 * dynamically from an external text file. This specific implementation utilizes 
 * a <strong>Naive Combinatorial Approach</strong> to evaluate all possible pairs.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N&sup2;)</code> - The nested loop structure forces the algorithm to multiply every element against every other element. As the dataset scales, the total number of operations grows quadratically, demonstrating a severe performance bottleneck.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Requires linear memory allocation to store the entire dataset within a dynamic list structure during execution.</li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct3 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct3.class);
    private final List<Integer> numbers = new ArrayList<>();
    
    /**
     * Initializes the computational context by reading a single-line dataset of 
     * space-separated integers from the filesystem into memory.
     */
    public MaxPairWiseProduct3() {
        Path path = Paths.get("src/main/java/topics/introduction/MaxPairWiseProductRandomNumbers.txt");
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            if (line != null) {
                for (String number : line.split(" ")) {
                    numbers.add(Integer.valueOf(number));
                }
            }
        } catch (IOException e) {
            log.error("Failed to load the random numbers dataset. Verify the file path and permissions.", e);
        }
    }
    
    /**
     * Computes the mathematical product iteratively by comparing every unique pair.
     * <p>
     * <strong>Architectural Note:</strong> A strict 64-bit cast is applied during the 
     * multiplication step <code>(long)</code> to prevent intermediate 32-bit arithmetic 
     * overflow prior to assignment.
     * </p>
     *
     * @return The highest mathematical pairwise product found in the dataset.
     */
    public long compute() {
        long maxProduct = 0;
        
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                // Ensure we do not multiply the exact same index entry against itself
                if (i != j) {
                    // Explicit cast to long prevents 32-bit overflow during multiplication
                    long result = (long) numbers.get(i) * numbers.get(j);
                    
                    if (result > maxProduct) {
                        maxProduct = result;
                    }
                }
            }
        }
        
        log.info("The computed result is = {}", maxProduct);
        return maxProduct;
    }
}