package topics.sorting.bubble;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Bubble Sort with Sentinel</h1>
 * <p>
 * An optimized variant of the left-bubbling sort. It introduces a "sentinel" 
 * boolean flag to monitor if any mathematical swaps occurred during the current 
 * array traversal. If a full pass completes without any swaps, the algorithm 
 * deduces that the array is already completely sorted and terminates early.
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity (Worst/Average):</strong> <code>O(N&sup2;)</code> - Occurs when the array is in reverse order.</li>
 * <li><strong>Time Complexity (Best):</strong> <code>O(N)</code> - Achieved directly due to the Sentinel flag if the array is already sorted.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> Yes - Maintains the relative order of equal elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class BubbleSentinel implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        int i = 1;
        boolean hasChange = true;

        // The loop continues as long as the array boundary hasn't been reached
        // AND a swap occurred in the previous algorithmic iteration.
        while (hasChange && (i < elements.length)) {
            hasChange = false;

            // Iterate backwards, pushing the smallest unsorted element to the left boundary
            for (int j = elements.length - 1; j >= i; j--) {
                if (elements[j - 1] > elements[j]) {
                    Util.swap(elements, j - 1, j);
                    hasChange = true; // Trigger the sentinel flag
                }
            }
            
            i++;
        }
    }
}