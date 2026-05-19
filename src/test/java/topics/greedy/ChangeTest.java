package topics.greedy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for Greedy Coin Change</h1>
 * <p>
 * Demonstrates both the success cases of the Greedy algorithm and the mathematical 
 * boundaries where it fails to find the global optimum (The Greedy Trap).
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Coin Change - Greedy Paradigms")
class ChangeTest {
    private static final Logger log = LoggerFactory.getLogger(ChangeTest.class);
    private static Change changeCalculator;

    @BeforeAll
    static void setup() {
        changeCalculator = new Change();
    }

    @Test
    @DisplayName("Should find the mathematically optimal solution for canonical coin sets")
    void shouldFindOptimalSolution() {
        int[] coins = {8, 4, 2, 1}; 
        int amount = 15;
        
        int[] expectedSolution = {1, 1, 1, 1}; // 8 + 4 + 2 + 1 = 15
        int[] result = changeCalculator.calculateCoins(amount, coins);
        
        log.trace("Testing optimal distribution for amount {}", amount);
        assertArrayEquals(expectedSolution, result, "Failed to calculate optimal canonical change.");
    }

    @Test
    @DisplayName("Should fall into the Greedy Trap (Sub-optimal solution #1)")
    void shouldDemonstrateSubOptimalGreedyBehavior1() {
        int[] coins = {200, 100, 50, 20, 2, 1};
        int amount = 60;
        
        // Greedy takes 50x1, then it's forced to take 2x5. Total = 6 coins.
        // Optimal would be 20x3. Total = 3 coins.
        int[] expectedGreedySolution = {0, 0, 1, 0, 5, 0}; 
        int[] result = changeCalculator.calculateCoins(amount, coins);
        
        log.trace("Demonstrating Greedy Trap 1. Target: {}. Greedy used 6 coins. Optimal is 3.", amount);
        assertArrayEquals(expectedGreedySolution, result, "Algorithm behavior deviated from standard Greedy path.");
    }

    @Test
    @DisplayName("Should fall into the Greedy Trap (Sub-optimal solution #2)")
    void shouldDemonstrateSubOptimalGreedyBehavior2() {
        int[] coins = {200, 100, 50, 20, 12, 5, 4, 1};
        int amount = 15;
        
        // Greedy takes 12x1, then 1x3. Total = 4 coins.
        // Optimal would be 5x3. Total = 3 coins.
        int[] expectedGreedySolution = {0, 0, 0, 0, 1, 0, 0, 3}; 
        int[] result = changeCalculator.calculateCoins(amount, coins);
        
        log.trace("Demonstrating Greedy Trap 2. Target: {}. Greedy used 4 coins. Optimal is 3.", amount);
        assertArrayEquals(expectedGreedySolution, result, "Algorithm behavior deviated from standard Greedy path.");
    }
}