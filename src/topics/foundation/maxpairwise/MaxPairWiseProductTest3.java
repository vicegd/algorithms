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
 * Verifies both the mathematical integrity of the pairwise product and the 
 * real-world wall-clock execution time. By measuring the duration, this test 
 * empirically demonstrates the scaling limitations of an <code>O(N&sup2;)</code> 
 * algorithm against file-based datasets.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest3 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest3.class);

    /**
     * <p><strong>Scenario:</strong> Computing the maximum pairwise product from a file-loaded dataset.</p>
     * <p><strong>Expected Outcome:</strong> The sequential brute-force search correctly identifies 
     * 9801 as the maximum product. The test also benchmarks the execution duration.</p>
     */
    @Test
    void shouldComputeProductAndBenchmarkDuration() {
        log.trace("Executing Brute-Force Dataset Evaluation");
        
        var calculator = new MaxPairWiseProduct3();
        
        // Benchmark Initialization
        Instant start = Instant.now(); 
        
        long result = calculator.compute();
        
        // Benchmark Termination
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        
        assertEquals(9801L, result, 
            "The algorithmic extraction from the text file must yield exactly 9801.");
            
        log.info("The O(N²) algorithmic execution completed in {} seconds ({} milliseconds).", 
                 duration.getSeconds(), duration.toMillis());
    }
}