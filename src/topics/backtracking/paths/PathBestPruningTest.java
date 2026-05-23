package topics.backtracking.paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Shortest Path (Branch & Bound)</h1>
 * <p>
 * Proves that the pruning heuristic safely discards expensive branches without 
 * ever missing the true global minimum.
 * </p>
 */
@DisplayName("PathBestPruning: Shortest Path Optimization (Branch & Bound)")
class PathBestPruningTest {
    private static final Logger log = LoggerFactory.getLogger(PathBestPruningTest.class);

    @Test
    @DisplayName("Should find the exact same optimal shortest path using pruning")
    void testPathBestPruning() {    
        int n = 4;
        int[][] weights = {
            {-1,  7,  9,  4},
            { 3, -1,  2, -1},
            { 4,  3, -1,  8},
            {-1,  9,  9, -1}
        };
        
        PathBestPruning engine = new PathBestPruning(n);
        engine.setSource(0);
        engine.setTarget(3);
        engine.setWeightMatrix(weights);
        
        log.debug("Starting Pruned (Branch & Bound) search...");
        engine.backtracking();
        
        log.debug("Best Cost Found: {}", engine.getBestCost());
        log.debug("Optimal Path: {}", engine.getBestPath());
        
        // The final optimal result MUST be mathematically identical to the un-pruned version.
        assertEquals(4, engine.getBestCost(), "Pruning algorithm incorrectly discarded the optimal path.");    
        assertEquals("NODE0**NODE3**", engine.getBestPath(), "Failed to reconstruct the optimal path sequence.");
    }
}