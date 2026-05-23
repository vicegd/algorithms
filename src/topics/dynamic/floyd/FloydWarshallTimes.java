package topics.dynamic.floyd;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>Empirical Complexity Analysis: Floyd-Warshall</h1>
 * <p>
 * Demonstrates the O(N³) growth of the algorithm.
 * </p>
 *
 * @author vicegd
 */
public class FloydWarshallTimes {
    private static final int INF = FloydWarshall.INF;

    public static void main(String[] args) {
        FloydWarshall engine = new FloydWarshall();

        System.out.println("=========================================================");
        System.out.println("  BENCHMARKING FLOYD-WARSHALL: Complexity O(N³)");
        System.out.println("=========================================================");
        System.out.printf("%-10s | %-15s%n", "Nodes (N)", "Time (ms)");
        System.out.println("---------------------------------------------------------");

        // Safe limit for N to avoid JVM memory issues (OutOfMemoryError)
        for (int n = 50; n <= 4096; n *= 2) {
            int[][] dist = generateRandomGraph(n);
            int[][] next = new int[n][n];

            long t1 = System.currentTimeMillis();
            engine.compute(dist, next);
            long t2 = System.currentTimeMillis();

            System.out.printf("N = %-7d | %-15d ms%n", n, (t2 - t1));
        }
        System.out.println("=========================================================");
    }

    private static int[][] generateRandomGraph(int n) {
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    // Random weight or INF
                    graph[i][j] = ThreadLocalRandom.current().nextBoolean() 
                                  ? ThreadLocalRandom.current().nextInt(1, 100) 
                                  : INF;
                }
            }
        }
        return graph;
    }
}