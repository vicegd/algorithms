package topics.backtracking;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Test Suite for ChessQueensOne</h1>
 * <p>
 * Validates the early-exit backtracking algorithm for the N-Queens problem.
 * Employs assertions to verify the presence or absence of a valid 
 * combinatorial arrangement across various board dimensions.
 * </p>
 *
 * @author vicegd
 */
class ChessQueensOneTest {
    private static final Logger log = LoggerFactory.getLogger(ChessQueensOneTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Chess Queens One Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Chess Queens One Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> A 4x4 chessboard.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must quickly locate a valid 
     * non-threatening arrangement and return <code>true</code>.</p>
     */
    @Test
    void shouldLocateSolutionOn4x4Board() {
        var chess = new ChessQueensOne(4);
        chess.solve();
        
        assertTrue(chess.hasFoundSolution(), 
            "A 4x4 board geometry allows for valid N-Queens arrangements and must yield true.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A 3x3 chessboard.</p>
     * <p><strong>Expected Outcome:</strong> It is geometrically impossible to place 
     * 3 non-threatening queens on a 3x3 board. The algorithm must exhaust the state 
     * space and correctly return <code>false</code>.</p>
     */
    @Test
    void shouldNotFindSolutionOn3x3Board() {
        var chess = new ChessQueensOne(3);
        chess.solve();
        
        assertFalse(chess.hasFoundSolution(), 
            "A 3x3 board lacks the necessary geometry for a valid N-Queens arrangement and must yield false.");
    }
}