package topics.dynamic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Combinations</h1>
 * <p>
 * Verifies the mathematical accuracy of the combination calculations and 
 * highlights the extreme performance disparity between Dynamic Programming 
 * and Naive Recursion.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Combinations - Dynamic Programming vs Recursion")
class CombinationsTest {
    private static final Logger log = LoggerFactory.getLogger(CombinationsTest.class);
    private static Combinations comb;

    @BeforeAll
    static void setup() {
        comb = new Combinations();
        log.trace("Combinations Validation Suite Initialized");
    }

    @Test
    @DisplayName("DP: Standard deck of cards (52 choose 5)")
    void shouldCalculateStandardDeckCombinations() {
        assertEquals(2598960L, comb.combinationsDP(52, 5), "Failed to calculate standard 52C5 combination.");
    }

    @Test
    @DisplayName("DP: Large dataset calculation (100 choose 15)")
    void shouldCalculateLargeScaleCombinations() {
        // DP resolves this instantly
        assertEquals(253338471349988640L, comb.combinationsDP(100, 15), "Failed to scale to large datasets.");
    }

    @Test
    @DisplayName("DP: Small dataset for matrix logging (9 choose 5)")
    void shouldCalculateSmallScaleCombinations() {
        assertEquals(126L, comb.combinationsDP(9, 5), "Failed to calculate small scale combination.");
    }

    @Test
    @DisplayName("Recursion: Noticeable lag on moderate datasets (30 choose 15)")
    void shouldDemonstrateRecursiveLagOnModerateDatasets() {
        long start = System.currentTimeMillis();
        long result = comb.combinationsRecursive(30, 15);
        long end = System.currentTimeMillis();
        
        log.debug("Recursive C(30, 15) took {} ms", (end - start));
        assertEquals(155117520L, result, "Recursive method failed to calculate the correct result.");
    }
}