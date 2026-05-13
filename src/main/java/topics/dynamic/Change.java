package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Coin Change Problem - Dynamic Programming Solution.
 *
 * Problem Statement:
 * Given an amount of money and a set of coin denominations, find the minimum
 * number of coins needed to make exact change. Each denomination may be used
 * any number of times (unbounded selection).
 *
 * Why Greedy Fails:
 * Greedily picking the largest coin that fits does NOT always give the minimum.
 *
 * Example where greedy fails (amount=15, coins=[1,6,4]):
 *   Greedy: 6+6+1+1+1 = 5 coins
 *   Optimal: 6+4+4+1  = 4 coins  <- what DP finds
 *
 * Why Dynamic Programming Works:
 * The problem has optimal substructure: the minimum coins for amount j using
 * denomination types 0..i depends only on already-solved smaller sub-problems.
 * It also has overlapping sub-problems: many intermediate amounts are computed
 * multiple times in naive recursion, making memoization worthwhile.
 *
 * DP Recurrence Relation:
 *   dp[i][j] = min(
 *     dp[i-1][j],              // Skip coin type i
 *     1 + dp[i][j - coins[i]]  // Use one more coin of type i (unbounded)
 *   )
 *   Base case: dp[0][j] = j  (only coins[0]=1 available; need j of them)
 *
 * Complexity Analysis:
 * - Time:  O(amount x types)  fills the entire DP table cell by cell
 * - Space: O(amount x types)  the DP table itself
 *   Note: reducible to O(amount) using a single rolling 1D array
 *
 * Example Table (amount=15, coins=[1,6,4]):
 *     j:  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 *  [1]:   0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 *  [6]:   0  1  2  3  4  5  1  2  3  4  5  6  2  3  4  5
 *  [4]:   0  1  2  3  1  2  1  2  2  3  2  3  2  3  2  4  <- answer: 4
 *
 * @author vicegd
 * @see topics.greedy.Change for the greedy approach (suboptimal in general)
 * @see Knapsack01 for the related 0/1 knapsack DP problem
 */
public class Change {
  private static final Logger LOG = LoggerFactory.getLogger(Change.class);

  /**
   * Finds the minimum number of coins needed to make exact change for amount.
   *
   * Uses bottom-up DP, building the solution table row by row.
   * Each row i adds denomination coins[i] to the available set.
   *
   * Key difference from 0/1 knapsack: when taking coin i, the lookup is
   * sol[i][j - coins[i]] (same row) instead of sol[i-1][...] (previous row),
   * because each denomination can be reused as many times as needed.
   *
   * Algorithm Steps:
   * 1. Initialize row 0: sol[0][j] = j  (need j copies of the unit coin)
   * 2. For each coin type i from 1 to types-1:
   *    For each amount j from 0 to amount:
   *      - notPicking = sol[i-1][j]
   *      - picking    = 1 + sol[i][j - coins[i]]  (only if j >= coins[i])
   *      - sol[i][j]  = min(notPicking, picking)
   * 3. Return sol[types-1][amount]
   *
   * Complexity Analysis:
   * - Time:  O(amount x types)
   * - Space: O(amount x types)
   *
   * @param amount the target amount (must be >= 0)
   * @param coins  available denominations; coins[0] must be 1 to guarantee
   *               a solution always exists for any positive amount
   * @return the minimum number of coins that sum exactly to amount
   */
  public int change(int amount, int[] coins) {
    var types = coins.length;
    var sol = new int[types][amount + 1];

    for (int i = 0; i <= amount; i++) {
      sol[0][i] = i;
    }

    for (int i = 1; i < types; i++) {
      for (int j = 0; j <= amount; j++) {
        var notPickingNewCoin = sol[i - 1][j];
        var pickingNewCoin = j >= coins[i]
            ? 1 + sol[i][j - coins[i]]
            : Integer.MAX_VALUE;
        sol[i][j] = Math.min(notPickingNewCoin, pickingNewCoin);
      }
    }

    var sb = new StringBuilder("\n");
    for (var row : sol) {
      for (var cell : row) {
        sb.append("%5d".formatted(cell));
      }
      sb.append("\n");
    }
    LOG.trace(sb.toString());
    return sol[types - 1][amount];
  }
}

