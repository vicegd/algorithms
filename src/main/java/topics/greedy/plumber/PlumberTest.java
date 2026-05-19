package topics.greedy.plumber;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Single-Plumber Scheduling</h1>
 * <p>
 * Demonstrates how execution order impacts cumulative waiting times, proving 
 * that the Shortest Processing Time (SPT) strategy yields the mathematical minimum.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Plumber Problem - Shortest Job First (Greedy)")
class PlumberTest {
    private static final Logger log = LoggerFactory.getLogger(PlumberTest.class);

    @BeforeAll
    static void setup() {
        log.trace("Initializing Plumber Scheduling Benchmarks");
    }

    @Test
    @DisplayName("Should calculate total waiting time for a random sub-optimal order")
    void shouldCalculateWaitingTimeRandomOrder() {
        int[] tasks = {2, 5, 4, 8, 6, 7, 3}; 
        Plumber plumber = new Plumber(tasks);
        
        int result = plumber.getTotalTimeOfWait();
        assertEquals(131, result, "Failed to calculate cumulative wait for random order.");
    }

    @Test
    @DisplayName("Should calculate the theoretical optimum natively using greedy sorting")
    void shouldCalculateOptimalWaitingTimeDirectly() {
        int[] tasks = {2, 5, 4, 8, 6, 7, 3}; // Random array
        Plumber plumber = new Plumber(tasks);
        
        // Tests the internal getOptimalTotalTimeOfWait method which sorts it automatically
        int result = plumber.getOptimalTotalTimeOfWait();
        assertEquals(112, result, "Greedy optimization method failed to reach the optimal sum.");
    }

    @Test
    @DisplayName("Should calculate total waiting time for a pre-sorted optimal order")
    void shouldCalculateWaitingTimeSmallestFirst() {
        int[] tasks = {2, 3, 4, 5, 6, 7, 8}; // Best case scenario
        Plumber plumber = new Plumber(tasks);
        
        int result = plumber.getTotalTimeOfWait();
        assertEquals(112, result, "Failed to calculate cumulative wait for pre-sorted optimal order.");
    }

    @Test
    @DisplayName("Should calculate total waiting time for the worst-case order")
    void shouldCalculateWaitingTimeBiggestFirst() {
        int[] tasks = {8, 7, 6, 5, 4, 3, 2, 1}; // Worst case scenario
        Plumber plumber = new Plumber(tasks);
        
        int result = plumber.getTotalTimeOfWait();
        assertEquals(204, result, "Failed to calculate cumulative wait for worst-case order.");
    }
}