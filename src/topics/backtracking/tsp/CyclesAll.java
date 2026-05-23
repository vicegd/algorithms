package topics.backtracking.tsp;

/**
 * <h1>Simple Cycles of a Node</h1>
 * <p>
 * This class calculates all simple cycles in a graph originating from a specific source node.
 * </p>
 * * <h2>Pedagogical Note: TSP vs Cycles</h2>
 * <ul>
 * <li><b>Traveling Salesman:</b> Requires COMPLETE simple cycles (must visit every node exactly once).</li>
 * <li><b>Node Cycles:</b> Accepts SIMPLE cycles of ANY length (visiting a subset of nodes and returning).</li>
 * </ul>
 * <p>
 * Even though it doesn't require complete paths, the worst-case time complexity remains 
 * factorial O(N!) for highly connected graphs due to the combinatorial explosion of subsets.
 * </p>
 * * @author vicegd
 */
public class CyclesAll extends HamiltonianAll {

    public CyclesAll(int n, int source, int[][] weights) {
        super(n, source, weights);
    }

    @Override
    protected void backtrack(int current) {
        // DIFFERENCE WITH TSP: We don't wait for length == n - 1.
        // At any step (as long as we have moved), if there is a valid edge back to the source, 
        // we have found a valid simple cycle!
        if (length > 0 && weights[current][source] != -1) {
            nsol++;
            if (log.isTraceEnabled()) {
                log.trace("Simple Cycle found. Nodes visited: {} | Cost: {}", 
                          length, cost + weights[current][source]);
            }
            // Note: We DO NOT return here, because extending this path might lead to 
            // longer, valid simple cycles!
        }

        // Standard Explore Phase
        for (int j = 0; j < n; j++) {
            if (!mark[j] && weights[current][j] != -1) {
                // Choose
                mark[j] = true;
                path[++length] = j;
                cost += weights[current][j];

                // Explore
                backtrack(j);

                // Un-choose (Backtrack)
                cost -= weights[current][j];
                length--;
                mark[j] = false;
            }
        }
    }
}