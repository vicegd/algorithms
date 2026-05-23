package topics.backtracking.tsp;

import java.util.Random;

/**
 * <h1>Empirical Complexity Analysis: TSP Optimizations</h1>
 * <p>
 * This benchmark demonstrates the massive performance difference between pure 
 * Exhaustive Search (Un-pruned) and Branch & Bound (Pruned) when solving the 
 * Traveling Salesman Problem.
 * </p>
 * * <h2>Pedagogical Note on Complexity</h2>
 * <p>
 * Both algorithms mathematically share a worst-case time complexity of O(N!). 
 * However, the <b>Pruning</b> heuristic allows the algorithm to skip millions of 
 * branches in practice. You will see the Un-pruned version start to freeze around 
 * N=12, while the Pruned version handles it effortlessly.
 * </p>
 *
 * @author vicegd
 */
public class SalesmanTimes {
    public static void main(String[] args) {
        System.out.println("=====================================================================");
        System.out.println("       BENCHMARKING TSP: UN-PRUNED vs PRUNED (Branch & Bound)");
        System.out.println("=====================================================================");
        System.out.printf("%-6s | %-25s | %-25s%n", "N", "Un-pruned Time (ms)", "Pruned Time (ms)");
        System.out.println("---------------------------------------------------------------------");

        // SAFETY LIMIT: O(N!) grows insanely fast. N=12 is usually the limit for a standard CPU
        // without pruning. N=14 is an absolute wall.
        int maxN = 13; 

        for (int n = 4; n <= maxN; n++) {
            // CRITICAL FIX: Both algorithms must compete on the EXACT SAME GRAPH
            int[][] sharedGraph = generateRandomWeights(n);
            int sourceNode = 0;

            // 1. Evaluate standard Salesman (Explores everything to find the best)
            Salesman salesman = new Salesman(n, sourceNode, sharedGraph);
            long t1 = System.currentTimeMillis();
            salesman.backtracking();
            long t2 = System.currentTimeMillis();
            long timeUnpruned = t2 - t1;

            // 2. Evaluate Salesman with Pruning (Branch & Bound)
            SalesmanPruning salesmanPruning = new SalesmanPruning(n, sourceNode, sharedGraph);
            t1 = System.currentTimeMillis();
            salesmanPruning.backtracking();
            t2 = System.currentTimeMillis();
            long timePruned = t2 - t1;

            // Print formatted results
            System.out.printf("N=%-4d | %-25d | %-25d%n", n, timeUnpruned, timePruned);
        }
        System.out.println("=====================================================================");
    }

    /**
     * Generates a fully connected graph with random distances.
     */
    private static int[][] generateRandomWeights(int n) {
        int[][] w = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Symmetric TSP: distance from i to j is the same as j to i
                w[i][j] = w[j][i] = r.nextInt(99) + 1;
            }
            w[i][i] = -1; // No self-loops allowed
        }
        return w;
    }
}