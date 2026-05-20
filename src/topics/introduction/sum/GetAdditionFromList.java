package topics.introduction.sum;

/**
 * <h1>Array Summation</h1>
 * <p>
 * Provides a sequential mathematical utility to calculate the aggregate sum 
 * of all elements contained within a one-dimensional integer array.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Requires a single linear traversal evaluating every element in the array exactly once.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - The evaluation requires a strictly constant amount of auxiliary memory, operating independently of the array's dimension.</li>
 * </ul>
 *
 * @author vicegd
 */
public class GetAdditionFromList {
    
    /**
     * Computes the total aggregate value of the provided sequence of integers.
     *
     * @param numbers The integer array to be evaluated.
     * @return The exact mathematical sum of all elements within the array.
     */
    public int sum(int[] numbers) {
        int totalValue = 0;
        
        for (int number : numbers) {
            totalValue += number;
        }
        
        return totalValue;
    }
}