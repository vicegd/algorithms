package topics.introduction.search;

import java.util.List;

/**
 * <h1>Search (Algorithmic Structural Variations)</h1>
 * <p>
 * Provides a comparative implementation of fundamental search algorithms 
 * (Sequential, Sentinel, and Binary) to demonstrate their operational 
 * mechanics and algorithmic complexities.
 * </p>
 *
 * @author vicegd
 */
public class Search {

    /**
     * Performs a standard sequential (linear) search across an array.
     * <p>
     * <strong>Complexity:</strong> <code>O(N)</code> Time | <code>O(1)</code> Space.
     * Evaluates every element sequentially until a match is found or the boundaries are exhausted.
     * </p>
     *
     * @param list  The array of integers to be searched.
     * @param value The target integer to locate.
     * @return <code>true</code> if the element exists within the array, <code>false</code> otherwise.
     */
    public boolean searchSequential(int[] list, int value) {
        for (int current : list) {
            if (current == value) {
                return true;
            }
        }
        return false;
    }
  
    /**
     * Performs a sequential search utilizing a "sentinel" value.
     * <p>
     * <strong>Mechanics:</strong> Temporarily appends the target value to the end of the list. 
     * This guarantees the search will find the element, eliminating the need to check array 
     * boundaries <code>(i &lt; length)</code> during the inner loop, which can yield slight 
     * micro-optimizations in continuous memory blocks.
     * </p>
     * <p>
     * <strong>Complexity:</strong> <code>O(N)</code> Time | <code>O(1)</code> Amortized Space.
     * </p>
     *
     * @param list  The dynamically resizable list of integers.
     * @param value The target integer to locate.
     * @return <code>true</code> if the element existed before the sentinel was added.
     */
    public boolean searchSequentialSentinel(List<Integer> list, int value) {
        // Insert the sentinel value to guarantee a match
        list.add(value); 
        
        int index = 0;
        // Boundary check is omitted; we strictly evaluate the value
        while (list.get(index) != value) {
            index++;
        }
        
        // Evaluate if the match was the original data or our injected sentinel
        boolean wasFoundInOriginalData = (index < list.size() - 1);
        
        // Structural Cleanup: Remove the sentinel to avoid mutating the original dataset permanently
        list.remove(list.size() - 1);
        
        return wasFoundInOriginalData;
    }
  
    /**
     * Performs a binary search across a strictly sorted array.
     * <p>
     * <strong>Mechanics:</strong> Halves the search space on each iteration by comparing the 
     * target value against the central pivot. <i>Note: The input array must be sorted prior to execution.</i>
     * </p>
     * <p>
     * <strong>Complexity:</strong> <code>O(log N)</code> Time | <code>O(1)</code> Space.
     * </p>
     *
     * @param list  The sorted array of integers.
     * @param value The target integer to locate.
     * @return <code>true</code> if the element is found, <code>false</code> otherwise.
     */
    public boolean searchBinary(int[] list, int value) {
        int left = 0;
        int right = list.length - 1;
        
        while (left <= right) {
            // Evaluates the midpoint safely to prevent integer overflow on massive arrays
            int mid = left + (right - left) / 2;
            
            if (list[mid] == value) {
                return true;
            } else if (list[mid] < value) {
                left = mid + 1; // Discard the left half
            } else {
                right = mid - 1; // Discard the right half
            }
        }
        
        return false;
    }
}