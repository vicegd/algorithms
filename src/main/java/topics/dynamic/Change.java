package topics.dynamic;

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
 * <h2>Space-Optimized DP Transition</h2>
 * <p>
 * Instead of a full 2D matrix <code>O(Amount * Types)</code>, we can use a 1D 
 * array <code>O(Amount)</code>. For each coin, we update the array from left to right:
 * <br><br>
 * <code>dp[j] = min( dp[j], 1 + dp[j - coin] )</code>
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(Amount &times; Types)</code> - We evaluate every coin against every sub-amount up to the target.</li>
 * <li><strong>Space Complexity:</strong> <code>O(Amount)</code> - We only maintain the optimal solutions for the current amounts being processed.</li>
 * </ul>
 *
 * @author vicegd
 */
public class Change {
    private static final Logger log = LoggerFactory.getLogger(Change.class);

    /**
     * Determines the minimum coins required for the target amount using a 1D DP table.
     *
     * @param amount The target monetary amount.
     * @param coins  The available denominations. Must include 1 to guarantee a solution.
     * @return The optimal (minimum) number of coins.
     * @throws IllegalArgumentException if amount is negative, coins are null, or no 1-unit coin exists.
     */
    public int change(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length == 0) {
            throw new IllegalArgumentException("Invalid input: Target amount must be non-negative and coins array cannot be empty.");
        }
        
        if (amount == 0) {
            return 0; // Base case: 0 coins needed for amount 0
        }

        // DP array where dp[i] represents the minimum coins needed to make amount 'i'
        int[] dp = new int[amount + 1];
        
        // Initialize the table. We assume the worst case: using only 1-unit coins.
        // Therefore, dp[j] initially equals j.
        for (int j = 0; j <= amount; j++) {
            dp[j] = j;
        }

        // Process each subsequent coin denomination
        for (int i = 1; i < coins.length; i++) {
            int currentCoin = coins[i];
            
            // We only update amounts that are equal to or greater than the current coin
            for (int j = currentCoin; j <= amount; j++) {
                
                int pickingNewCoin = 1 + dp[j - currentCoin];
                int notPickingNewCoin = dp[j];
                
                // Keep the mathematically optimal (minimum) choice
                dp[j] = Math.min(notPickingNewCoin, pickingNewCoin);
            }
            
            if (log.isTraceEnabled()) {
                log.trace("DP Table after processing coin [{}]: {}", currentCoin, formatArraySubset(dp, amount));
            }
        }

        return dp[amount];
    }
    
    /**
     * Helper to safely format the DP array for logging without overwhelming the console.
     */
    private String formatArraySubset(int[] arr, int maxElements) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= Math.min(maxElements, 15); i++) {
            sb.append(arr[i]).append(i < Math.min(maxElements, 15) ? ", " : "");
        }
        if (maxElements > 15) sb.append(", ...]");
        else sb.append("]");
        return sb.toString();
    }
}