package topics.introduction;

/**
 * <h1>Maximum Value Extraction</h1>
 * <p>
 * Provides a sequential mathematical utility to identify the maximum 
 * element contained within a one-dimensional integer array.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Requires a single linear traversal evaluating every element.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - The evaluation requires a strictly constant amount of auxiliary memory.</li>
 * </ul>
 *
 * @author vicegd
 */
public class GetMaximumFromList {
    
    /**
     * Computes the maximum value within the provided sequence of integers.
     *
     * @param numbers The integer array to be evaluated.
     * @return The highest integer value found within the array.
     * @throws IllegalArgumentException if the provided array is null or empty.
     */
    public int max(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("The array must contain at least one element for extraction.");
        }
        
        // Initializes with the first element to strictly ensure mathematical 
        // safety even if the array exclusively contains negative integers.
        int maximumValue = numbers[0];
        
        for (int number : numbers) {
            if (number > maximumValue) {
                maximumValue = number;
            }
        }
        
        return maximumValue;
    }
}