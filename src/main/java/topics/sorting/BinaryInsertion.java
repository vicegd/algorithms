package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Binary Insertion Sort Algorithm</h1>
 * <p>
 * An educational optimization of the standard Direct Insertion Sort. 
 * Instead of sequentially scanning backwards to find the correct insertion 
 * point for the new element (the key), this variant employs a <strong>Binary Search</strong> 
 * upon the already sorted sub-array.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Assume the first element (index 0) is sorted.</li>
 * <li>Pick the next element as the <code>key</code>.</li>
 * <li>Use Binary Search <code>O(log N)</code> on the sorted left portion to find the exact insertion index.</li>
 * <li>Shift all elements from the insertion index up to the current boundary one position to the right.</li>
 * <li>Place the key into the newly vacated position.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity (Comparisons):</strong> <code>O(N log N)</code> - Binary search drastically reduces the number of comparisons.</li>
 * <li><strong>Time Complexity (Swaps/Shifts):</strong> <code>O(N&sup2;)</code> - Moving elements in an array still requires linear shifting, dominating the overall time complexity in average/worst cases.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> Yes - Because the binary search condition <code>(key < elements[center])</code> forces the algorithm to place equal elements to the right of existing ones.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.utils.SortingAlgorithm
 */
public class BinaryInsertion implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(BinaryInsertion.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to prevent code duplication
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating Binary Insertion Sort execution");
        }

        // 'i' marks the boundary of the sorted portion
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int left = 0;
            int right = i - 1;

            // Phase 1: Binary Search to find the exact insertion index
            while (left <= right) {
                // Prevents potential integer overflow compared to (left + right) / 2
                int center = left + (right - left) / 2; 
                
                if (key < elements[center]) {
                    right = center - 1;
                } else {
                    left = center + 1;
                }
            }

            // Phase 2: Shift array elements to the right to create the gap
            for (int j = i - 1; j >= left; j--) {
                elements[j + 1] = elements[j];
            }

            // Phase 3: Insert the key into its mathematical position
            elements[left] = key;

            if (trace) {
                Util.trace(i, elements);
            }
        }
    }
}