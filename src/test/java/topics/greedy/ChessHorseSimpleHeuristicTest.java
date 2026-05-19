package topics.greedy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * <h1>Validation Suite for Knight's Tour (Naive Greedy)</h1>
 * <p>
 * Demonstrates the severe limitations of a poor greedy heuristic by proving 
 * it fails on standard board configurations.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Knight's Tour - Naive First-Fit Heuristic")
class ChessHorseSimpleHeuristicTest {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseSimpleHeuristicTest.class);

    @BeforeAll
    static void setup() {
        log.trace("Initializing Naive Knight's Tour Benchmarks");
    }

    @Test
    @DisplayName("Should trap itself quickly and fail to complete a small 5x5 board")
    void shouldFailOnSmallBoard() {
        int n = 5;
        int[] initialPos = {3, 4}; // Starting near the corner
        
        ChessHorseSimpleHeuristic chess = new ChessHorseSimpleHeuristic(n);
        boolean result = chess.solveTour(initialPos); 
        
        // The assertion expects FALSE because the naive heuristic gets trapped easily
        assertFalse(result, "Algorithm miraculously completed a board using a naive heuristic where it was expected to fail.");
    }
}