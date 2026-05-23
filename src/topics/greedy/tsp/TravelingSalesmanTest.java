package topics.greedy.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Traveling Salesman (Nearest Neighbor Heuristic)")
class TravelingSalesmanTest {

    @Test
    @DisplayName("Should return a valid cycle cost")
    void shouldFindValidCycle() {
        // Simple graph with 3 nodes
        int inf = Integer.MAX_VALUE;
        int[][] weights = {
            {inf, 10, 15},
            {10, inf, 20},
            {15, 20, inf}
        };

        TravelingSalesman engine = new TravelingSalesman();
        SalesmanSolution sol = engine.solve(weights, 0);

        // Path: 0 -> 1 (10) -> 2 (20) -> 0 (15) = Cost 45
        assertTrue(sol.totalCost() == 45, "The greedy cost calculation is incorrect.");
    }

    @Test
    @DisplayName("Should find the greedy cycle on a 7-node graph with cost 121")
    void shouldFindGreedyCycleOnSevenNodeGraph() {
        int n = 7;
        int[][] w = new int[n][n];
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

        TravelingSalesman engine = new TravelingSalesman();
        SalesmanSolution sol = engine.solve(w, 0);

        // Path: 0->2(11)->4(14)->5(16)->3(18)->6(22)->1(13)->0(27) = Cost 121
        assertEquals(121, sol.totalCost(), "The greedy cost for the 7-node graph is incorrect.");
        assertArrayEquals(new int[]{0, 2, 4, 5, 3, 6, 1, 0}, sol.path(), "The greedy path for the 7-node graph is incorrect.");
    }
}