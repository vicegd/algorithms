package topics.backtracking;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Suite for ChessQueensAll</h1>
 * <p>
 * Validates the exhaustive backtracking algorithm for the N-Queens problem.
 * Employs assertions to verify the exact number of valid combinatorial
 * arrangements across various board dimensions.
 * </p>
 *
 * @author vicegd
 */
class ChessQueensAllTest {
    private static final Logger log = LoggerFactory.getLogger(ChessQueensAllTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Chess Queens All Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Chess Queens All Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> A 4x4 chessboard.</p>
     * <p><strong>Expected Outcome:</strong> Mathematical proofs dictate exactly 
     * 2 valid non-threatening arrangements for 4 queens.</p>
     */
    @Test
    void shouldFindTwoSolutionsOn4x4Board() {
        var chess = new ChessQueensAll(4);
        chess.solve();
        
        assertEquals(2, chess.getSolutionCount(), 
            "A 4x4 board must yield exactly 2 valid N-Queens arrangements.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A 5x5 chessboard.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must traverse the state 
     * space and discover exactly 10 valid arrangements.</p>
     */
    @Test
    void shouldFindTenSolutionsOn5x5Board() {
        var chess = new ChessQueensAll(5);
        chess.solve();
        
        assertEquals(10, chess.getSolutionCount(), 
            "A 5x5 board must yield exactly 10 valid N-Queens arrangements.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A 3x3 chessboard.</p>
     * <p><strong>Expected Outcome:</strong> It is geometrically impossible to place 
     * 3 non-threatening queens on a 3x3 board. The result must be 0.</p>
     */
    @Test
    void shouldFindNoSolutionsOn3x3Board() {
        var chess = new ChessQueensAll(3);
        chess.solve();
        
        assertEquals(0, chess.getSolutionCount(), 
            "A 3x3 board lacks the necessary geometry for a valid N-Queens arrangement and must yield 0 solutions.");
    }
}