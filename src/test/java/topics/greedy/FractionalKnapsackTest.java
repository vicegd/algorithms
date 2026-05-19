package topics.greedy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Fractional Knapsack</h1>
 * <p>
 * Ensures the Greedy algorithm correctly calculates ratios, sorts them, 
 * and mathematically fractures the final item to perfectly fill the capacity.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Fractional Knapsack - Greedy Algorithms")
class FractionalKnapsackTest {
    private static final Logger log = LoggerFactory.getLogger(FractionalKnapsackTest.class);
    private static FractionalKnapsack knapsack;

    @BeforeAll
    static void setup() {
        knapsack = new FractionalKnapsack();
    }

    @Test
    @DisplayName("Should mathematically optimize fractional distribution (Case 1)")
    void shouldFindOptimalFractionalDistribution() {
        int[] weights = {10, 20, 30, 40, 50};
        int[] values = {20, 30, 66, 40, 60};
        int maxWeight = 100;

        /* Ratios Breakdown:
         * Item 0: 20/10 = 2.0
         * Item 1: 30/20 = 1.5
         * Item 2: 66/30 = 2.2 (Best)
         * Item 3: 40/40 = 1.0 (Worst)
         * Item 4: 60/50 = 1.2
         * * Execution Order: Item 2 (30kg), Item 0 (10kg), Item 1 (20kg). Used = 60kg. Remaining = 40kg.
         * Next is Item 4 (50kg). We only need 40kg. Fraction = 40/50 = 0.8.
         * Item 3 is ignored (0.0).
         */
        float[] expectedSolution = {1.0f, 1.0f, 1.0f, 0.0f, 0.8f}; 
        
        float[] result = knapsack.fillKnapsack(maxWeight, weights, values);
        
        // Assert Array Equals with a delta for floating point precision
        assertArrayEquals(expectedSolution, result, 0.001f, "Algorithm failed to correctly fracture and allocate items.");
        
        // Calculate and log total value for educational trace
        float totalValue = 0;
        float totalWeight = 0;
        for (int i = 0; i < result.length; i++) {
            totalValue += values[i] * result[i];
            totalWeight += weights[i] * result[i];
        }
        
        log.trace("Final Optimized Knapsack -> Weight: {}/{} | Value: {}", totalWeight, maxWeight, totalValue);
        assertEquals(164.0f, totalValue, 0.001f, "Total extracted value does not match the mathematical maximum.");
    }
}