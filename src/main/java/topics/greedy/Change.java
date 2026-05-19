package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Coin Change</h1>
 * <p>
 * Calculates the number of coins required to make a specific amount of change.
 * The heuristic (Greedy Choice) is to always pick the largest possible coin denomination 
 * that does not exceed the remaining amount.
 * </p>
 *
 * <h2>The Greedy Trap</h2>
 * <p>
 * While this algorithm is blazingly fast <code>O(C)</code>, it <strong>does not guarantee 
 * the globally optimal solution</strong> (minimum number of coins) for all currency systems.
 * It works perfectly for standard currencies (like US Dollars or Euros) which are canonical, 
 * but fails on arbitrary coin sets (e.g., coins = {20, 15, 1}, amount = 30. Greedy gives 15 coins: 
 * 20x1 + 1x10. Optimal is 2 coins: 15x2).
 * </p>
 *
 * <h2>Financial Software Note</h2>
 * <p>
 * Never use <code>float</code> or <code>double</code> for currency calculations due to IEEE 754 
 * binary precision loss. Always use integers (representing the smallest sub-unit, e.g., cents) 
 * or <code>java.math.BigDecimal</code>.
 * </p>
 *
 * @author vicegd
 */
public class Change {
    private static final Logger log = LoggerFactory.getLogger(Change.class);

    /**
     * Calculates the change using a purely greedy approach.
     * <p>
     * Optimized using Integer Division and Modulo instead of repeated subtraction, 
     * making the time complexity strictly bound by the number of coin types.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(C)</code> where C is the length of the coins array.</li>
     * <li><strong>Space Complexity:</strong> <code>O(C)</code> to store the solution array.</li>
     * </ul>
     *
     * @param amount The target amount to return (must be non-negative).
     * @param coins  The available coin denominations, <strong>strictly sorted in descending order</strong>.
     * @return An array of the same length as <code>coins</code>, representing the count of each coin used.
     */
    public int[] calculateCoins(int amount, int[] coins) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        if (coins == null || coins.length == 0) {
            throw new IllegalArgumentException("Coin denominations must be provided.");
        }

        int[] solution = new int[coins.length];
        int remainingAmount = amount;

        for (int i = 0; i < coins.length; i++) {
            if (remainingAmount == 0) {
                break; // Target reached, short-circuit
            }

            // How many times does this coin fit into the remaining amount?
            if (remainingAmount >= coins[i]) {
                solution[i] = remainingAmount / coins[i]; // E.g., 60 / 50 = 1 coin
                remainingAmount = remainingAmount % coins[i]; // E.g., 60 % 50 = 10 remaining
            }
        }

        if (log.isTraceEnabled()) {
            log.trace("Target amount: {}. Remaining unresolved amount: {}", amount, remainingAmount);
        }

        // Note: If remainingAmount > 0 here, it means the given coins cannot make exact change.
        return solution;
    }
}