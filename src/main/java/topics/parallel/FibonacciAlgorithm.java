package topics.parallel;

/**
 * <h1>Naive Recursive Fibonacci</h1>
 * <p>
 * Calculates the Fibonacci sequence using a purely mathematical, highly 
 * inefficient recursive approach. This implementation deliberately ignores 
 * optimizations like memoization to demonstrate exponential time complexity. 
 * It serves as a baseline to highlight the necessity of parallel computing strategies.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(2^N)</code> - Exponential growth. Every calculation branches into two identical sub-trees, causing massive redundant processing.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Linear memory required for the deep recursive stack frames.</li>
 * </ul>
 *
 * @author vicegd
 */
public class FibonacciAlgorithm {  
    private final int targetIndex;
  
    /**
     * Initializes the algorithm with the target sequence index.
     *
     * @param targetIndex The mathematical index (N) to compute.
     */
    public FibonacciAlgorithm(int targetIndex) {
        this.targetIndex = targetIndex;
    }
  
    /**
     * Triggers the recursive calculation.
     *
     * @return The computed Fibonacci sequence value.
     */
    public long solve() {
        return calculateFibonacci(this.targetIndex);
    }

    /**
     * Internal mathematical recursion.
     */
    private long calculateFibonacci(int current) {
        if (current <= 1) {
            return current;
        }
        return calculateFibonacci(current - 1) + calculateFibonacci(current - 2);
    }
}