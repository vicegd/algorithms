package topics.divideconquer;

import topics.divideconquer.utils.Util;
import topics.sorting.Quicksort;

/**
 * <h1>Median Calculation</h1>
 * <p>
 * Finds the median of an unsorted array. This class demonstrates the evolution 
 * from a brute-force sorting approach to the highly optimized Quickselect algorithm.
 * </p>
 *
 * <h2>Even Length Definition</h2>
 * <p>
 * Note: For arrays with an even number of elements, there are technically two medians. 
 * This algorithm follows the standard integer division convention <code>(N/2)</code>, 
 * which targets the upper median element mathematically.
 * </p>
 *
 * @author vicegd
 */
public class Median {

    /**
     * <h2>1. Sorting Approach</h2>
     * <p>
     * Sorts the entire array to easily extract the central element.
     * While conceptually simple, it does unnecessary work by fully ordering 
     * the elements on the left and right sides of the median.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N log N) - Bound by the Quicksort step.</li>
     * <li><strong>Space Complexity:</strong> O(N) - We clone the array to prevent mutating the user's original data.</li>
     * </ul>
     *
     * @param v Array of integers.
     * @return The median value.
     */
    public int medianBySorting(int[] v) {
        if (v == null || v.length == 0) {
            throw new IllegalArgumentException("Cannot calculate the median of a null or empty array.");
        }
        
        // Prevent side-effects: Do not mutate the original array
        int[] copy = v.clone();
        
        Quicksort quicksort = new Quicksort();
        quicksort.sort(copy);
        
        int centerPosition = copy.length / 2;
        return copy[centerPosition]; 
    }    

    /**
     * <h2>2. Quickselect Approach (Divide & Conquer)</h2>
     * <p>
     * Uses the Quicksort partition scheme to recursively isolate the median.
     * If the pivot lands perfectly on the median index, we stop immediately.
     * If it lands to the right or left, we recursively search ONLY the relevant half, 
     * discarding the other half completely.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N) average case. O(N&sup2;) worst case.</li>
     * <li><strong>Space Complexity:</strong> O(N) for cloning + O(log N) call stack.</li>
     * </ul>
     *
     * @param v Array of integers.
     * @return The median value.
     */
    public int medianQuickselect(int[] v) {
        if (v == null || v.length == 0) {
            throw new IllegalArgumentException("Cannot calculate the median of a null or empty array.");
        }
        
        // Prevent side-effects: Do not mutate the original array
        int[] copy = v.clone();
        int targetIndex = copy.length / 2;
        
        return quickselect(0, copy.length - 1, copy, targetIndex);
    }

    /**
     * Private Quickselect recursive helper.
     * Finds the k-th smallest element within the segment boundaries.
     */
    private int quickselect(int left, int right, int[] v, int k) { 
        // Base case: If the segment is only one element long, we've found it
        if (left == right) {
            return v[left];
        }

        // Partition the array segment around a pivot
        int pivotPosition = Util.partition(v, left, right);

        // Analyze where the pivot landed relative to our target 'k'
        if (pivotPosition == k) {
            // Jackpot: The pivot landed exactly on the median index.
            return v[pivotPosition];
        } else if (pivotPosition > k) {
            // The median must be to the left of the pivot. Discard the right half.
            return quickselect(left, pivotPosition - 1, v, k);
        } else {
            // The median must be to the right of the pivot. Discard the left half.
            return quickselect(pivotPosition + 1, right, v, k);
        }
    }
}