package topics.greedy;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Fractional Knapsack</h1>
 * <p>
 * Evaluates a set of items, each with a specific weight and value, to determine 
 * the combination that maximizes the total value within a knapsack's weight limit.
 * <strong>Crucially, items can be broken into fractions</strong> if they don't fit entirely.
 * </p>
 *
 * <h2>The Greedy Choice</h2>
 * <p>
 * Because items can be fractionalized, the mathematically optimal strategy is to:
 * <ol>
 * <li>Calculate the Value-to-Weight ratio for each item.</li>
 * <li>Sort items descending by this ratio.</li>
 * <li>Take as much of the highest-ratio items as possible until the knapsack is full.</li>
 * </ol>
 * This Guarantees the global optimum, unlike the 0/1 Knapsack variant which 
 * requires Dynamic Programming.
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O(N log N) - Bound by the sorting step.</li>
 * <li><strong>Space Complexity:</strong> O(N) - To store the fractional solution and helper objects.</li>
 * </ul>
 *
 * @author vicegd
 */
public class FractionalKnapsack {
    private static final Logger log = LoggerFactory.getLogger(FractionalKnapsack.class);

    /**
     * Calculates the optimal fractional distribution of items to maximize value.
     *
     * @param maxWeight The maximum weight capacity of the knapsack.
     * @param weights   The absolute weight of each item.
     * @param values    The absolute value of each item.
     * @return A float array representing the fraction taken of each item (0.0 to 1.0).
     */
    public float[] fillKnapsack(int maxWeight, int[] weights, int[] values) {
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("Weights and values must be valid and of equal length.");
        }

        int n = weights.length;
        float[] solution = new float[n];
        
        // 1. Map items to a helper object to track original indices after sorting
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(i, weights[i], values[i]);
        }

        // 2. Sort items by their value-to-weight ratio in DESCENDING order O(N log N)
        Arrays.sort(items, (a, b) -> Float.compare(b.ratio, a.ratio));

        int currentWeight = 0;

        // 3. Iterate through sorted items and pack the knapsack O(N)
        for (Item item : items) {
            if (currentWeight == maxWeight) {
                break; // Knapsack is completely full
            }

            if (currentWeight + item.weight <= maxWeight) {
                // Take the whole item
                solution[item.originalIndex] = 1.0f;
                currentWeight += item.weight;
            } else {
                // Take only the fraction needed to fill the knapsack exactly
                int remainingCapacity = maxWeight - currentWeight;
                float fractionToTake = (float) remainingCapacity / item.weight;
                
                solution[item.originalIndex] = fractionToTake;
                currentWeight = maxWeight; // Knapsack is now full
            }
        }

        if (log.isTraceEnabled()) {
            log.trace("Fractional Allocation Result: {}", Arrays.toString(solution));
        }

        return solution;
    }

    /**
     * Private helper class to maintain data cohesion during sorting.
     */
    private static class Item {
        int originalIndex;
        int weight;
        int value;
        float ratio;

        Item(int index, int weight, int value) {
            this.originalIndex = index;
            this.weight = weight;
            this.value = value;
            this.ratio = (float) value / weight;
        }
    }
}