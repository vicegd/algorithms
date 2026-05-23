package topics.backtracking.tsp;

/**
 * <h1>TSP Optimization with Pruning (Bounding)</h1>
 * <p>
 * This class implements <b>Branch & Bound</b> by pruning branches that exceed 
 * the current {@code bestCost}.
 * </p>
 * * <h2>Bounding Principle</h2>
 * <p>
 * If {@code currentCost + edgeCost >= bestCost}, we stop exploring this path. 
 * This is the crucial heuristic that makes this algorithm usable for slightly larger N.
 * </p>
 */
public class SalesmanPruning extends Salesman {

    public SalesmanPruning(int n, int source, int[][] weights) {
        super(n, source, weights);
    }

    @Override
    protected void backtrack(int current) {
        // Base case: Cycle completed
        if (length == n - 1) {
            super.backtrack(current);
            return;
        }

        // Recursive step WITH PRUNING
        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (!mark[nextNode] && weights[current][nextNode] != -1) {
                int nextCost = cost + weights[current][nextNode];
                
                // PRUNING: Only explore if this branch is promising
                if (nextCost < bestCost) {
                    // Choose
                    mark[nextNode] = true;
                    path[++length] = nextNode;
                    cost = nextCost;

                    backtrack(nextNode);

                    // Un-choose
                    cost -= weights[current][nextNode];
                    length--;
                    mark[nextNode] = false;
                }
            }
        }
    }
}