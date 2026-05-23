package topics.backtracking.paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Shortest Path (Un-pruned)</h1>
 * <p>
 * Validates that the exhaustive search correctly identifies the shortest path 
 * by evaluating all possible valid simple paths to the target.
 * </p>
 */
@DisplayName("PathBest: Shortest Path Optimization (No Pruning)")
class PathBestTest {
    private static final Logger log = LoggerFactory.getLogger(PathBestTest.class);

    @Test
    @DisplayName("Should find the absolute shortest path by exploring the entire state space")
    void testPathBest() {    
        int n = 4;
        int[][] weights = {
            {-1,  7,  9,  4},
            { 3, -1,  2, -1},
            { 4,  3, -1,  8},
            {-1,  9,  9, -1}
        };
        
        PathBest engine = new PathBest(n);
        engine.setSource(0);
        engine.setTarget(3);
        engine.setWeightMatrix(weights);
        
        log.debug("Starting Un-pruned search...");
        engine.backtracking();
        
        log.debug("Total target reaches explored: {}", engine.getNumberSolutions());
        log.debug("Best Cost Found: {}", engine.getBestCost());
        log.debug("Optimal Path: {}", engine.getBestPath());
        
        // Assertions based on the known graph topology
        assertEquals(3, engine.getNumberSolutions(), "Should have evaluated 3 complete paths to the target.");
        assertEquals(4, engine.getBestCost(), "Failed to find the minimum cost.");    
        assertEquals("NODE0**NODE3**", engine.getBestPath(), "Failed to reconstruct the optimal path sequence.");
    }
}