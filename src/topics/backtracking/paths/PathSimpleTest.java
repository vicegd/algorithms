package topics.backtracking.paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PathSimple: All Simple Paths")
class PathSimpleTest {
    private static final Logger log = LoggerFactory.getLogger(PathSimpleTest.class);
    
    @Test
    @DisplayName("Should find 3 distinct simple paths in the test graph")
    void testPathInput4() {  
        int n = 4;
        int[][] weights = {
            {-1,  7,  9,  4},
            { 3, -1,  2, -1},
            { 4,  3, -1,  8},
            {-1,  9,  9, -1}
        };
        
        PathSimple engine = new PathSimple(n);
        engine.setSource(0);
        engine.setTarget(3);
        engine.setWeightMatrix(weights);
        
        log.debug("WEIGHT MATRIX: {}", engine.writeWeights());
        engine.backtracking();        
        
        log.debug("Total simple paths found: {}", engine.getNumberSolutions());
        assertEquals(3, engine.getNumberSolutions());
    }
}