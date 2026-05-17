package topics.backtracking;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Suite for Permutations Generator</h1>
 * <p>
 * Validates the exhaustive backtracking permutation algorithm. 
 * Employs assertions to verify that the generated count matches theoretical factorial 
 * math expectations.
 * </p>
 *
 * @author vicegd
 */
class PermutationsTest {
    private static final Logger log = LoggerFactory.getLogger(PermutationsTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Permutations Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Permutations Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> Generating permutations for a set of 4 elements.</p>
     * <p><strong>Expected Outcome:</strong> 4! equals exactly 24 configurations.</p>
     */
    @Test
    void shouldGenerateExactlyTwentyFourPermutationsForFourElements() {
        var permutations = new Permutations(4);
        permutations.generateAll();
        
        assertEquals(24, permutations.getPermutationCount(), 
            "The number of permutations for N=4 must be 4! (24).");
    }
    
    /**
     * <p><strong>Scenario:</strong> Generating permutations for a set of 5 elements.</p>
     * <p><strong>Expected Outcome:</strong> 5! equals exactly 120 configurations.</p>
     */
    @Test
    void shouldGenerateExactlyOneHundredTwentyPermutationsForFiveElements() {
        var permutations = new Permutations(5);
        permutations.generateAll();
        
        assertEquals(120, permutations.getPermutationCount(), 
            "The number of permutations for N=5 must be 5! (120).");
    }
}