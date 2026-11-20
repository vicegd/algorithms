package topics.greedy.tsp;

import java.util.Arrays;

/**
 * <h1>Traveling Salesman Solution Record</h1>
 * <p>
 * Immutable value carrier that encapsulates the tour produced by the greedy 
 * Traveling Salesman heuristic along with its total accumulated edge weight.
 * </p>
 *
 * @param path      Ordered sequence of visited vertex indices (including the return to origin).
 * @param totalCost Cumulative weight of all edges traversed in {@code path}.
 * @author vicegd
 */
public record SalesmanSolution(int[] path, int totalCost) {
    @Override
    public String toString() {
        return "Path: " + Arrays.toString(path) + " | Total Cost: " + totalCost;
    }
}