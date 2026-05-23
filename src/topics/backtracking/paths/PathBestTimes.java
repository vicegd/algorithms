package topics.backtracking.paths;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Empirical Analysis: Pruning Impact</h1>
 * <p>
 * Measures the execution time difference between solving the Shortest Path 
 * with brute force (PathBest) vs. Branch & Bound (PathBestPruning).
 * </p>
 */
public class PathBestTimes {
    private static final Logger log = LoggerFactory.getLogger(PathBestTimes.class);
    
    public static void main(String[] args) {
        System.out.println("=====================================================================");
        System.out.println("          BENCHMARKING PATHS: UN-PRUNED vs PRUNED (B&B)");
        System.out.println("=====================================================================");
        System.out.printf("%-6s | %-25s | %-25s%n", "N", "Un-pruned Time (ms)", "Pruned Time (ms)");
        System.out.println("---------------------------------------------------------------------");

        // Limit set to 15 to prevent the un-pruned O(N!) algorithm from freezing the machine.
        int maxN = 15;

        for (int n = 3; n <= maxN; n++) {
            // CRITICAL FIX: Generate ONE shared graph so both algorithms compete fairly
            int[][] sharedWeights = generateRandomWeights(n);
            int source = 0;
            int target = n - 1;
            
            // 1. Un-pruned
            PathBest pathBest = new PathBest(n);
            pathBest.setWeightMatrix(sharedWeights);
            pathBest.setSource(source);
            pathBest.setTarget(target);
            
            long t1 = System.currentTimeMillis();       
            pathBest.backtracking();
            long t2 = System.currentTimeMillis();
            long unprunedTime = (t2 - t1);
            
            // 2. Pruned (Branch & Bound)
            PathBestPruning pathBestPruning = new PathBestPruning(n);
            pathBestPruning.setWeightMatrix(sharedWeights);
            pathBestPruning.setSource(source);
            pathBestPruning.setTarget(target);
            
            t1 = System.currentTimeMillis();        
            pathBestPruning.backtracking();
            t2 = System.currentTimeMillis();
            long prunedTime = (t2 - t1);
            
            System.out.printf("N=%-4d | %-25d | %-25d%n", n, unprunedTime, prunedTime);
        }
        System.out.println("=====================================================================");
    }
    
    private static int[][] generateRandomWeights(int n) {
        int[][] w = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    w[i][j] = -1;
                } else {
                    w[i][j] = r.nextInt(100) + 1;
                }
            }
        }
        return w;
    }
}