package topics.greedy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Knight's Tour (Greedy)</h1>
 * <p>
 * Evaluates Warnsdorff's heuristic on scenarios that are perfectly 
 * solvable by the algorithm, and scenarios where the greedy trap triggers a dead end.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Knight's Tour - Warnsdorff's Heuristic")
class ChessHorseTest {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseTest.class);

    @BeforeAll
    static void setup() {
        log.trace("Initializing Knight's Tour Benchmarks");
    }

    @Test
    @DisplayName("Should hit a dead end and fail to complete the 5x5 board")
    void shouldFailOnSpecificBoardAndStart() {
        int n = 5;
        int[] initialPos = {3, 4}; // Starting near the corner
        
        ChessHorse chess = new ChessHorse(n);
        boolean result = chess.solveTour(initialPos); 
        
        assertFalse(result, "Algorithm unexpectedly completed a mathematically unresolvable greedy path.");
    }

    @Test
    @DisplayName("Should successfully complete the full 8x8 chessboard")
    void shouldCompleteFullChessboard() {
        int n = 8;
        int[] initialPos = {1, 3}; 
        
        ChessHorse chess = new ChessHorse(n);
        boolean result = chess.solveTour(initialPos); 
        
        assertTrue(result, "Algorithm failed to complete a standard solvable 8x8 tour.");
    }
}