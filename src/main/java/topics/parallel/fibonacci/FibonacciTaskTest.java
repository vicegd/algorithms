package topics.parallel.fibonacci;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Parallel Fibonacci</h1>
 * <p>
 * Demonstrates the structural speedup achieved by leveraging the Fork/Join pool 
 * compared to the purely sequential execution. Benchmarks execution using the 
 * modern Java Time API.
 * </p>
 *
 * @author vicegd
 */
class FibonacciTaskTest {
    private static final Logger log = LoggerFactory.getLogger(FibonacciTaskTest.class);
    private static ForkJoinPool pool; 
  
    /**
     * Initializes the testing context and provisions the concurrent thread pool.
     */
    @BeforeAll
    static void setup() {
        log.trace("Fibonacci Task Tests - Initializing ForkJoinPool");
        // Provisions a pool with parallelism matching the available processor cores
        pool = new ForkJoinPool(); 
    }
  
    /**
     * Gracefully shuts down the thread pool, releasing OS resources.
     */
    @AfterAll
    static void teardown() {
        log.trace("Fibonacci Task Tests - Shutting Down ForkJoinPool");
        if (pool != null) {
            pool.shutdown();
        }
    }
  
    /**
     * <p><strong>Scenario:</strong> Parallel execution for N=30.</p>
     */
    @Test
    void shouldComputeFibonacciForModerateSizeInParallel() {
        int n = 30;
        var task = new FibonacciTask(n);

        Instant start = Instant.now(); 
        long result = pool.invoke(task); 
        Instant end = Instant.now();
  
        log.trace("Fibonacci problem of size: {}", n);
        log.trace("Computed Result: {}", result);
        log.trace("Elapsed time: {} ms", Duration.between(start, end).toMillis());     
        
        assertEquals(832040L, result, "Fibonacci(30) must strictly equal 832,040");
    }
  
    /**
     * <p><strong>Scenario:</strong> Parallel execution for N=50.</p>
     * <p><strong>Purpose:</strong> Demonstrates the hardware utilization and speedup 
     * across multiple cores compared to the sequential bottleneck.</p>
     */
    @Test
    void shouldComputeFibonacciForLargeSizeInParallel() {
        int n = 50;
        var task = new FibonacciTask(n);
 
        Instant start = Instant.now(); 
        long result = pool.invoke(task); 
        Instant end = Instant.now();
  
        log.trace("Fibonacci problem of size: {}", n);
        log.trace("Computed Result: {}", result);
        log.trace("Elapsed time: {} ms ({} seconds)", 
                  Duration.between(start, end).toMillis(),
                  Duration.between(start, end).getSeconds());     
        
        assertEquals(12586269025L, result, "Fibonacci(50) must strictly equal 12,586,269,025");
    }
}