package topics.parallel.cuberoot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Parallel Array Transformation</h1>
 * <p>
 * Benchmarks the {@link RecursiveActionComparison} under varying degrees of 
 * parallelism and granularity thresholds to empirically demonstrate Fork/Join scaling.
 * </p>
 *
 * @author vicegd
 */
class RecursiveActionComparisonTest {
    private static final Logger log = LoggerFactory.getLogger(RecursiveActionComparisonTest.class);
    
    // An array of 10,000,000 elements requires roughly 40MB of contiguous heap space.
    private static final int DATASET_SIZE = 10_000_000;
    
    private static int[] sourceData;
    
    /**
     * Initializes the massive dataset once to avoid penalizing individual tests 
     * with random number generation overhead.
     */
    @BeforeAll
    static void setup() {
        log.trace("Recursive Action Comparison Tests - Initializing 10M Element Dataset");
        var random = new Random();
        sourceData = new int[DATASET_SIZE];
        
        for (int i = 0; i < DATASET_SIZE; i++) {
            sourceData[i] = random.nextInt(100);
        }
    }
    
    @AfterAll
    static void teardown() {
        log.trace("Recursive Action Comparison Tests - Context Destroyed");
        // Helps the Garbage Collector reclaim the 40MB array immediately
        sourceData = null; 
    }
    
    /**
     * <p><strong>Scenario:</strong> Low Parallelism (3 threads) with extremely fine granularity (100 elements).</p>
     * <p><strong>Observation:</strong> The overhead of creating thousands of tiny tasks may degrade performance.</p>
     */
    @Test
    void shouldExecuteWithFineGranularity() {
        executeBenchmark(3, 100);
    }
    
    /**
     * <p><strong>Scenario:</strong> High Parallelism (8 threads) with moderate granularity (1,000 elements).</p>
     * <p><strong>Observation:</strong> Often the "sweet spot" for modern multi-core processors.</p>
     */
    @Test
    void shouldExecuteWithModerateGranularity() {
        executeBenchmark(8, 1000);
    }
    
    /**
     * <p><strong>Scenario:</strong> Single Thread (1) with coarse granularity (10,000 elements).</p>
     * <p><strong>Observation:</strong> Acts almost as a sequential baseline.</p>
     */
    @Test
    void shouldExecuteWithCoarseGranularity() {
        executeBenchmark(1, 10000);
    }
    
    /**
     * Helper method to orchestrate the benchmark securely.
     *
     * @param parallelismLevel The number of threads the ForkJoinPool will utilize.
     * @param threshold        The chunk size at which recursion stops.
     */
    private void executeBenchmark(int parallelismLevel, int threshold) {
        // Isolate the test execution by working on a fresh clone of the data
        int[] dataClone = sourceData.clone();
        var task = new RecursiveActionComparison(dataClone, 0, dataClone.length, threshold); 
        
        log.trace("--- Starting Benchmark ---");
        log.trace("Parallelism Level: {}", parallelismLevel); 
        log.trace("Sequential Threshold: {}", threshold); 
        
        Instant start = Instant.now();
        Instant end;
        try (ForkJoinPool pool = new ForkJoinPool(parallelismLevel)) {
            pool.invoke(task);
            end = Instant.now();
        }
        
        log.trace("Elapsed time: {} ms", Duration.between(start, end).toMillis());
        
        // Assert mathematical correctness without crashing memory with string builders
        for (int i = 0; i < dataClone.length; i++) {
            int expectedCubeRoot = (int) Math.cbrt(sourceData[i]);
            assertEquals(expectedCubeRoot, dataClone[i], 
                "Array element at index " + i + " was not transformed correctly.");
        }
    }
}