package topics.dynamic.knapsack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>0/1 Knapsack</h1>
 * <p>
 * Evaluates a set of items, each with a specific weight and value, to determine 
 * the combination that maximizes the total value without exceeding the weight 
 * limit of a knapsack. The "0/1" property dictates that an item must be taken 
 * entirely or left entirely (no fractions).
 * </p>
 *
 * <h2>Dynamic Programming Transition Matrix (2D)</h2>
 * <p>
 * This implementation constructs a 2D matrix of size <code>(N+1) &times; (W+1)</code>.
 * Row <code>0</code> represents having "0 items" available (base case = 0 value).
 * For each subsequent cell <code>dp[i][w]</code>, we decide whether to:
 * </p>
 * <ol>
 * <li><strong>Leave the item:</strong> Inherit the maximum value from the row directly above <code>dp[i-1][w]</code>.</li>
 * <li><strong>Take the item:</strong> Add the item's value to the maximum value found in the row above, shifted left by the item's weight: <code>ItemValue + dp[i-1][w - ItemWeight]</code>.</li>
 * </ol>
 *
 * <h2>Important Note on Input Data</h2>
 * <p>
 * In this specific educational implementation, the <code>benefits</code> array represents 
 * the <strong>value per unit of weight (Value/Kg)</strong>, not the absolute value. 
 * Therefore, the total value of an item is calculated as: 
 * <code>itemValue = benefits[i] * weights[i]</code>.
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N &times; W)</code> - Where N is items and W is max capacity.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N &times; W)</code> - Maintains the full historical state matrix for pedagogical clarity.</li>
 * </ul>
 *
 * @author vicegd
 */
public class Knapsack01 {
    private static final Logger log = LoggerFactory.getLogger(Knapsack01.class);

    /**
     * Solves the 0/1 Knapsack problem using a 2D DP Matrix.
     *
     * @param maxWeight The absolute weight limit of the knapsack.
     * @param benefits  The value per Kg for each item.
     * @param weights   The absolute weight of each item.
     * @return The maximum possible total value.
     */
    public float knapsack01(int maxWeight, float[] benefits, int[] weights) {
        if (maxWeight < 0 || benefits == null || weights == null) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }
        if (benefits.length != weights.length) {
            throw new ArrayIndexOutOfBoundsException("Benefits and weights arrays must be of equal length.");
        }

        int n = weights.length;
        
        // DP Matrix: +1 row for the "0 items" base case, +1 col for "0 capacity" base case.
        // By default, Java initializes the array with 0.0f, which perfectly matches our base cases.
        float[][] dp = new float[n + 1][maxWeight + 1];

        // i represents the number of items considered (1 to N)
        for (int i = 1; i <= n; i++) {
            
            // Adjust index by -1 to access the correct item in the input arrays
            int currentWeight = weights[i - 1];
            float totalItemValue = benefits[i - 1] * currentWeight;

            // w represents the current capacity being evaluated (0 to maxWeight)
            for (int w = 0; w <= maxWeight; w++) {
                
                if (currentWeight <= w) {
                    // Option 1: Don't take the item (look directly up)
                    float notTakingItem = dp[i - 1][w];
                    
                    // Option 2: Take the item (look up and left)
                    float takingItem = totalItemValue + dp[i - 1][w - currentWeight];
                    
                    dp[i][w] = Math.max(notTakingItem, takingItem);
                } else {
                    // The item doesn't fit at all, we must leave it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        if (log.isTraceEnabled()) {
            printMatrix(dp, n, maxWeight);
        }

        return dp[n][maxWeight];
    }

    /**
     * Helper method to format the 2D DP matrix for clean console logging.
     */
    private void printMatrix(float[][] dp, int n, int maxWeight) {
        StringBuilder sb = new StringBuilder("\nDP Matrix (Rows: Items 0 to N, Cols: Capacity 0 to W):\n");
        for (int i = 0; i <= n; i++) {
            sb.append(String.format("Item %d: | ", i));
            for (int w = 0; w <= maxWeight; w++) {
                sb.append(String.format("%6.1f | ", dp[i][w]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}