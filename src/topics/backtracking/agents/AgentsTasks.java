package topics.backtracking.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Agent-Task Assignment</h1>
 * <p>
 * Evaluates an <code>N x N</code> matrix of deployment costs to assign N tasks to N unique agents.
 * Unlike Greedy approaches, this Backtracking algorithm systematically explores 
 * <strong>every single valid permutation</strong> of assignments to guarantee the absolute 
 * global minimum cost.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> O(N!) - Explores all factorial permutations (Pure Force).</li>
 * <li><strong>Space Complexity:</strong> O(N) - Bounded by the recursion call stack depth.</li>
 * </ul>
 *
 * @author vicegd
 */
public class AgentsTasks {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasks.class);
    
    private final int[][] costs;
    private final int n;
    
    // State tracking variables for the absolute best path found
    private int[] bestSolution;
    private int minCost;

    /**
     * Constructs the assignment engine performing a defensive copy of the cost matrix.
     *
     * @param costs Square matrix of dimensions N x N mapping assignment costs.
     */
    public AgentsTasks(int[][] costs) {
        if (costs == null || costs.length == 0 || costs.length != costs[0].length) {
            throw new IllegalArgumentException("Cost context must be a valid, non-empty square matrix.");
        }
        this.n = costs.length;
        this.costs = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            System.arraycopy(costs[i], 0, this.costs[i], 0, n);
        }
    }

    /**
     * Triggers the backtracking exploration to find the optimal assignment.
     *
     * @return Array where index is the Agent and value is the assigned Task.
     */
    public int[] solve() {
        int[] currentSolution = new int[n];
        boolean[] taskAssigned = new boolean[n]; // O(1) tracker instead of O(N) scanning
        
        this.bestSolution = new int[n];
        this.minCost = Integer.MAX_VALUE;

        // Initialize empty state
        for (int i = 0; i < n; i++) {
            currentSolution[i] = -1;
        }

        // Kick off the DFS recursion
        backtrack(0, currentSolution, taskAssigned);
        
        if (log.isTraceEnabled()) {
            log.trace("Exploration Finished. Absolute Minimum Cost: {}", minCost);
        }
        return bestSolution;
    }

    /**
     * Returns the optimal cost calculated by the solve() method.
     */
    public int getOptimalCost() {
        return minCost;
    }

    /**
     * Core recursive backtracking function (Depth-First Search).
     */
    private void backtrack(int currentAgent, int[] currentSolution, boolean[] taskAssigned) {
        // Base Case (Leaf Node): All agents have been assigned a task
        if (currentAgent == n) {
            evaluateSolution(currentSolution);
            return;
        }

        // Recursive Step: Try assigning every possible unassigned task to the current agent
        for (int task = 0; task < n; task++) {
            if (!taskAssigned[task]) {
                
                // 1. CHOOSE (Make a move)
                currentSolution[currentAgent] = task;
                taskAssigned[task] = true;
                
                // 2. EXPLORE (Recurse deeper into the tree)
                backtrack(currentAgent + 1, currentSolution, taskAssigned);
                
                // 3. UN-CHOOSE (Backtrack to explore other parallel branches)
                taskAssigned[task] = false;
                currentSolution[currentAgent] = -1;
            }
        }
    }

    /**
     * Evaluates a completed permutation leaf and updates the global minimum if better.
     */
    private void evaluateSolution(int[] currentSolution) {
        int currentPathCost = 0;
        for (int i = 0; i < n; i++) {
            currentPathCost += costs[i][currentSolution[i]];
        }

        // Update the best global state if this permutation is cheaper
        if (currentPathCost < minCost) {
            minCost = currentPathCost;
            System.arraycopy(currentSolution, 0, bestSolution, 0, n);
            
            if (log.isTraceEnabled()) {
                log.trace("New Best Solution Found! Cost: {}", minCost);
            }
        }
    }
}