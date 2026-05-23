package topics.backtracking.paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PathWorst: Longest Simple Path")
class PathWorstTest {
    
    @Test
    @DisplayName("Should find the absolute longest simple path in the graph")
    void testPathWorst() {    
        int[][] weights = {
            {-1,  7,  9,  4},
            { 3, -1,  2, -1},
            { 4,  3, -1,  8},
            {-1,  9,  9, -1}
        };
        
        PathWorst engine = new PathWorst(4);
        engine.setSource(0);
        engine.setTarget(3);
        engine.setWeightMatrix(weights);
        engine.backtracking();
        
        assertEquals(3, engine.getNumberSolutions());
        // Max cost path: 0 -> 1 (7) -> 2 (2) -> 3 (8) = 17
        assertEquals(17, engine.getWorstCost());  
        assertEquals("NODE0**NODE1**NODE2**NODE3**", engine.getWorstPath());
    }
}