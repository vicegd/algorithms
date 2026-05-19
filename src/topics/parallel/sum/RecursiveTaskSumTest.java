package topics.parallel.sum;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Parallel Summation</h1>
 * <p>
 * Verifies mathematical reduction correctness and benchmarks execution times 
 * over a highly populated floating-point array using JUnit 5.
 * </p>
 *
 * @author vicegd
 */
class RecursiveTaskSumTest {
    private static final Logger log = LoggerFactory.getLogger(RecursiveTaskSumTest.class);
    
    private static ForkJoinPool pool; 
    private static double[] data; 
  
    /**
     * Provisions concurrent resources and structures an alternate dataset 
     * of 999,999 values prior to executing benchmarks.
     */
    @BeforeAll
    static void setup() {
        log.trace("Recursive Task Sum Tests - Instantiating Context");
        pool = new ForkJoinPool(); 
        data = new double[999_999]; 
        
        // Alternating mathematical formula sign initializer loop
        for (int i = 0; i < data.length; i++) { 
            data[i] = ((i % 2) == 0) ? i : -i;
        }
        
        // Safe stringification via streams guarded under configuration verification check
        if (log.isTraceEnabled()) {
            String initialSequenceDump = Arrays.stream(data)
                    .limit(50) // Safe clamping limit for visualization logs
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            log.trace("Original sequence initialized (Clamped view of first 50 entries): [{}]...", initialSequenceDump);
        }
    }
  
    /**
     * Disposes of hardware execution thread environments gracefully.
     */
    @AfterAll
    static void teardown() {
        log.trace("Recursive Task Sum Tests - Shutting Down Context Pool");
        if (pool != null) {
            pool.shutdown();
        }
    }
  
    /**
     * <p><strong>Scenario:</strong> Accumulating 999,999 alternated float primitives in parallel.</p>
     * <p><strong>Expected Outcome:</strong> Total sum must equate perfectly to 499,999 
     * under a delta margin of <code>1E-3</code> due to floating-point truncation variances.</p>
     */
    @Test
    void shouldAggregateArraySumInParallelCorrectly() {
        var task = new RecursiveTaskSum(data, 0, data.length); 
        
        Instant start = Instant.now();
        double computedResult = pool.invoke(task); 
        Instant end = Instant.now();
        
        log.trace("Parallel Reduction completed in {} ms", Duration.between(start, end).toMillis());
        log.trace("Reduction Mathematical Result: {}", computedResult);
        
        // Assert true reduction logic accuracy using JUnit 5 assertion constraints
        double expectedResult = 499_999.0;
        double precisionDelta = 0.001;
        
        assertEquals(expectedResult, computedResult, precisionDelta,
            "The parallel summation deviated past the safe floating-point precision constraint boundary."); 
    }
}