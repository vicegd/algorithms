package topics.branchandbound;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <h1>Test Suite for the 8-Puzzle Solver</h1>
 * <p>
 * Validates the resolution algorithms, state pruning mechanisms, and bounding 
 * heuristics across solvable and mathematically unsolvable board configurations.
 * </p>
 *
 * @author vicegd
 */
class EightPuzzleTest {
    private static final Logger log = LoggerFactory.getLogger(EightPuzzleTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Eight Puzzle Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Eight Puzzle Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> A moderately scrambled board configuration.</p>
     * <p><strong>Expected Outcome:</strong> The Branch and Bound algorithm utilizing 
     * the Manhattan distance heuristic must locate the final configuration (heuristic = 0).</p>
     */
    @Test
    void shouldSolvePuzzleUsingManhattanHeuristic() {
        int[] board = {2, 3, 6, 1, 5, 4, 9, 7, 8};
        
        var puzzle = new EightPuzzle(HeuristicType.MANHATTAN, board); 
        puzzle.branchAndBound(puzzle.getRootNode()); 
        puzzle.printSolutionTrace(); 
        
        int finalHeuristic = puzzle.getBestNode().getHeuristicValue();
        assertEquals(0, finalHeuristic, 
            "The Manhattan heuristic evaluation of the best node must yield exactly 0 indicating a solved state.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A highly scrambled board configuration.</p>
     * <p><strong>Expected Outcome:</strong> The Branch and Bound algorithm utilizing 
     * the Misplaced Tiles heuristic must successfully locate the final configuration.</p>
     */
    @Test
    void shouldSolvePuzzleUsingMisplacedTilesHeuristic() {
        int[] board = {2, 3, 6, 1, 5, 4, 9, 7, 8};
        
        var puzzle = new EightPuzzle(HeuristicType.WRONG_PLACE, board); 
        puzzle.branchAndBound(puzzle.getRootNode()); 
        puzzle.printSolutionTrace(); 
        
        int finalHeuristic = puzzle.getBestNode().getHeuristicValue();
        assertEquals(0, finalHeuristic, 
            "The Wrong Place heuristic evaluation of the best node must yield exactly 0 indicating a solved state.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A board that is inherently in the target state.</p>
     * <p><strong>Expected Outcome:</strong> The algorithm must immediately identify the 
     * target geometry and return the baseline heuristic without further evaluation.</p>
     */
    @Test
    void shouldIdentifyAlreadySolvedPuzzle() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        var puzzle = new EightPuzzle(HeuristicType.MANHATTAN, board); 
        puzzle.branchAndBound(puzzle.getRootNode()); 
        puzzle.printSolutionTrace(); 
        
        int finalHeuristic = puzzle.getBestNode().getHeuristicValue();
        assertEquals(0, finalHeuristic, 
            "A pre-solved board geometry must be identified immediately, yielding a heuristic of 0.");
    }
    
    /**
     * <p><strong>Scenario:</strong> A mathematically scrambled board containing an odd 
     * inversion parity, making it unsolvable.</p>
     * <p><strong>Expected Outcome:</strong> The pruning function must discard the entire 
     * execution tree. Attempting to extract the best node subsequently triggers a Null Pointer Exception.</p>
     */
    @Test
    void shouldRejectUnsolvablePuzzleBoard() {
        int[] board = {9, 3, 7, 6, 5, 4, 8, 2, 1};
        
        var puzzle = new EightPuzzle(HeuristicType.MANHATTAN, board); 
        puzzle.branchAndBound(puzzle.getRootNode()); 
        puzzle.printSolutionTrace(); 
        
        // Verifies the operational artifact of the framework when no valid node is mapped.
        assertThrows(NullPointerException.class, () -> {
            puzzle.getBestNode().getHeuristicValue();
        }, "Querying the heuristic of an unsolvable board must throw a NullPointerException as the tree is heavily pruned.");
    }
}