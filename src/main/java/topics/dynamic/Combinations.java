package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Combinations (n choose k) - Dynamic Programming</h1>
 * <p>
 * Calculates the mathematical combinations of <code>n</code> elements taken 
 * <code>k</code> at a time. This implementation highlights the dramatic difference 
 * between Naive Recursion (Exponential time) and Dynamic Programming (Polynomial time).
 * </p>
 *
 * <h2>The Overlapping Subproblems Trap</h2>
 * <p>
 * The mathematical recurrence relation is:
 * <br><code>C(n, k) = C(n-1, k-1) + C(n-1, k)</code><br>
 * If implemented using pure recursion, it repeatedly calculates the exact same 
 * branches, leading to a catastrophic time complexity of <code>O(2^n)</code>.
 * </p>
 *
 * <h2>Dynamic Programming Solution (Pascal's Triangle)</h2>
 * <p>
 * By utilizing a 2D array (memoization table), we compute the values systematically 
 * from the bottom up, effectively building Pascal's Triangle. We store the 
 * intermediate values, ensuring each subproblem is solved exactly once.
 * </p>
 *
 * <h2>Complexity Analysis (DP Version)</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N &times; K)</code> - We fill a 2D matrix of size N*K.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N &times; K)</code> - To store the matrix (can be optimized to O(K) using a 1D rolling array).</li>
 * </ul>
 *
 * @author vicegd
 */
public class Combinations {
    private static final Logger log = LoggerFactory.getLogger(Combinations.class);

    /**
     * Calculates combinations using Dynamic Programming (Bottom-Up).
     *
     * @param n Total number of elements.
     * @param k Number of elements to choose.
     * @return The number of combinations.
     */
    public long combinationsDP(int n, int k) {
        // Handle mathematical edge cases safely
        if (k < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        // DP table where table[i][j] stores C(i, j)
        long[][] table = new long[n + 1][k + 1];

        // Base cases initialization based on Pascal's Triangle boundaries
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1; // C(n, 0) is always 1
            if (i <= k) {
                table[i][i] = 1; // C(n, n) is always 1
            }
        }

        // Fill the table using the recurrence relation
        for (int i = 1; i <= n; i++) {
            // We only need to calculate up to 'k' or 'i', whichever is smaller
            for (int j = 1; j <= Math.min(i - 1, k); j++) {
                table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
            }
        }

        if (log.isTraceEnabled()) {
            writeSolution(table, n, k);
        }

        return table[n][k];
    }

    /**
     * Calculates combinations using Naive Recursion.
     * WARNING: This method exhibits O(2^n) exponential time complexity.
     * It is included strictly for educational performance comparisons.
     *
     * @param n Total number of elements.
     * @param k Number of elements to choose.
     * @return The number of combinations.
     */
    public long combinationsRecursive(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (n == k || k == 0) return 1;
        
        return combinationsRecursive(n - 1, k - 1) + combinationsRecursive(n - 1, k);
    }

    /**
     * Helper method to visualize the built DP table.
     */
    private void writeSolution(long[][] table, int n, int k) {
        StringBuilder sb = new StringBuilder("\nDP Table (Pascal's Triangle):\n");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                sb.append(String.format("%-8d", table[i][j]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}