package topics.greedy.tsp;

import java.util.Arrays;

/**
 * Record class to hold the calculated path and its cost.
 */
public record SalesmanSolution(int[] path, int totalCost) {
    @Override
    public String toString() {
        return "Path: " + Arrays.toString(path) + " | Total Cost: " + totalCost;
    }
}