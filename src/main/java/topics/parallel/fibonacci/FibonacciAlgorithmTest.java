package topics.parallel.fibonacci;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Naive Fibonacci</h1>
 * <p>
 * Demonstrates both the functional accuracy and the severe performance 
 * degradation of an O(2^N) algorithm. Benchmarking utilizes modern Java Time API.
 * </p>
 *
 * @author vicegd
 */
class FibonacciAlgorithmTest {
    private static final Logger log = LoggerFactory.getLogger(FibonacciAlgorithmTest.class);
  
    /**
     * Initializes the context prior to executing validations.
     */
    @BeforeAll
    static void setup() {
        log.trace("Fibonacci Algorithm Tests - Initializing Context");
    }
  
    /**
     * Cleans up resources after validations complete.
     */
    @AfterAll
    static void teardown() {
        log.trace("Fibonacci Algorithm Tests - Tearing Down Context");
    }
  
    /**
     * <p><strong>Scenario:</strong> Computes Fibonacci for N=30.</p>
     * <p><strong>Purpose:</strong> Executes in a reasonable timeframe (milliseconds) 
     * to serve as a fast functional verification.</p>
     */
    @Test
    void shouldComputeFibonacciForModerateSize() {
        int n = 30;
        var calculator = new FibonacciAlgorithm(n);
        
        Instant start = Instant.now(); 
        long result = calculator.solve();   
        Instant end = Instant.now();
      
        log.trace("Fibonacci problem of size: {}", n);
        log.trace("Computed Result: {}", result);
        log.trace("Elapsed time: {} ms", Duration.between(start, end).toMillis()); 
        
        assertEquals(832040L, result, "Fibonacci(30) must strictly equal 832,040");
    }
  
    /**
     * <p><strong>Scenario:</strong> Computes Fibonacci for N=50.</p>
     * <p><strong>Purpose:</strong> Demonstrates the severe bottleneck of exponential complexity. 
     * <em>Warning: This test will block the CPU for a significant duration on a single thread. 
     * In an automated CI/CD environment, consider adding @Disabled to prevent pipeline freezing.</em></p>
     */
    @Test
    void shouldComputeFibonacciForLargeSizeAndDemonstrateBottleneck() {
        int n = 50;
        var calculator = new FibonacciAlgorithm(n);
         
        Instant start = Instant.now(); 
        long result = calculator.solve();   
        Instant end = Instant.now();
       
        log.trace("Fibonacci problem of size: {}", n);
        log.trace("Computed Result: {}", result);
        log.trace("Elapsed time: {} ms ({} seconds)", 
                  Duration.between(start, end).toMillis(), 
                  Duration.between(start, end).getSeconds()); 
        
        assertEquals(12586269025L, result, "Fibonacci(50) must strictly equal 12,586,269,025");
    }
}