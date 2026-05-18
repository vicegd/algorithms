package topics.divideconquer;

/**
 * <h1>Sequential (Linear) Search Algorithm</h1>
 * <p>
 * Implements the baseline linear search paradigm to locate an element within an array. 
 * Unlike Binary Search, Sequential Search makes no assumptions about data layout 
 * and functions correctly on both sorted and unsorted collections.
 * </p>
 *
 * <h2>Divide & Conquer Strategy by Subtraction</h2>
 * <p>
 * The recursive approach demonstrates a Divide and Conquer by Subtraction model 
 * (where <code>a = 1, b = 1, k = 0</code>). Each step safely isolates the head element 
 * for evaluation and reduces the subsequent search space dimension by exactly 1.
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Worst/Average case requires scanning all elements. <code>O(1)</code> Best case (element at index 0).</li>
 * <li><strong>Space Complexity (Iterative):</strong> <code>O(1)</code> - Requires constant auxiliary index memory.</li>
 * <li><strong>Space Complexity (Recursive):</strong> <code>O(N)</code> - Bound directly by call stack frames depth.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.divideconquer.BinarySearch for optimized sorted alternatives.
 */
public class SequentialSearch {

    /**
     * Iterative implementation of sequential linear search.
     * Generally preferred over the recursive counter-part due to memory constraints.
     *
     * @param v The target array to search across (can be unsorted).
     * @param x The target value to locate.
     * @return The absolute index of target value x within v, or Integer.MIN_VALUE if not found.
     */
    public int searchIterative(int[] v, int x) {
        if (v == null) return Integer.MIN_VALUE;

        int n = v.length;
        int i = 0;
        while (i < n) { 
            if (v[i] == x) {
                return i;
            }
            i++;
        }
        return Integer.MIN_VALUE; // Target does not exist in the collection
    }
  
    /**
     * Recursive implementation of sequential linear search.
     * Illustrates Divide and Conquer by Subtraction semantics.
     *
     * @param v The target array to search across (can be unsorted).
     * @param x The target value to locate.
     * @return The absolute index of target value x within v, or Integer.MIN_VALUE if not found.
     */
    public int searchRecursive(int[] v, int x) {
        if (v == null || v.length == 0) return Integer.MIN_VALUE;
        return searchBySubtraction(0, v, x);
    }

    /**
     * Private recursive helper managing pointer updates.
     */
    private int searchBySubtraction(int i, int[] v, int x) {
        // Base case 1: Search space fully exhausted
        if (i == v.length) {
            return Integer.MIN_VALUE;
        }
        
        // Base case 2: Element located successfully
        if (v[i] == x) {
            return i;
        }
        
        // Recursive step: Reduce problem space by 1
        return searchBySubtraction(i + 1, v, x);
    }
}