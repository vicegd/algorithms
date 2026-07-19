package topics.sorting.selection;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Direct Selection Sort</h1>
 * <p>
 * An educational sorting implementation that divides the input list into two parts: 
 * a sorted sublist built up from left to right, and a sublist of the remaining 
 * unsorted items. The algorithm proceeds by finding the smallest element in the 
 * unsorted sublist and swapping it with the leftmost unsorted element.
 * </p>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N&sup2;)</code> strictly in all cases (Best, Average, Worst). It must always scan the entire remaining array to guarantee it has found the absolute minimum.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> No - Swapping non-adjacent elements across the array can change the relative order of equal elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class DirectSelection implements SortingAlgorithm {

    @Override  
    public void sort(int[] elements) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        // 'i' marks the boundary between the sorted (left) and unsorted (right) portions
        for (int i = 0; i < elements.length - 1; i++) {
            
            // Find the index of the absolute minimum element in the unsorted portion O(N)
            int posMin = Util.findPosMin(elements, i); 
            
            // Micro-optimization: Only execute memory writes if the minimum is not already in place
            if (i != posMin) {
                Util.swap(elements, i, posMin);
            }
        }
    }
}