package topics.greedy.plumber;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Multi-Plumber Scheduling</h1>
 * <p>
 * Contrasts greedy workload distribution against randomized chaos.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Multi-Plumber Scheduling (Greedy Load Balancing)")
class MultiPlumberTest {
    private static final Logger log = LoggerFactory.getLogger(MultiPlumberTest.class);
    private static MultiPlumber multiPlumber;

    @BeforeAll
    static void setup() {
        multiPlumber = new MultiPlumber();
    }

    @Test
    @DisplayName("Should successfully calculate a sub-optimal random distribution")
    void shouldCalculateSubOptimalRandomAssignment() {
        int[] tasks = {2, 5, 4, 8, 6, 7, 3}; 
        int numPlumbers = 3;
        
        int result = multiPlumber.calculateRandomWaitTime(tasks, numPlumbers);
        log.trace("Total global waiting time (Random): {}", result);
        
        // While random, it will practically always be worse or equal to the optimum (51)
        assertTrue(result >= 51, "Random assignment somehow beat the mathematical optimum.");
    }

    @Test
    @DisplayName("Should rigorously calculate the optimal greedy distribution")
    void shouldCalculateOptimalRoundRobinAssignment() {
        int[] tasks = {2, 5, 4, 8, 6, 7, 3}; 
        int numPlumbers = 3;
        
        // The mathematical optimum calculation:
        // Sorted: 2, 3, 4, 5, 6, 7, 8
        // Plumber 0: [2, 5, 8] -> Cascades: 2 + 7 + 15 = 24
        // Plumber 1: [3, 6]    -> Cascades: 3 + 9 = 12
        // Plumber 2: [4, 7]    -> Cascades: 4 + 11 = 15
        // Global Total: 24 + 12 + 15 = 51.
        
        int result = multiPlumber.calculateOptimalWaitTime(tasks, numPlumbers);
        
        assertEquals(51, result, "The Greedy Round-Robin assignment failed to reach the optimal sum.");
    }
}