package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Direct Insertion Sort</h1>
 * <p>
 * An educational sorting implementation that builds the final sorted array 
 * one item at a time. It operates similarly to how one might sort a hand of 
 * playing cards: picking an element and inserting it into its mathematically 
 * correct position among the already sorted elements to its left.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Assume the first element (index 0) is already sorted.</li>
 * <li>Pick the next element, referred to as the <code>key</code>.</li>
 * <li>Compare the key with elements in the sorted sub-array to its left.</li>
 * <li>Shift all elements strictly greater than the key one position to the right.</li>
 * <li>Insert the key into the newly vacated correct position.</li>
 * <li>Repeat until the boundary reaches the end of the array.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity (Worst/Average):</strong> <code>O(N&sup2;)</code> - Occurs when the array is in reverse order.</li>
 * <li><strong>Time Complexity (Best):</strong> <code>O(N)</code> - Occurs when the array is already sorted (the inner <code>while</code> loop never executes).</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place without recursion overhead.</li>
 * <li><strong>Stability:</strong> Yes - Maintains the relative order of equal elements because it only shifts strictly greater elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.utils.SortingAlgorithm
 */
public class DirectInsertion implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(DirectInsertion.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to the traceable method with tracing disabled to prevent logic duplication
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating Direct Insertion Sort execution");
        }

        // 'i' marks the boundary between the sorted (left) and unsorted (right) portions
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int j = i - 1;
            
            // Shift elements of the sorted segment to the right to create space for the key
            while (j >= 0 && key < elements[j]) {
                elements[j + 1] = elements[j];
                j--;
            }
            
            // Insert the key into its mathematically correct sorted position
            elements[j + 1] = key;
            
            if (trace) {
                Util.trace(i, elements);
            }
        }
    }
}