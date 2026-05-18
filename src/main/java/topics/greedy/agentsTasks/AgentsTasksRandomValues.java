package topics.greedy.agentsTasks;

import java.util.concurrent.ThreadLocalRandom;
import topics.greedy.AgentsTasks;

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
        int[] solution1 = new int[n];
        int[] solution2 = new int[n];

        // 1. Fill matrix with data assets
        fillMatrixRandomly(costMatrix);
        
        // 2. Instantiate and process Strategy 1
        AgentsTasks tasks = new AgentsTasks(costMatrix);
        
        System.out.println("--- Executing Assignment Strategy 1 ---");
        tasks.greedy1(solution1);
        int totalCost1 = tasks.getCost1(solution1);
        System.out.println("Strategy 1 Allocation Completed. Total Resolved Cost: " + totalCost1);

        // 3. Process Strategy 2
        System.out.println("\n--- Executing Assignment Strategy 2 ---");
        tasks.greedy2(solution2);
        int totalCost2 = tasks.getCost2(solution2);
        System.out.println("Strategy 2 Allocation Completed. Total Resolved Cost: " + totalCost2);
    }

    /**
     * Fills a square matrix with random costs between 1 and 999.
     * Uses ThreadLocalRandom for optimized concurrent memory efficiencybaselines.
     *
     * @param matrix The matrix to populate.
     */
    public static void fillMatrixRandomly(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // ThreadLocalRandom is significantly faster and more secure than new Random()
                matrix[i][j] = ThreadLocalRandom.current().nextInt(1, 1000);
            }
        }
    }
}