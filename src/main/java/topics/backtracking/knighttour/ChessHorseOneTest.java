package topics.backtracking.knighttour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Test Suite for ChessHorseOne</h1>
 * <p>
 * Validates the early-exit backtracking algorithm for the Knight's Tour problem.
 * Employs assertions to verify the presence or absence of a valid path 
 * based on varying initial topological conditions.
 * </p>
 *
 * @author vicegd
 */
class ChessHorseOneTest {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseOneTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Chess Horse One Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Chess Horse One Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> Standard 5x5 board starting at the top-left corner.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must quickly locate a valid 
     * topological path and return <code>true</code> without exploring the entire state space.</p>
     */
    @Test
    void shouldLocateSolutionOnStandard5x5Board() {
        var chess = new ChessHorseOne(5, 0, 0);
        chess.solve();
        
        assertTrue(chess.hasFoundSolution(), 
            "A 5x5 board starting at coordinate (0,0) must yield at least one valid path.");
    }
    
    /**
     * <p><strong>Scenario:</strong> 5x5 board starting at an edge coordinate (1,4).</p>
     * <p><strong>Expected Outcome:</strong> Due to mathematical constraints, a knight cannot 
     * complete a full tour from this specific starting point. The algorithm must exhaust the 
     * feasible state space and correctly return <code>false</code>.</p>
     */
    @Test
    void shouldNotFindSolutionFromDeadEndStartingPosition() {
        var chess = new ChessHorseOne(5, 1, 4);
        chess.solve();
        
        assertFalse(chess.hasFoundSolution(), 
            "A 5x5 board starting at coordinate (1,4) represents an unfeasible tour and must yield no solutions.");
    }
}