package topics.divideconquer.factorial;

/**
 * <h1>Factorial Calculation</h1>
 * <p>
 * Demonstrates both the iterative and recursive paradigms for computing the 
 * factorial of a non-negative integer (N!).
 * </p>
 * * <h2>The Integer Overflow Trap</h2>
 * <p>
 * Factorial functions grow explosively.
 * <ul>
 * <li>Using standard 32-bit <code>int</code>, the maximum computable value is 12!</li>
 * <li>At 13!, the value exceeds 2.14 billion and causes a silent arithmetic overflow.</li>
 * <li>By upgrading to 64-bit <code>long</code>, this implementation safely computes up to 20!</li>
 * </ul>
 * For values strictly greater than 20!, Java's <code>BigInteger</code> class must be used.
 * </p>
 *
 * @author vicegd
 */
public class Factorial {

    /**
     * Iterative implementation using a linear accumulator.
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(1)</code></li>
     * </ul>
     *
     * @param n A non-negative integer up to 20.
     * @return The factorial of N.
     * @throws IllegalArgumentException if N is negative.
     */
    public long factorialIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is strictly undefined for negative numbers.");
        }
        
        long f = 1;
        // Minor optimization: start from 2, since multiplying by 1 changes nothing
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    /**
     * Recursive implementation demonstrating Divide and Conquer by Subtraction.
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(N)</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(N)</code> due to the execution call stack.</li>
     * </ul>
     *
     * @param n A non-negative integer up to 20.
     * @return The factorial of N.
     * @throws IllegalArgumentException if N is negative.
     */
    public long factorialRecursive(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is strictly undefined for negative numbers.");
        }
        
        // Base case
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // Recursive step: n! = n * (n-1)!
        return n * factorialRecursive(n - 1);
    }
}