package topics.greedy;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>0/1 Knapsack</h1>
 * <p>
 * Attempts to solve the 0/1 Knapsack problem (where items cannot be broken) 
 * using the Fractional Knapsack's greedy heuristic: prioritizing the highest 
 * Value-to-Weight ratio.
 * </p>
 *
 * <h2>Educational Purpose: The Greedy Failure</h2>
 * <p>
 * This class serves as mathematical proof that a Greedy algorithm <strong>CANNOT</strong> 
 * reliably solve the 0/1 Knapsack problem. Because items are indivisible, greedily 
 * picking high-ratio items often fills the knapsack awkwardly, leaving large gaps 
 * of unused capacity and resulting in a severely sub-optimal total value.
 * </p>
 * <p>
 * To see the correct, mathematically optimal solution for this problem, refer 
 * to the Dynamic Programming implementation: <code>topics.dynamic.Knapsack01</code>.
 * </p>
 *
 * @author vicegd
 */
public class Knapsack01 {
    private static final Logger log = LoggerFactory.getLogger(Knapsack01.class);

    /**
     * Attempts to pack the knapsack greedily.
     *
     * @param maxWeight The maximum weight capacity of the knapsack.
     * @param weights   The absolute weight of each item.
     * @param values    The absolute value of each item.
     * @return An integer array (0 or 1) representing whether each item was taken.
     */
    public int[] fillKnapsackGreedily(int maxWeight, int[] weights, int[] values) {
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("Weights and values must be valid and of equal length.");
        }

        int n = weights.length;
        int[] solution = new int[n];
        
        // 1. Map items to track original indices after sorting
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(i, weights[i], values[i]);
        }

        // 2. Sort items descending by their value-to-weight ratio O(N log N)
        Arrays.sort(items, (a, b) -> Float.compare(b.ratio, a.ratio));

        int currentWeight = 0;

        // 3. Greedily attempt to pack items O(N)
        for (Item item : items) {
            // Because items CANNOT be broken, we must check if the whole item fits
            if (currentWeight + item.weight <= maxWeight) {
                solution[item.originalIndex] = 1; 
                currentWeight += item.weight;
            }
            // If it doesn't fit, we simply skip it and evaluate the next one.
            // This skipping is exactly what causes the Greedy Trap.
        }

        if (log.isTraceEnabled()) {
            log.trace("Sub-Optimal Greedy Allocation Result: {}", Arrays.toString(solution));
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