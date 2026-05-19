package topics.parallel.square;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Parallel Squaring</h1>
 * <p>
 * Verifies the mathematical truth and side-effect consistency of 
 * in-place parallel array transformations using JUnit 5.
 * </p>
 *
 * @author vicegd
 */
class RecursiveActionSquareTest {
    private static final Logger log = LoggerFactory.getLogger(RecursiveActionSquareTest.class);
    
    private static ForkJoinPool pool; 
    private static int[] data; 
    private static int[] srcData; 
    
    /**
     * Provisions resources and generates raw mock sequences prior to evaluation.
     */
    @BeforeAll
    static void setup() {
        log.trace("Recursive Action Square Tests - Instantiating Testing Context");
        var random = new Random();
        pool = new ForkJoinPool(); 
        data = new int[1000]; 
        
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }
        
        // Modernized logging: Converts the array to a space-separated String via Streams 
        // entirely avoiding manual iteration and raw string concatenations.
        if (log.isTraceEnabled()) {
            String sequenceDump = Arrays.stream(data)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            log.trace("Original sequence initialized: [{}]", sequenceDump);
        }
        
        srcData = data.clone();
    }
  
    /**
     * Disposes of operational hardware thread pools to guarantee clean environment teardown.
     */
    @AfterAll
    static void teardown() {
        log.trace("Recursive Action Square Tests - Shutting Down Resources");
        if (pool != null) {
            pool.shutdown();
        }
    }
  
    /**
     * <p><strong>Scenario:</strong> Processing an array of 1,000 integers through Fork/Join decomposition.</p>
     * <p><strong>Expected Outcome:</strong> Every discrete index must exactly match the square of its original value.</p>
     */
    @Test
    void shouldComputeSquaresInParallelCorrectly() {
        var task = new RecursiveActionSquare(data, 0, data.length); 
        
        pool.invoke(task); 
        
        log.trace("Parallel transformation task execution finalized.");
        
        // Assert algorithmic and mathematical integrity
        for (int i = 0; i < data.length; i++) {
            int expectedSquare = srcData[i] * srcData[i];
            assertEquals(expectedSquare, data[i], 
                "Inversion anomaly discovered at index: " + i);
        }

        if (log.isTraceEnabled()) {
            String transformedDump = Arrays.stream(data)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
            log.trace("Transformed structural sequence: [{}]", transformedDump);
        }
    }
}