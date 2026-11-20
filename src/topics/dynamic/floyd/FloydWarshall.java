package topics.dynamic.floyd;

/**
 * <h1>Floyd-Warshall (All-Pairs Shortest Path)</h1>
 * <p>
 * Computes the shortest paths between all pairs of nodes in a directed graph 
 * using Dynamic Programming.
 * </p>
 *
 * <h2>Relaxation Process</h2>
 * <p>
 * The algorithm performs <code>n</code> iterations. In each iteration <code>k</code>, 
 * it checks for every pair <code>(i, j)</code> if passing through node <code>k</code> 
 * offers a shorter path than the one currently known.
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O(N³)</li>
 * <li><strong>Space Complexity:</strong> O(N²)</li>
 * </ul>
 *
 * @author vicegd
 */
public class FloydWarshall { 
    // Constant representing "Infinity" to avoid integer overflow during additions
    public static final int INF = Integer.MAX_VALUE / 2;

    /**
     * Executes the Floyd-Warshall algorithm.
     * @param dist Distance matrix. Will be updated with minimum path costs.
     * @param next Predecessor matrix to reconstruct the paths.
     */
    public void compute(int[][] dist, int[][] next) {
        int n = dist.length;

        // Initialize predecessor matrix: -1 indicates a direct edge
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = -1;
            }
        }

        // Dynamic Programming core: Relaxation through intermediate node k
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = k; // Update the intermediate node
                    }
                }
            }
        }
    }

    /**
     * Recursively reconstructs the shortest path between two nodes.
     * @param next Predecessor matrix.
     * @param i Source node index.
     * @param j Destination node index.
     * @param nodeNames Array of node identifiers.
     * @return Formatted string representing the optimal path.
     */
    public String reconstructPath(int[][] next, int i, int j, String[] nodeNames) {
        return nodeNames[i] + reconstructIntermediate(next, i, j, nodeNames) + " -> " + nodeNames[j];
    }

    /**
     * Reconstructs the chain of intermediate nodes strictly between {@code i} and {@code j}
     * so shared midpoint vertices are not duplicated when concatenating sub-paths.
     */
    private String reconstructIntermediate(int[][] next, int i, int j, String[] nodeNames) {
        int k = next[i][j];
        if (k == -1) {
            return "";
        }
        return reconstructIntermediate(next, i, k, nodeNames)
                + " -> " + nodeNames[k]
                + reconstructIntermediate(next, k, j, nodeNames);
    }
}