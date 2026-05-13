package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Coin Change Problem — Dynamic Programming solution.
 *
 * <p>Given an amount of money and a set of coin denominations, finds the minimum
 * number of coins needed to make exact change. Each denomination may be used
 * any number of times (unbounded selection).
 *
 * <h2>Why Greedy Fails</h2>
 * <p>Greedily picking the largest coin that fits does <em>not</em> always give
 * the minimum. For example, with {@code amount=15} and {@code coins=[1,6,4]}:
 * <pre>
 *   Greedy:  6+6+1+1+1 = 5 coins
 *   Optimal: 6+4+4+1   = 4 coins  ← what DP finds
 * </pre>
 *
 * <h2>Why Dynamic Programming Works</h2>
 * <p>The problem has <strong>optimal substructure</strong>: the minimum coins
 * for amount {@code j} using denominations {@code 0..i} depends only on
 * already-solved smaller sub-problems. It also has
 * <strong>overlapping sub-problems</strong>: many intermediate amounts are
 * recomputed in naive recursion, making memoisation worthwhile.
 *
 * <h2>Recurrence Relation</h2>
 * <pre>
 *   dp[i][j] = min(
 *     dp[i-1][j],              // skip coin type i
 *     1 + dp[i][j - coins[i]]  // use one more coin of type i (unbounded)
 *   )
 *   Base case: dp[0][j] = j   (only coins[0]=1 available)
 * </pre>
 *
 * <h2>Complexity</h2>
 * <ul>
 *   <li><strong>Time:</strong>  O(amount &times; types) — fills the entire DP table</li>
 *   <li><strong>Space:</strong> O(amount &times; types) — the DP table itself
 *       (reducible to O(amount) with a rolling 1-D array)</li>
 * </ul>
 *
 * <h2>Example Table</h2>
 * <p>{@code amount=15, coins=[1,6,4]}:
 * <pre>
 *      j:  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 *   [1]:   0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
 *   [6]:   0  1  2  3  4  5  1  2  3  4  5  6  2  3  4  5
 *   [4]:   0  1  2  3  1  2  1  2  2  3  2  3  2  3  2  4  ← answer: 4
 * </pre>
 *
 * @author vicegd
 * @see topics.greedy.Change for the greedy approach (suboptimal in general)
 * @see Knapsack01 for the related 0/1 knapsack DP problem
 */
public class Change {
  private static final Logger LOG = LoggerFactory.getLogger(Change.class);

  /**
   * Finds the minimum number of coins needed to make exact change for
   * {@code amount}.
   *
   * <p>Uses bottom-up DP, building the solution table row by row.
   * Each row {@code i} adds denomination {@code coins[i]} to the available set.
   *
   * <p><strong>Key difference from 0/1 knapsack:</strong> when taking coin
   * {@code i}, the lookup is {@code sol[i][j - coins[i]]} (same row) instead
   * of {@code sol[i-1][...]} (previous row), because each denomination can be
   * reused as many times as needed.
   *
   * <h3>Algorithm Steps</h3>
   * <ol>
   *   <li>Initialise row 0: {@code sol[0][j] = j} (need {@code j} copies of
   *       the unit coin).</li>
   *   <li>For each coin type {@code i} from 1 to {@code types-1}:<br>
   *       For each amount {@code j} from 0 to {@code amount}:
   *       <ul>
   *         <li>{@code notPicking = sol[i-1][j]}</li>
   *         <li>{@code picking    = 1 + sol[i][j - coins[i]]} (only if
   *             {@code j >= coins[i]})</li>
   *         <li>{@code sol[i][j]  = min(notPicking, picking)}</li>
   *       </ul>
   *   </li>
   *   <li>Return {@code sol[types-1][amount]}.</li>
   * </ol>
   *
   * <h3>Complexity</h3>
   * <ul>
   *   <li><strong>Time:</strong>  O(amount &times; types)</li>
   *   <li><strong>Space:</strong> O(amount &times; types)</li>
   * </ul>
   *
   * @param amount the target amount (must be &ge; 0)
   * @param coins  available denominations; {@code coins[0]} must be 1 to
   *               guarantee a solution always exists for any positive amount
   * @return the minimum number of coins that sum exactly to {@code amount}
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

