package topics.dynamic.change;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Coin Change</h1>
 * <p>
 * Computes the absolute minimum number of coins required to make exact change 
 * for a specific target amount. Each denomination can be selected an unlimited 
 * number of times (Unbounded Knapsack variation).
 * </p>
 *
 * <h2>Why Greedy Fails Here</h2>
 * <p>
 * A greedy approach (always picking the largest coin first) does not guarantee 
 * an optimal solution. For example, to make {@code 15} with {@code [1, 6, 4]}:
 * </p>
 * <ul>
 * <li><strong>Greedy:</strong> 6 + 6 + 1 + 1 + 1 = 5 coins.</li>
 * <li><strong>DP Optimal:</strong> 6 + 4 + 4 + 1 = 4 coins.</li>
 * </ul>
 *
 * <h2>Dynamic Programming Transition Matrix (2D)</h2>
 * <p>
 * This implementation constructs a 2D matrix of size <code>N &times; (Amount+1)</code>.
 * Row <code>0</code> represents having only the base coin available (base case: {@code dp[0][j] = j}).
 * For each subsequent cell <code>dp[i][j]</code>, we decide whether to:
 * </p>
 * <ol>
 * <li><strong>Skip the coin:</strong> Inherit the minimum from the row directly above: <code>dp[i-1][j]</code>.</li>
 * <li><strong>Use the coin:</strong> Add 1 to the minimum for the remaining amount in the same row: <code>1 + dp[i][j - coins[i]]</code>.</li>
 * </ol>
 * <p>
 * Note: Since each denomination can be reused, we look within the <em>same row</em> (unlike 0/1 Knapsack,
 * which looks at the row above to prevent reuse).
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N &times; Amount)</code> - We evaluate every coin against every sub-amount up to the target.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N &times; Amount)</code> - Maintains the full historical state matrix for pedagogical clarity.</li>
 * </ul>
 *
 * @author vicegd
 */
public class Change {
    private static final Logger log = LoggerFactory.getLogger(Change.class);

    /**
     * Determines the minimum coins required for the target amount using a 2D DP matrix.
     *
     * @param amount The target monetary amount.
     * @param coins  The available denominations. Must include {@code 1} as the first element to guarantee a solution.
     * @return The optimal (minimum) number of coins.
     * @throws IllegalArgumentException if amount is negative, coins are null, or coins array is empty.
     */
    public int change(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length == 0) {
            throw new IllegalArgumentException("Invalid input: Target amount must be non-negative and coins array cannot be empty.");
        }

        if (amount == 0) {
            return 0; // Base case: 0 coins needed for amount 0
        }

        int n = coins.length;

        // DP Matrix: N rows (one per coin type), Amount+1 columns (one per sub-amount 0..amount).
        // By default, Java initializes the array with 0, which matches the base column (amount 0 = 0 coins).
        int[][] dp = new int[n][amount + 1];

        // Base case (row 0): using only coins[0] = 1, the minimum coins for amount j is exactly j.
        for (int j = 0; j <= amount; j++) {
            dp[0][j] = j;
        }

        // i represents the coin type being introduced (1 to N-1)
        for (int i = 1; i < n; i++) {
            int currentCoin = coins[i];

            // j represents the sub-amount being evaluated (0 to amount)
            for (int j = 0; j <= amount; j++) {

                // Option 1: Skip the coin — inherit the optimal solution from the row above
                dp[i][j] = dp[i - 1][j];

                if (j >= currentCoin) {
                    // Option 2: Use the coin — look left within the same row (unbounded reuse)
                    int useCoin = 1 + dp[i][j - currentCoin];
                    dp[i][j] = Math.min(dp[i][j], useCoin);
                }
            }
        }

        if (log.isTraceEnabled()) {
            printMatrix(dp, n, amount);
        }

        return dp[n - 1][amount];
    }

    /**
     * Helper method to format the 2D DP matrix for clean console logging.
     */
    private void printMatrix(int[][] dp, int n, int amount) {
        StringBuilder sb = new StringBuilder("\nDP Matrix (Rows: Coins 0 to N-1, Cols: Amount 0 to W):\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("Coin %d: | ", i));
            for (int j = 0; j <= amount; j++) {
                sb.append(String.format("%3d | ", dp[i][j]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}