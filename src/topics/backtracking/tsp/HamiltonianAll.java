package topics.backtracking.tsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Hamiltonian Cycles (Exhaustive Search)</h1>
 * <p>
 * Generates all possible Hamiltonian cycles in a weighted graph using Backtracking.
 * Since the TSP is NP-Hard, this approach explores the entire search space O((N-1)!).
 * </p>
 * * <h2>The Backtracking Phases</h2>
 * <ul>
 * <li><strong>Choose:</strong> Mark the next city as visited and add its weight.</li>
 * <li><strong>Explore:</strong> Recurse to the next level of the path.</li>
 * <li><strong>Un-choose:</strong> Backtrack by resetting the mark and subtracting the weight.</li>
 * </ul>
 * * @author vicegd
 */
public class HamiltonianAll {
    private static final Logger log = LoggerFactory.getLogger(HamiltonianAll.class);
    
    private final int n;
    private final int[][] weights;
    private final int source;
    private final boolean[] visited;
    private final int[] path;
    
    private int solutionCount = 0;
    private int currentCost = 0;
    private int pathLength = 0;

    public HamiltonianAll(int n, int source, int[][] weights) {
        this.n = n;
        this.source = source;
        this.weights = weights;
        this.visited = new boolean[n];
        this.path = new int[n + 1];
        
        this.path[0] = source;
        this.visited[source] = true;
    }

    /**
     * Triggers the exhaustive backtracking search.
     */
    public void solve() {
        backtrack(source);
    }

    private void backtrack(int current) {
        // Base case: All nodes visited and path closed back to source
        if (pathLength == n - 1) {
            if (weights[current][source] != -1) {
                solutionCount++;
                logSolution();
            }
            return;
        }

        // Recursive step: Try every possible next node
        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (!visited[nextNode] && weights[current][nextNode] != -1) {
                // 1. CHOOSE
                visited[nextNode] = true;
                pathLength++;
                path[pathLength] = nextNode;
                currentCost += weights[current][nextNode];
                
                // 2. EXPLORE
                backtrack(nextNode);
                
                // 3. UN-CHOOSE
                visited[nextNode] = false;
                currentCost -= weights[current][nextNode];
                pathLength--;
            }
        }
    }

    private void logSolution() {
        StringBuilder sb = new StringBuilder("Cycle found: ");
        for (int i = 0; i <= pathLength; i++) sb.append("NODE").append(path[i]).append("-");
        sb.append("NODE").append(source);
        log.trace("{} | Cost: {}", sb.toString(), currentCost);
    }

    public int getSolutionCount() {
        return solutionCount;
    }
}