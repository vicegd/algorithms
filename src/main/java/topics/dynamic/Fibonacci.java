package topics.dynamic;

/**
 * <h1>Fibonacci Sequence - Algorithmic Paradigms</h1>
 * <p>
 * Computes the Fibonacci number of order N. This class serves as a masterclass 
 * in algorithmic complexity, demonstrating how the exact same mathematical problem 
 * can be solved using different paradigms, ranging from unaffordable exponential 
 * time to highly optimized logarithmic time.
 * </p>
 * <p>Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89...</p>
 *
 * @author vicegd
 */
public class Fibonacci {

    /**
     * <h2>1. Iterative Approach (Space Optimized DP)</h2>
     * <p>Computes Fibonacci using a simple loop. It only keeps track of the 
     * last two values instead of the entire array.</p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(1)</code></li>
     * </ul>
     *
     * @param n Positive integer input.
     * @return Fibonacci value for n.
     */
    public long fibonacciIterative(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n == 0) return 0;

        long n1 = 0;
        long n2 = 1;
        for (int i = 2; i <= n; i++) {
            long sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
        return n2;
    }

    /**
     * <h2>2. Dynamic Programming Approach (Tabulation)</h2>
     * <p>Uses a 1D array to store previously computed values, avoiding 
     * recalculations. This is the fundamental concept of Bottom-Up DP.</p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code></li>
     * </ul>
     *
     * @param n Positive integer input.
     * @return Fibonacci value for n.
     */
    public long fibonacciDP(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * <h2>3. Tail Recursive Approach (Divide & Conquer by Subtraction)</h2>
     * <p>A linear recursive version that passes the accumulators forward. 
     * Modern compilers can optimize this to prevent StackOverflows.</p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code> (Call stack)</li>
     * </ul>
     *
     * @param n Positive integer input.
     * @return Fibonacci value for n.
     */
    public long fibonacciTailRecursive(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        return tailHelper(0, 1, n);
    }

    private long tailHelper(long n1, long n2, int n) {
        if (n == 0) return n1;
        if (n == 1) return n2;
        return tailHelper(n2, n1 + n2, n - 1);
    }

    /**
     * <h2>4. Naive Recursive Approach (The Trap)</h2>
     * <p>Strict translation of the mathematical formula <code>F(n) = F(n-1) + F(n-2)</code>.
     * It solves the same overlapping subproblems millions of times.</p>
     * <ul>
     * <li><strong>Time Complexity:</strong> Exponential <code>O(1.618^N)</code> - UNAFFORDABLE FOR N > 45.</li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code> (Call stack)</li>
     * </ul>
     *
     * @param n Positive integer input.
     * @return Fibonacci value for n.
     */
    public long fibonacciNaiveRecursive(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n <= 1) return n;
        
        return fibonacciNaiveRecursive(n - 1) + fibonacciNaiveRecursive(n - 2);
    }

    /**
     * <h2>5. Logarithmic Approach (Fast Doubling / Matrix Exponentiation)</h2>
     * <p>Sophisticated Divide & Conquer algorithm that exploits mathematical 
     * matrix identities to compute the answer skipping massive amounts of steps.</p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(log N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(1)</code></li>
     * </ul>
     *
     * @param n Positive integer input.
     * @return Fibonacci value for n.
     */
    public long fibonacciLogarithmic(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n == 0) return 0;
        
        long i = 1, j = 0, k = 0, h = 1, t;
        
        while (n > 0) {
            if (n % 2 == 1) {
                t = j * h;
                j = i * h + j * k + t;
                i = i * k + t;
            }
            t = h * h;
            h = 2 * k * h + t;
            k = k * k + t;
            n = n / 2;
        }
        return j;
    }
}