package topics.sorting.bubble;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Bidirectional Bubble Sort (Cocktail Shaker Sort)</h1>
 * <p>
 * An educational variation of the standard Bubble Sort. Instead of repeatedly 
 * passing through the list from left to right, this algorithm alternates passes 
 * from left-to-right and then right-to-left. This bidirectional approach helps 
 * to rapidly mitigate the "turtle" problem in standard Bubble Sort, where small 
 * elements at the end of the list take a long time to move to the beginning.
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity (Worst/Average):</strong> <code>O(N&sup2;)</code> - Still scales quadratically for heavily randomized or reversed arrays.</li>
 * <li><strong>Time Complexity (Best):</strong> <code>O(N)</code> - Achieved via the early-termination sentinel if the array is already sorted.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> Yes - Maintains the relative order of equal elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class BidirectionalBubble implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        int left = 0;
        int right = elements.length - 1;
        boolean hasChange = true;

        while (hasChange && (left < right)) {
            hasChange = false;

            // Forward Pass: Bubble the maximum element to the right boundary
            for (int i = left; i < right; i++) {
                if (elements[i] > elements[i + 1]) {
                    Util.swap(elements, i, i + 1);
                    hasChange = true;
                }
            }
            
            // Micro-optimization: If no swaps occurred moving forward, the array is perfectly sorted
            if (!hasChange) {
                break;
            }

            // Backward Pass: Bubble the minimum element to the left boundary
            for (int i = right - 1; i >= left; i--) {
                if (elements[i] > elements[i + 1]) {
                    Util.swap(elements, i, i + 1);
                    hasChange = true;
                }
            }

            // Narrow the boundaries as the extremities are now guaranteed to be in their final sorted positions
            left++;
            right--;
        }
    }
}