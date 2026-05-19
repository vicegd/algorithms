package topics.divideconquer.fibonacci;

/**
 * <h1>Fibonacci Sequence</h1>
 * <p>
 * Computes the Fibonacci number of order N. This class focuses on demonstrating 
 * how the Divide and Conquer strategy can be applied to the same problem with 
 * drastically different performance outcomes (from Exponential to Logarithmic).
 * </p>
 *
 * @author vicegd
 * @see topics.dynamic.fibonacci.Fibonacci for Dynamic Programming approaches.
 */
public class Fibonacci {

    /**
     * <h2>1. Iterative Approach (Linear)</h2>
     * <p>Standard baseline approach for comparison.</p>
     * <ul>
     * <li><strong>Time:</strong> O(N)</li>
     * <li><strong>Space:</strong> O(1)</li>
     * </ul>
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
     * <h2>2. Array-Based Approach (Memoization)</h2>
     * <p>Stores all intermediate results. (Primarily a Dynamic Programming concept).</p>
     * <ul>
     * <li><strong>Time:</strong> O(N)</li>
     * <li><strong>Space:</strong> O(N)</li>
     * </ul>
     */
    public long fibonacciArray(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[] v = new long[n + 1];
        v[0] = 0;
        v[1] = 1;
        for (int i = 2; i <= n; i++) {
            v[i] = v[i - 1] + v[i - 2];
        }
        return v[n];
    }

    /**
     * <h2>3. Tail Recursive (D&C by Subtraction)</h2>
     * <p>A linear recursive version. It divides the problem by subtracting 1 
     * at each step, passing the accumulated state forward.</p>
     * <ul>
     * <li><strong>Time:</strong> O(N)</li>
     * <li><strong>Space:</strong> O(N) (Call stack)</li>
     * </ul>
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
     * <h2>4. Naive Recursive (The Overlapping Trap)</h2>
     * <p>Strict translation of F(n) = F(n-1) + F(n-2). This is a classic example 
     * of when Divide and Conquer fails terribly because the subproblems are not 
     * independent, leading to massive recalculations.</p>
     * <ul>
     * <li><strong>Time:</strong> O(1.618^N) Exponential</li>
     * <li><strong>Space:</strong> O(N) (Call stack)</li>
     * </ul>
     */
    public long fibonacciNaiveRecursive(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative numbers.");
        if (n <= 1) return n;
        return fibonacciNaiveRecursive(n - 1) + fibonacciNaiveRecursive(n - 2);
    }

    /**
     * <h2>5. Logarithmic Approach (D&C by Division)</h2>
     * <p>Sophisticated D&C algorithm that exploits mathematical matrix identities 
     * (Fast Doubling). It divides the problem size by 2 at each step instead of 1.</p>
     * <ul>
     * <li><strong>Time:</strong> O(log N)</li>
     * <li><strong>Space:</strong> O(1)</li>
     * </ul>
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