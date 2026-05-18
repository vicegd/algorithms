package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Cheaper Travel on the River - Dynamic Programming</h1>
 * <p>
 * A classic routing problem. Given a river with <code>N</code> docks, you can only 
 * travel downstream (from dock <code>i</code> to dock <code>j</code> where <code>i &lt; j</code>).
 * You are provided with a tariff matrix indicating the direct cost between any two docks.
 * The goal is to find the absolute minimum cost to travel between all possible pairs of docks, 
 * as sometimes taking a sequence of shorter boat trips is cheaper than one long direct trip.
 * </p>
 *
 * <h2>Algorithm Strategy</h2>
 * <p>
 * This is a specialized shortest-path Dynamic Programming approach for a Directed Acyclic Graph (DAG).
 * We build the optimal cost matrix <code>C</code> by iterating backwards.
 * For any pair of docks <code>(i, j)</code>, the minimum cost is the lowest value among:
 * </p>
 * <ul>
 * <li>Taking the direct trip: <code>Tariff[i][j]</code></li>
 * <li>Stopping at an intermediate dock <code>k</code>: <code>Cost[i][k] + Cost[k][j]</code></li>
 * </ul>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N&sup3;)</code> - Three nested loops to evaluate all start points, end points, and intermediate stops.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N&sup2;)</code> - To store the resulting minimum cost matrix.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RiverTravel {
    private static final Logger log = LoggerFactory.getLogger(RiverTravel.class);

    /**
     * Calculates the minimum cost matrix for traveling between all pairs of docks.
     *
     * @param tariff The initial matrix containing direct travel fees. Must be an N x N upper triangular matrix.
     * @return A new N x N matrix containing the minimum cost for each (i, j) pair.
     */
    public int[][] calculateMinimumCosts(int[][] tariff) {
        if (tariff == null || tariff.length == 0 || tariff.length != tariff[0].length) {
            throw new IllegalArgumentException("Tariff must be a valid, non-empty square matrix.");
        }

        int n = tariff.length;
        int[][] minCost = new int[n][n];

        // We iterate backwards from the second-to-last dock up to the first.
        // This ensures that when we evaluate a path from 'i' to 'j' via 'k', 
        // the sub-path from 'k' to 'j' has already been optimally calculated.
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                
                int currentMin = Integer.MAX_VALUE;
                
                // Evaluate all possible intermediate stops 'k' between 'i' and 'j'
                // (including k=j, which represents the direct trip without stops)
                for (int k = i + 1; k <= j; k++) {
                    int costViaK;
                    
                    if (k == j) {
                        // Direct trip scenario
                        costViaK = tariff[i][j];
                    } else {
                        // Multi-stop scenario: optimal path to k + optimal path from k to j
                        costViaK = minCost[i][k] + minCost[k][j];
                    }
                    
                    if (costViaK < currentMin) {
                        currentMin = costViaK;
                    }
                }
                minCost[i][j] = currentMin;
            }
        }

        if (log.isTraceEnabled()) {
            log.trace("Initial Tariffs:");
            printUpperTriangularMatrix(tariff);
            log.trace("Calculated Minimum Costs:");
            printUpperTriangularMatrix(minCost);
        }

        return minCost;
    }

    /**
     * Helper to clearly visualize the upper triangular matrices.
     */
    private void printUpperTriangularMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder("\n");
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= i) {
                    sb.append(String.format("%5d", matrix[i][j]));
                } else {
                    sb.append("    -"); // Mark invalid upstream paths
                }
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}