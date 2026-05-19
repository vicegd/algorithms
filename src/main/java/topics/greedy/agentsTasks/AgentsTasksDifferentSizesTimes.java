package topics.greedy.agentsTasks;

import topics.greedy.AgentsTasks;

/**
 * <h1>Empirical Runtime Analysis for Agent Task Assignment</h1>
 * <p>
 * This benchmarking program systematically scales the problem dimensions (N agents and N tasks) 
 * to empirically verify the expected quadratic growth <code>O(N²)</code> of the Greedy assignment strategies.
 * </p>
 *
 * @author vicegd
 */
public class AgentsTasksDifferentSizesTimes {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing parameter: Please provide the number of iterations (nTimes) as an argument.");
        }

        int nTimes = Integer.parseInt(args[0]);
        long t1, t2;

        System.out.println("=========================================================");
        System.out.println("  BENCHMARKING GREEDY ASSIGNMENT (Empirical Curve O(N²))");
        System.out.println("=========================================================");

        // Lower limit protects memory heap space while scaling up cleanly
        for (int n = 10; n <= 2560; n *= 2) {
            int[][] costMatrix = new int[n][n];

            // Fill the matrix with benchmark assets
            AgentsTasksRandomValues.fillMatrixRandomly(costMatrix);

            t1 = System.currentTimeMillis();
            
            // Execute the algorithms structural routines nTimes to stabilize scheduling noise
            for (int r = 1; r <= nTimes; r++) {
                AgentsTasks tasks = new AgentsTasks(costMatrix);
                
                // Directly invoke the operations without passing external container arrays
                tasks.assignTasksToAgents();
                tasks.assignAgentsToTasks();
            }
            
            t2 = System.currentTimeMillis();
            long totalTime = t2 - t1;

            System.out.printf("Scale Matrix Size: N = %-6d | Executions: %-5d | Total Duration: %d ms%n", 
                              n, nTimes, totalTime);
        }
        System.out.println("=========================================================");
    }
}