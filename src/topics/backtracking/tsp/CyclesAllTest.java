package topics.backtracking.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("All Simple Cycles of a Node")
class CyclesAllTest {
    private static final Logger log = LoggerFactory.getLogger(CyclesAllTest.class);

    @Test
    @DisplayName("Should find all 1956 simple cycles originating from node 0")
    void testCycles() {
        int n = 7;
        int source = 0;
        int[][] w = createExampleGraph();

        CyclesAll cycles = new CyclesAll(n, source, w);
        log.debug("Starting exhaustive cycle generation...");
        
        cycles.backtracking();

        log.debug("Total simple cycles found: {}", cycles.getNumberSolutions());
        
        // Validation against the known mathematical result for this specific topology
        assertEquals(1956, cycles.getNumberSolutions());
    }

    public static int[][] createExampleGraph() {
        int n = 7;
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = -1; // Default to no-edge
            }
        }
        
        w[0][1]=27; w[1][0]=27; w[0][2]=11; w[2][0]=11;
        w[0][3]=17; w[3][0]=17; w[0][4]=20; w[4][0]=20;
        w[0][5]=12; w[5][0]=12; w[0][6]=32; w[6][0]=32;
        w[1][2]=15; w[2][1]=15; w[1][3]=26; w[3][1]=26;
        w[1][4]=21; w[4][1]=21; w[1][5]=30; w[5][1]=30;
        w[1][6]=13; w[6][1]=13; w[2][3]=28; w[3][2]=28;
        w[2][4]=14; w[4][2]=14; w[2][5]=23; w[5][2]=23;
        w[2][6]=22; w[6][2]=22; w[3][4]=19; w[4][3]=19;
        w[3][5]=18; w[5][3]=18; w[3][6]=22; w[6][3]=22;
        w[4][5]=16; w[5][4]=16; w[4][6]=23; w[6][4]=23;
        w[5][6]=24; w[6][5]=24;
        
        return w;
    }
}