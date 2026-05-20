package topics.divideconquer.maxsum;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>Empirical Runtime Analysis for Maximum Subarray Sum</h1>
 * <p>
 * This benchmarking utility demonstrates the dramatic performance differences 
 * between algorithmic complexities: Cubic O(N&sup3;), Quadratic O(N&sup2;), 
 * and Linearithmic O(N log N).
 * </p>
 *
 * <h2>Educational Note</h2>
 * <p>
 * The execution loop is strictly capped at N = 4096. 
 * If allowed to scale indefinitely, the O(N&sup3;) algorithm would cause 
 * the CPU to hang for hours due to combinatorial explosion.
 * </p>
 *
 * @author vicegd
 */
public class MaxSumBenchmark {
    public static void main(String[] args) {
        MaxSum engine = new MaxSum();

        System.out.println("=======================================================================");
        System.out.println("   BENCHMARKING MAXIMUM SUBARRAY SUM: O(N³) vs O(N²) vs O(N log N) ");
        System.out.println("=======================================================================");
        System.out.printf("%-12s | %-15s | %-15s | %-15s%n", "Array Size", "Cubic O(N³)", "Quadratic O(N²)", "D&C O(N log N)");
        System.out.println("-----------------------------------------------------------------------");

        // Capped at 4096 to prevent the Cubic algorithm from freezing the execution environment.
        for (int n = 10; n <= 4096; n *= 2) {
            int[] v = new int[n];
            
            // Populate with both positive and negative values (crucial for this specific problem)
            for (int i = 0; i < n; i++) {
                v[i] = ThreadLocalRandom.current().nextInt(-1000, 1000);
            }

            // 1. Measure Cubic Time O(N³)
            long t1 = System.currentTimeMillis();
            engine.maxSubarrayCubic(v);
            long t2 = System.currentTimeMillis();

            // 2. Measure Quadratic Time O(N²)
            long t3 = System.currentTimeMillis();
            engine.maxSubarrayQuadratic(v);
            long t4 = System.currentTimeMillis();

            // 3. Measure Divide & Conquer Time O(N log N)
            long t5 = System.currentTimeMillis();
            engine.maxSubarrayDivideAndConquer(v);
            long t6 = System.currentTimeMillis();

            // Output formatted results
            System.out.printf("N = %-8d | %-12d ms | %-12d ms | %-12d ms%n", 
                              n, (t2 - t1), (t4 - t3), (t6 - t5));
        }
        System.out.println("=======================================================================");
    }
}