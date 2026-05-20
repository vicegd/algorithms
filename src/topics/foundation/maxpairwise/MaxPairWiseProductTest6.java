package topics.foundation.maxpairwise;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Performance Validation Suite for Maximum Pairwise Product</h1>
 * <p>
 * Verifies the mathematical integrity of the optimal linear-scan algorithm. 
 * This test evaluates execution time and JVM heap memory consumption, demonstrating 
 * the theoretical and practical limits of an <code>O(N)</code> implementation 
 * compared to previous iterations.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest6 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest6.class);

    /**
     * <p><strong>Scenario:</strong> Computing the maximum pairwise product using a single linear scan.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm correctly identifies 9801. 
     * The execution duration should theoretically represent the fastest processing time among all approaches.</p>
     */
    @Test
    void shouldComputeProductAndBenchmarkResources() {
        log.trace("Executing O(N) Optimal Linear Scan Evaluation");
        
        var calculator = new MaxPairWiseProduct6();
        
        // Benchmark CPU Time
        Instant start = Instant.now();
        long result = calculator.compute();
        Instant end = Instant.now();
        
        assertEquals(9801L, result, 
            "The linear-scan algorithmic extraction must yield exactly 9801.");
            
        // Process Duration
        Duration duration = Duration.between(start, end);
        log.info("Execution completed in {} seconds ({} milliseconds).", 
                 duration.getSeconds(), duration.toMillis());
                 
        // Benchmark Memory Footprint
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Optional: Request garbage collection for a cleaner memory delta
        
        long memoryUsedBytes = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsedMb = memoryUsedBytes / (1024 * 1024);
        
        log.info("Approximate heap memory consumed: {} bytes (~{} MB).", 
                 memoryUsedBytes, memoryUsedMb);
    }
}