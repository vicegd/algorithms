package topics.greedy.knapsack;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for 0/1 Knapsack (Greedy Trap)</h1>
 * <p>
 * These tests explicitly expect the algorithm to fail to find the global optimum, 
 * proving the necessity of Dynamic Programming for this specific problem domain.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("0/1 Knapsack - Greedy Trap Demonstrations")
class Knapsack01Test {
    private static final Logger log = LoggerFactory.getLogger(Knapsack01Test.class);
    private static Knapsack01 knapsack;

    @BeforeAll
    static void setup() {
        knapsack = new Knapsack01();
    }

    @Test
    @DisplayName("Should trap itself on simple bounds (Sub-optimal solution #1)")
    void shouldDemonstrateGreedyTrap1() {
        int[] weights = {5, 4, 3}; 
        int[] values = {60, 40, 30};
        int maxWeight = 7;
        
        // Ratios: Item 0 = 12.0, Item 1 = 10.0, Item 2 = 10.0
        // Greedy takes Item 0 (Weight 5). Remaining capacity: 2.
        // It tries Item 1 (Weight 4). Doesn't fit. Skips.
        // It tries Item 2 (Weight 3). Doesn't fit. Skips.
        // Final greedy value: 60 (Weight used: 5).
        // OPTIMAL value is 70 (Taking items 1 and 2, using exact weight 7).
        
        int[] expectedGreedyFailure = {1, 0, 0}; 
        int[] result = knapsack.fillKnapsackGreedily(maxWeight, weights, values);
        
        log.trace("Demonstrating Trap 1: Greedy yields value 60. Optimal is 70 (via DP).");
        assertArrayEquals(expectedGreedyFailure, result, "Algorithm deviated from the expected greedy failure path.");
    }

    @Test
    @DisplayName("Should trap itself on complex bounds (Sub-optimal solution #2)")
    void shouldDemonstrateGreedyTrap2() {
        // Fixed the array mismatch from the original code (deleted the extra 600 weight)
        int[] weights = {30, 25, 60, 50, 45, 40, 80, 80}; 
        int[] values = {2550, 2050, 4800, 3500, 4050, 2720, 5200, 36000};
        int maxWeight = 55;
        
        // The highest ratio by far is Item 7 (36000/80 = 450.0). But its weight is 80! It doesn't fit in 55.
        // The next best ratio is Item 4 (4050/45 = 90.0). 
        // Greedy takes Item 4. Remaining capacity: 10. No other item fits in 10.
        // Final greedy value: 4050 (Weight used: 45).
        // OPTIMAL value is 4600 (Taking items 0 and 1: 2550 + 2050 = 4600. Exact weight: 55).
        
        int[] expectedGreedyFailure = {0, 0, 0, 0, 1, 0, 0, 0}; 
        int[] result = knapsack.fillKnapsackGreedily(maxWeight, weights, values);
        
        log.trace("Demonstrating Trap 2: Greedy yields value 4050. Optimal is 4600 (via DP).");
        assertArrayEquals(expectedGreedyFailure, result, "Algorithm deviated from the expected greedy failure path.");
    }
}