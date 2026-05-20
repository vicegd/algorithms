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
 * Verifies the mathematical integrity of the ascending-sort pairwise product. 
 * This test continues the benchmarking methodology, evaluating both execution 
 * time and JVM heap memory consumption to demonstrate efficiency.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest5 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest5.class);

    /**
     * <p><strong>Scenario:</strong> Computing the maximum pairwise product using an ascending sort.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm correctly identifies 9801. 
     * The test evaluates and logs the execution duration alongside JVM memory utilization.</p>
     */
    @Test
    void shouldComputeProductAndBenchmarkResources() {
        log.trace("Executing O(N log N) Ascending Sort Evaluation");
        
        var calculator = new MaxPairWiseProduct5();
        
        // Benchmark CPU Time
        Instant start = Instant.now();
        long result = calculator.compute();
        Instant end = Instant.now();
        
        assertEquals(9801L, result, 
            "The ascending-sort algorithmic extraction must yield exactly 9801.");
            
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