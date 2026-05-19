package topics.greedy.agents;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>Random Asset Generator for Agent Task Assignment</h1>
 * <p>
 * Generates arbitrary square matrices representing cost metrics associated with 
 * mapping specific tasks to individual agents. Values are bounded between 1 and 999.
 * </p>
 *
 * @author vicegd
 */
public class AgentsTasksRandomValues {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing parameter: Please provide the matrix dimension size (N) as an argument.");
        }

        int n = Integer.parseInt(args[0]);
        int[][] costMatrix = new int[n][n];

        // 1. Fill matrix with data assets
        fillMatrixRandomly(costMatrix);
        
        // 2. Instantiate the assignment engine context
        AgentsTasks tasks = new AgentsTasks(costMatrix);
        
        // 3. Process Strategy 1 (Row-Minima / Agent-Driven)
        System.out.println("--- Executing Assignment Strategy 1 (Row-Minima) ---");
        int[] solution1 = tasks.assignTasksToAgents();
        int totalCost1 = tasks.calculateRowStrategyCost(solution1);
        System.out.println("Strategy 1 Allocation Completed. Total Resolved Cost: " + totalCost1);

        // 4. Process Strategy 2 (Column-Minima / Task-Driven)
        System.out.println("\n--- Executing Assignment Strategy 2 (Column-Minima) ---");
        int[] solution2 = tasks.assignAgentsToTasks();
        int totalCost2 = tasks.calculateColumnStrategyCost(solution2);
        System.out.println("Strategy 2 Allocation Completed. Total Resolved Cost: " + totalCost2);
    }

    /**
     * Fills a square matrix with random costs between 1 and 999.
     * Uses ThreadLocalRandom for optimized memory efficiency boundaries.
     *
     * @param matrix The matrix to populate.
     */
    public static void fillMatrixRandomly(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // ThreadLocalRandom eliminates internal locks and scales beautifully
                matrix[i][j] = ThreadLocalRandom.current().nextInt(1, 1000);
            }
        }
    }
}