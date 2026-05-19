package topics.backtracking.knighttour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Suite for ChessHorseAll</h1>
 * <p>
 * Validates the exhaustive backtracking algorithm for the Knight's Tour problem.
 * Employs assertions to verify path generation counts across different
 * board configurations and initial states.
 * </p>
 * * @author vicegd
 */
class ChessHorseAllTest {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseAllTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Chess Horse All Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Chess Horse All Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> Standard 5x5 board starting at the top-left corner.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must traverse the entire state space
     * and discover exactly 304 valid topological paths.</p>
     */
    @Test
    void shouldFindAllSolutionsOnStandard5x5Board() {
        var chess = new ChessHorseAll(5, 0, 0);
        chess.solve();
        
        assertEquals(304, chess.getSolutionCount(), 
            "A 5x5 board starting at coordinate (0,0) must yield exactly 304 valid paths.");
    }
    
    /**
     * <p><strong>Scenario:</strong> 5x5 board starting at an edge coordinate (1,4).</p>
     * <p><strong>Expected Outcome:</strong> Due to mathematical constraints, a knight cannot 
     * complete a full tour from this specific starting point. The algorithm must safely 
     * terminate with 0 solutions.</p>
     */
    @Test
    void shouldFindNoSolutionsFromDeadEndStartingPosition() {
        var chess = new ChessHorseAll(5, 1, 4);
        chess.solve();
        
        assertEquals(0, chess.getSolutionCount(), 
            "A 5x5 board starting at coordinate (1,4) represents an unfeasible tour and must yield 0 solutions.");
    }
}