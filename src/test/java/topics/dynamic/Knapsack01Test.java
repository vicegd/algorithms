package topics.dynamic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for 0/1 Knapsack</h1>
 * <p>
 * Ensures the Dynamic Programming algorithm correctly identifies the 
 * mathematical maximum values without fractional breaking.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("0/1 Knapsack - Dynamic Programming")
class Knapsack01Test {
    private static final Logger log = LoggerFactory.getLogger(Knapsack01Test.class);
    private static Knapsack01 knapsack;

    @BeforeAll
    static void setup() {
        knapsack = new Knapsack01();
        log.trace("Knapsack01 Validation Suite Initialized");
    }

    @Test
    @DisplayName("Case 1: Standard bounds where Greedy fails")
    void shouldFindOptimalValueWhenGreedyFails() {
        int maxWeight = 7;
        float[] benefits = {12f, 10f, 10f}; 
        int[] weights = {5, 4, 3};          
        
        float result = knapsack.knapsack01(maxWeight, benefits, weights);
        assertEquals(70.0f, result, 0.01f, "Failed to find optimal route over the greedy trap.");
    }

    @Test
    @DisplayName("Case 2: Heavy capacity with varied item spread")
    void shouldHandleHeavyCapacityWithVariedSpread() {
        int maxWeight = 55;
        float[] benefits = {65f, 85f, 82f, 80f, 68f, 70f, 90f, 60f};
        int[] weights = {80, 30, 25, 60, 40, 50, 45, 600};
        
        float result = knapsack.knapsack01(maxWeight, benefits, weights);
        assertEquals(4600.0f, result, 0.01f, "Failed to calculate large capacity correctly.");
    }

    @Test
    @DisplayName("Case 3: Small capacity, highly competitive items")
    void shouldHandleSmallCapacityWithCompetitiveItems() {
        int maxWeight = 6;
        float[] benefits = {2f, 2f, 5f, 2.5f};
        int[] weights = {3, 2, 1, 4};
        
        float result = knapsack.knapsack01(maxWeight, benefits, weights);
        assertEquals(15.0f, result, 0.01f, "Failed to optimize highly competitive lightweight items.");
    }

    @Test
    @DisplayName("Case 4: Exact fit boundaries")
    void shouldHandleExactFitBoundaries() {
        int maxWeight = 10;
        float[] benefits = {8/6f, 1f, 1f};
        int[] weights = {6, 5, 5};
        
        float result = knapsack.knapsack01(maxWeight, benefits, weights);
        assertEquals(10.0f, result, 0.01f, "Failed exact fit boundary logic.");
    }

    @Test
    @DisplayName("Case 5: Fractional benefit values")
    void shouldHandleFractionalBenefitValues() {
        int maxWeight = 10;
        float[] benefits = {2f, 3f, 13/5f, 26/7f, 1f};
        int[] weights = {3, 4, 5, 7, 5};
        
        float result = knapsack.knapsack01(maxWeight, benefits, weights);
        assertEquals(32.0f, result, 0.01f, "Failed to calculate fractional benefits properly.");
    }
}