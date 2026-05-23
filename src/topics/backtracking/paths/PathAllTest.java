package topics.backtracking.paths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PathAll: Non-Simple Paths Matching Exact Cost")
class PathAllTest {
    
    @Test
    @DisplayName("Should find exactly 310,422 paths combining cycles to reach cost 80")
    void testPathInput4() {  
        int n = 4;
        int threshold = 80;
        
        int[][] weights = {
            {-1,  4,  9, -1},
            { 3, -1,  2,  8},
            {-1,  9, -1,  1},
            { 2,  6, -1, -1}
        };
        
        PathAll engine = new PathAll(n, threshold);
        engine.setSource(3);
        engine.setTarget(2);
        engine.setWeightMatrix(weights);
        
        engine.backtracking();
        
        // This is a massive combinatorial explosion due to cyclic permutations!
        assertEquals(310422, engine.getNumberSolutions(), "Combinatorial path logic failed.");
    }
}