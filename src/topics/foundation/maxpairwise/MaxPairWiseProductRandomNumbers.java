package topics.foundation.maxpairwise;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Random Dataset Generator</h1>
 * <p>
 * Generates a synthetic benchmarking dataset of random integers for the Maximum 
 * Pairwise Product algorithmic evaluations. Writes 100,000 random integers 
 * (ranging between 0 and 99) separated by spaces into a designated text file.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Requires linear time to sequentially generate and write exactly <i>N</i> random integers to the output stream.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Operates with a strictly constant memory footprint. Utilizing a <code>BufferedWriter</code> ensures data is flushed to the disk in chunks, avoiding the memory overhead of assembling the entire 100,000-number string in RAM simultaneously.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.foundation.maxpairwise.MaxPairWiseProduct3
 */
public class MaxPairWiseProductRandomNumbers {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductRandomNumbers.class);

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String... args) {
        Path path = Paths.get("src/topics/foundation/maxpairwise/MaxPairWiseProductRandomNumbers.txt");
        Random random = new Random();
        int totalNumbers = 100_000;
        
        log.info("Initiating dataset generation: {} random integers...", totalNumbers);

        // Utilizing a try-with-resources block guarantees that the BufferedWriter 
        // safely closes the file stream and releases system locks, even if an exception occurs.
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < totalNumbers; i++) {
                writer.write(random.nextInt(100) + " ");
            }
            log.info("Dataset successfully generated and securely written to: {}", path.toAbsolutePath());
        } catch (IOException e) {
            log.error("A critical I/O error occurred while attempting to write the random dataset.", e);
        }
    }
}