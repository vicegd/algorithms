package topics.divideconquer.search;

/**
 * <h1>Binary Search</h1>
 * <p>
 * A classic Divide and Conquer algorithm used to find the position of a target 
 * value within a <strong>strictly sorted array</strong>. It operates by repeatedly 
 * dividing the search interval in half.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Begin with the mid-point of the whole array as a search key.</li>
 * <li>If the value of the search key is equal to the item, return the index.</li>
 * <li>If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half.</li>
 * <li>Otherwise, narrow it to the upper half.</li>
 * <li>Repeatedly check until the value is found or the interval is empty.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(log N)</code> in the worst/average case. <code>O(1)</code> in the best case (found at the first midpoint).</li>
 * <li><strong>Space Complexity (Iterative):</strong> <code>O(1)</code> - Requires only a few pointers.</li>
 * <li><strong>Space Complexity (Recursive):</strong> <code>O(log N)</code> - Memory consumed by the call stack depth.</li>
 * </ul>
 *
 * @author vicegd
 */
public class BinarySearch {

    /**
     * Iterative implementation of Binary Search.
     * Highly recommended over the recursive version due to O(1) space complexity.
     *
     * @param v The strictly sorted array to search in (ascending order).
     * @param x The target value to locate.
     * @return The index of the target value, or Integer.MIN_VALUE if not found.
     */
    public int binarySearchIterative(int[] v, int x) {
        if (v == null || v.length == 0) return Integer.MIN_VALUE;

        int left = 0;
        int right = v.length - 1;

        while (left <= right) {
            // Mathematically safe midpoint calculation to prevent Integer Overflow
            int center = left + (right - left) / 2;

            if (v[center] == x) {
                return center;
            } else if (v[center] > x) {
                right = center - 1; // The target must be in the left half
            } else {
                left = center + 1;  // The target must be in the right half
            }
        }
        
        return Integer.MIN_VALUE; // Target does not exist in the array
    }

    /**
     * Recursive implementation of Binary Search.
     * Demonstrates the Divide and Conquer paradigm elegantly, though it incurs 
     * O(log N) auxiliary space penalty due to the call stack.
     *
     * @param v The strictly sorted array to search in (ascending order).
     * @param x The target value to locate.
     * @return The index of the target value, or Integer.MIN_VALUE if not found.
     */
    public int binarySearchRecursive(int[] v, int x) {
        if (v == null || v.length == 0) return Integer.MIN_VALUE;
        return searchByDivision(0, v.length - 1, v, x);
    }

    /**
     * Private recursive helper method handling the bounds.
     */
    private int searchByDivision(int left, int right, int[] v, int x) {
        if (left > right) {
            return Integer.MIN_VALUE; // Search space exhausted
        }

        int center = left + (right - left) / 2;

        if (v[center] == x) {
            return center;
        } else if (v[center] > x) {
            // The target must be in the left half
            return searchByDivision(left, center - 1, v, x);
        } else {
            // The target must be in the right half
            return searchByDivision(center + 1, right, v, x);
        }
    }
}