package topics.backtracking;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Suite for SubsetsGivenSum</h1>
 * <p>
 * Validates the exhaustive backtracking algorithm used to resolve the Subset Sum problem.
 * Employs assertions to verify combinatorial subset generation against 
 * predefined integer arrays and expected sums.
 * </p>
 *
 * @author vicegd
 */
class SubsetsGivenSumTest {
    private static final Logger log = LoggerFactory.getLogger(SubsetsGivenSumTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Subsets Given Sum Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Subsets Given Sum Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> A set consisting of the first 5 natural numbers <code>{1, 2, 3, 4, 5}</code>.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must locate exactly 3 subsets that total 10 
     * (e.g., {1, 4, 5}, {2, 3, 5}, {1, 2, 3, 4}).</p>
     */
    @Test
    void shouldFindThreeSubsetsSummingToTenUsingNaturalNumbers() {
        int[] elements = {1, 2, 3, 4, 5};
        var subsetSum = new SubsetsGivenSum(elements, 10);
        
        subsetSum.solve();
        
        assertEquals(3, subsetSum.getSolutionCount(), 
            "The set {1, 2, 3, 4, 5} must yield exactly 3 distinct subsets that sum to 10.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A set consisting of the first 4 perfect squares <code>{1, 4, 9, 16}</code>.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must locate exactly 1 subset that totals 14 
     * (e.g., {1, 4, 9}).</p>
     */
    @Test
    void shouldFindOneSubsetSummingToFourteenUsingPerfectSquares() {
        int[] elements = {1, 4, 9, 16};
        var subsetSum = new SubsetsGivenSum(elements, 14);
        
        subsetSum.solve();
        
        assertEquals(1, subsetSum.getSolutionCount(), 
            "The set {1, 4, 9, 16} must yield exactly 1 distinct subset that sums to 14.");
    }
}