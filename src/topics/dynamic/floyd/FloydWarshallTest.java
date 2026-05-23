package topics.dynamic.floyd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Floyd-Warshall</h1>
 * <p>
 * Validates the correctness of the minimum cost calculation and path reconstruction.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Floyd-Warshall (All-Pairs Shortest Path)")
class FloydWarshallTest {
    private static final Logger log = LoggerFactory.getLogger(FloydWarshallTest.class);
    private static final int INF = FloydWarshall.INF;

    @Test
    @DisplayName("Should correctly calculate minimum costs in a graph with negative edges")
    void shouldCalculateMinCostWithNegativeEdges() {
        int n = 5;
        int[][] dist = {
            {0, 3, 8, INF, -4},
            {INF, 0, INF, 1, 7},
            {INF, 4, 0, INF, INF},
            {2, INF, -5, 0, INF},
            {INF, INF, INF, 6, 0}
        };
        
        int[][] next = new int[n][n];
        FloydWarshall engine = new FloydWarshall();
        engine.compute(dist, next);

        // Expected cost from NODE2 to NODE4 is 3
        int source = 2;
        int target = 4;
        assertEquals(3, dist[source][target], "Minimum cost calculation failed.");
        
        // Expected path: NODE2 -> NODE1 -> NODE3 -> NODE0 -> NODE4
        String[] nodeNames = {"NODE0", "NODE1", "NODE2", "NODE3", "NODE4"};
        String path = engine.reconstructPath(next, source, target, nodeNames);
        assertEquals("NODE2 -> NODE1 -> NODE3 -> NODE0 -> NODE4", path, "Path reconstruction failed.");
    }
}