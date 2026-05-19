package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Agent-Task Assignment</h1>
 * <p>
 * Evaluates an <code>N &times; N</code> matrix of deployment costs to assign N tasks to N unique agents.
 * This class contrasts two structural Greedy approaches (Row-Minima vs. Column-Minima) 
 * to demonstrate that localized voracious choices do not guarantee a globally optimal configuration.
 * </p>
 *
 * @author vicegd
 */
public class AgentsTasks {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasks.class);
    private final int[][] costMatrix;
    private final int n;

    /**
     * Constructs the assignment engine performing a defensive copy of the cost matrix.
     *
     * @param costs Square matrix of dimensions N x N mapping assignment costs.
     * @throws IllegalArgumentException if the matrix is null, empty, or asymmetrical.
     */
    public AgentsTasks(int[][] costs) {
        if (costs == null || costs.length == 0 || costs.length != costs[0].length) {
            throw new IllegalArgumentException("Cost context must be a valid, non-empty square matrix.");
        }
        this.n = costs.length;
        this.costMatrix = new int[n][n];
        
        // Deep copy to guarantee internal state immutability against outside references
        for (int i = 0; i < n; i++) {
            System.arraycopy(costs[i], 0, this.costMatrix[i], 0, n);
        }

        if (log.isTraceEnabled()) {
            printCostMatrix();
        }
    }

    /**
     * <h2>Strategy 1: Row-Minima (Agent-Driven)</h2>
     * <p>
     * Iterates through agents (rows) sequentially, assigning each agent to their 
     * cheapest available task (column) that hasn't been claimed yet.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N&sup2;)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code> for assignment tracking array.</li>
     * </ul>
     *
     * @return An array where <code>result[i]</code> represents the task assigned to agent <code>i</code>.
     */
    public int[] assignTasksToAgents() {
        int[] assignments = new int[n];
        boolean[] taskClaimed = new boolean[n];

        for (int i = 0; i < n; i++) {
            int minCost = Integer.MAX_VALUE;
            int selectedTask = -1;

            for (int j = 0; j < n; j++) {
                if (!taskClaimed[j] && costMatrix[i][j] < minCost) {
                    minCost = costMatrix[i][j];
                    selectedTask = j;
                }
            }
            assignments[i] = selectedTask;
            taskClaimed[selectedTask] = true;
        }
        return assignments;
    }

    /**
     * <h2>Strategy 2: Column-Minima (Task-Driven)</h2>
     * <p>
     * Iterates through tasks (columns) sequentially, assigning each task to the 
     * cheapest available agent (row) that hasn't been claimed yet.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N&sup2;)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code> for assignment tracking array.</li>
     * </ul>
     *
     * @return An array where <code>result[j]</code> represents the agent mapped to task <code>j</code>.
     */
    public int[] assignAgentsToTasks() {
        int[] assignments = new int[n];
        boolean[] agentClaimed = new boolean[n];

        for (int j = 0; j < n; j++) {
            int minCost = Integer.MAX_VALUE;
            int selectedAgent = -1;

            for (int i = 0; i < n; i++) {
                if (!agentClaimed[i] && costMatrix[i][j] < minCost) {
                    minCost = costMatrix[i][j];
                    selectedAgent = i;
                }
            }
            assignments[j] = selectedAgent;
            agentClaimed[selectedAgent] = true;
        }
        return assignments;
    }

    /**
     * Computes total cost incurred by Strategy 1 (Row-Minima).
     *
     * @param assignments Output tracking array mapping agent to task.
     * @return Total aggregate cost metric.
     */
    public int calculateRowStrategyCost(int[] assignments) {
        if (log.isTraceEnabled()) {
            log.trace("Allocation Solution (Row Strategy 1):");
            for (int i = 0; i < n; i++) {
                log.trace("\tAgent {} has been assigned Task {}", i, assignments[i]);
            }
        }

        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            totalCost += costMatrix[i][assignments[i]];
        }
        return totalCost;
    }

    /**
     * Computes total cost incurred by Strategy 2 (Column-Minima).
     *
     * @param assignments Output tracking array mapping task to agent.
     * @return Total aggregate cost metric.
     */
    public int calculateColumnStrategyCost(int[] assignments) {
        if (log.isTraceEnabled()) {
            log.trace("Allocation Solution (Column Strategy 2):");
            for (int j = 0; j < n; j++) {
                log.trace("\tTask {} has been assigned to Agent {}", j, assignments[j]);
            }
        }

        int totalCost = 0;
        for (int j = 0; j < n; j++) {
            totalCost += costMatrix[assignments[j]][j];
        }
        return totalCost;
    }

    private void printCostMatrix() {
        StringBuilder sb = new StringBuilder("\nCost Configuration Context:\n");
        for (int i = 0; i < n; i++) {
            sb.append("Agent ").append(i).append(" | ");
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%3d // ", costMatrix[i][j]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}