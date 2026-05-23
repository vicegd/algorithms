package topics.branchandbound.stringinterleaving;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import topics.backtracking.stringinterleaving.StringInterleavingGenerator;

/**
 * <h1>The Master Benchmark: String Interleaving Across Paradigms</h1>
 * <p>
 * This class serves as the ultimate pedagogical demonstration, benchmarking the performance 
 * of different algorithmic paradigms solving the exact same problem space.
 * </p>
 *
 * <h2>Phases of the Benchmark:</h2>
 * <ol>
 * <li><strong>Generation Phase:</strong> Compares Backtracking (DFS) vs Branch & Bound (BFS) 
 * for generating all valid combinations. Demonstrates the extreme overhead of maintaining a Priority Queue.</li>
 * <li><strong>Validation Phase:</strong> Compares pure Recursion (Divide & Conquer) vs Dynamic Programming 
 * for verifying the generated combinations. Demonstrates the power of Memoization.</li>
 * </ol>
 *
 * @author vicegd
 */
public class StringInterleavingBenchmark {
    public static void main(String[] args) {
        // SAFETY LIMIT: n=10 generates 184,756 combinations.
        // n=12 would generate 2,704,156 combinations (High risk of OutOfMemoryError in B&B Heap)
        int n = 10; 
        String a = generateRandomWord(n);
        String b = generateRandomWord(n);

        System.out.println("==========================================================================");
        System.out.println("        MASTER BENCHMARK: STRING INTERLEAVING PARADIGMS");
        System.out.println("==========================================================================");
        System.out.printf("Word A (Size %d): %s%n", n, a);
        System.out.printf("Word B (Size %d): %s%n", n, b);
        System.out.println("--------------------------------------------------------------------------");

        /* ==========================================================
         * PHASE 1: GENERATION (Backtracking vs Branch and Bound)
         * ========================================================== */
        System.out.println("[PHASE 1] GENERATION OF ALL COMBINATIONS");
        
        // 1. Backtracking (Depth-First Search)
        StringInterleavingGenerator generatorBacktracking = new StringInterleavingGenerator();
        long t1 = System.currentTimeMillis();
        List<String> solutions = generatorBacktracking.generateAllInterleavings(a, b);
        long t2 = System.currentTimeMillis();
        long timeBacktracking = t2 - t1;
        
        System.out.printf("Total Valid Shuffles Mathematically Possible: %,d%n", solutions.size());
        System.out.printf("-> Pure Backtracking (DFS) Time : %d ms%n", timeBacktracking);

        // 2. Branch and Bound (State Space Search via Priority Queue)
        // Heap customHeap = new HeapRepeatedNodes();
        topics.branchandbound.stringinterleaving.StringInterleavingGenerator generatorBB = new topics.branchandbound.stringinterleaving.StringInterleavingGenerator(a, b, null /* replace with customHeap */);
        t1 = System.currentTimeMillis();
        generatorBB.branchAndBound(generatorBB.getRootNode());
        t2 = System.currentTimeMillis();
        long timeBB = t2 - t1;
        
        System.out.printf("-> Branch & Bound (Heap) Time   : %d ms (Notice the massive Heap overhead)%n", timeBB);
        System.out.println("--------------------------------------------------------------------------");

        /* ==========================================================
         * PHASE 2: VALIDATION (Divide & Conquer vs Dynamic Programming)
         * ========================================================== */
        System.out.println("[PHASE 2] VALIDATION OF ALL GENERATED STRINGS");

        // 1. Pure Recursion (Divide & Conquer)
        topics.divideconquer.stringinterleaving.StringInterleaving checkerRecursive = new topics.divideconquer.stringinterleaving.StringInterleaving();
        t1 = System.currentTimeMillis();
        for (String sol : solutions) {
            checkerRecursive.isInterleaved(a, b, sol); 
        }
        t2 = System.currentTimeMillis();
        long timeRecursive = t2 - t1;
        
        System.out.printf("-> Divide & Conquer Time        : %d ms%n", timeRecursive);

        // 2. Dynamic Programming (Tabulation/Memoization)
        topics.dynamic.stringinterleaving.StringInterleaving checkerDP = new topics.dynamic.stringinterleaving.StringInterleaving();
        t1 = System.currentTimeMillis();
        for (String sol : solutions) {
            checkerDP.isInterleaved(a, b, sol);
        }
        t2 = System.currentTimeMillis();
        long timeDP = t2 - t1;
        
        System.out.printf("-> Dynamic Programming Time     : %d ms%n", timeDP);
        System.out.println("==========================================================================");
    }

    /**
     * Generates a random lowercase string of a specific length using modern ThreadLocalRandom.
     */
    public static String generateRandomWord(int n) {
        StringBuilder buffer = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // ASCII 97 ('a') to 122 ('z')
            int randomAscii = ThreadLocalRandom.current().nextInt(97, 123);
            buffer.append((char) randomAscii);
        }
        return buffer.toString();
    }
}