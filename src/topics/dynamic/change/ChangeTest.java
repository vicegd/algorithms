package topics.dynamic.change;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Dynamic Programming Coin Change</h1>
 * <p>
 * Demonstrates the structural flaws of the Greedy approach and proves 
 * the mathematical optimality of the Dynamic Programming transition matrix.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Coin Change - Dynamic Programming Optimization")
class ChangeTest {
    private static Change changeCalculator;

    @BeforeAll
    static void setup() {
        changeCalculator = new Change();
    }

    @Test
    @DisplayName("Should find optimal combination when Greedy fails (Case 1)")
    void shouldFindOptimalCombinationWhenGreedyFails() {
        int[] coins = {1, 6, 4};
        assertEquals(4, changeCalculator.change(15, coins), "DP failed to optimize over the greedy path.");
    }

    @Test
    @DisplayName("Should maintain correctness when Greedy is naturally optimal (Case 2)")
    void shouldMaintainCorrectnessWhenGreedyIsOptimal() {
        int[] coins = {1, 2, 20, 50, 100, 200};
        assertEquals(3, changeCalculator.change(60, coins), "DP failed to match the standard optimal path.");
    }

    @Test
    @DisplayName("Should navigate complex sub-optimal traps (Case 3)")
    void shouldNavigateComplexSuboptimalTraps() {
        int[] coins = {1, 4, 5, 12, 20, 50, 100, 200};
        assertEquals(3, changeCalculator.change(15, coins), "DP fell into the mathematical greedy trap.");
    }
}