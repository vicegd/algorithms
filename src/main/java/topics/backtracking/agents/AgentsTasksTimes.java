package topics.backtracking.agents;

import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Empirical Runtime Analysis for Backtracking</h1>
 * <p>
 * Demonstrates the O(N!) combinatorial explosion. Watch how the execution time 
 * increases violently as N grows from 10 to 12.
 * </p>
 *
 * @author vicegd
 */
public class AgentsTasksTimes {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasksTimes.class);

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("  BENCHMARKING PURE BACKTRACKING (Empirical Curve O(N!))");
        System.out.println("=========================================================");

        // WARNING: N grows linearly, but execution time grows factorially.
        // N=11 takes millions of operations. N=12 takes almost half a billion.
        // N > 12 is generally unsafe for pure backtracking without Branch & Bound.
        for (int n = 2; n <= 12; n++) {
            int[][] costMatrix = new int[n][n];
            fillMatrixRandomly(costMatrix);

            AgentsTasks engine = new AgentsTasks(costMatrix);
            
            long t1 = System.currentTimeMillis();
            engine.solve(); // Measures strictly the algorithmic execution
            long t2 = System.currentTimeMillis();
            
            long totalTime = t2 - t1;

            System.out.printf("Scale Matrix Size: N = %-2d | Combinations evaluated: %-10.0f | Total Duration: %d ms%n", 
                              n, factorial(n), totalTime);
        }
        System.out.println("=========================================================");
    }

    private static void fillMatrixRandomly(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextInt(1, 1000);
            }
        }
    }

    private static double factorial(int n) {
        double fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}