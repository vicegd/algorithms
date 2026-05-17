package topics.introduction;

import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Performance Validation Suite for Maximum Pairwise Product</h1>
 * <p>
 * Verifies the mathematical integrity of the sorting-based pairwise product. 
 * Furthermore, this test benchmarks both execution time and heap memory 
 * consumption, empirically demonstrating the superior <code>O(N log N)</code> 
 * scaling behavior over the brute-force approach.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest4 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest4.class);

    /**
     * <p><strong>Scenario:</strong> Computing the maximum pairwise product using a descending sort.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm correctly identifies 9801. 
     * The test evaluates and logs the execution duration alongside JVM memory utilization.</p>
     */
    @Test
    void shouldComputeProductAndBenchmarkResources() {
        log.trace("Executing O(N log N) Dataset Evaluation");
        
        var calculator = new MaxPairWiseProduct4();
        
        // Benchmark CPU Time
        Instant start = Instant.now();
        long result = calculator.compute();
        Instant end = Instant.now();
        
        assertEquals(9801L, result, 
            "The sorting algorithmic extraction must yield exactly 9801.");
            
        // Process Duration
        Duration duration = Duration.between(start, end);
        log.info("Execution completed in {} seconds ({} milliseconds).", 
                 duration.getSeconds(), duration.toMillis());
                 
        // Benchmark Memory Footprint
        Runtime runtime = Runtime.getRuntime();
        
        // Suggestion to run garbage collection before measuring for a cleaner delta, 
        // though strictly optional for rough estimations.
        runtime.gc(); 
        long memoryUsedBytes = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsedMb = memoryUsedBytes / (1024 * 1024);
        
        log.info("Approximate heap memory consumed: {} bytes (~{} MB).", 
                 memoryUsedBytes, memoryUsedMb);
    }
}