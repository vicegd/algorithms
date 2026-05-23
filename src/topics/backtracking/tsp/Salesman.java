package topics.backtracking.tsp;

/**
 * <h1>Traveling Salesman</h1>
 * <p>
 * Extends HamiltonianAll to find the <b>global minimum cost</b> cycle.
 * Unlike the base class, it stores {@code bestCost} and updates it whenever a 
 * shorter Hamiltonian cycle is discovered.
 * </p>
 */
public class Salesman extends HamiltonianAll {
    protected final int[] bestPath;
    protected int bestCost = Integer.MAX_VALUE;

    public Salesman(int n, int source, int[][] weights) {
        super(n, source, weights);
        this.bestPath = new int[n + 1];
    }

    @Override
    protected void backtrack(int current) {
        // Base Case: Check if this cycle is the best found so far
        if (length == n - 1) {
            int finalCost = cost + weights[current][source];
            if (weights[current][source] != -1 && finalCost < bestCost) {
                bestCost = finalCost;
                System.arraycopy(path, 0, bestPath, 0, n);
                bestPath[n] = source;
                nsol++;
            }
            return;
        }

        // Recursive step (No pruning yet)
        super.backtrack(current);
    }

    public int getBestCost() { return bestCost; }
    public String getBestPath() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) sb.append("NODE").append(bestPath[i]).append("**");
        return sb.toString();
    }
}