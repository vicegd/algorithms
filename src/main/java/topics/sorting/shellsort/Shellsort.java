package topics.sorting.shellsort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.SortingAlgorithm;
import topics.sorting.Util;

/**
 * <h1>Shellsort</h1>
 * <p>
 * An highly efficient optimization of Direct Insertion Sort. It overcomes the 
 * limitation of Insertion Sort (where elements only move one position at a time) 
 * by comparing and moving elements that are separated by a specific "gap". 
 * The gap is progressively reduced until it reaches 1, at which point the 
 * algorithm behaves exactly like a standard Insertion Sort, but on an array 
 * that is already nearly sorted.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Determine the initial gap size (e.g., <code>N / 2</code>).</li>
 * <li>Perform a "gapped" insertion sort: compare elements separated by the gap.</li>
 * <li>Shift elements separated by the gap to the right to make room for the <code>key</code>.</li>
 * <li>Reduce the gap size (e.g., divide by 2) and repeat.</li>
 * <li>The final pass is always performed with a gap of 1, guaranteeing a fully sorted array.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> Highly dependent on the gap sequence. Using Shell's original sequence (N/2), the worst-case is <code>O(N&sup2;)</code>. With Hibbard's or Knuth's sequences, it can drop to <code>O(N^(3/2))</code> or <code>O(N^(4/3))</code>.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> No - Elements leaping across large gaps will jump over equal elements, destroying relative order.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class Shellsort implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(Shellsort.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to the traceable method to avoid duplicating the core logic
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating Shellsort execution");
        }

        int n = elements.length;

        // Modernization: Dynamic gap sequence (Shell's original sequence: N/2, N/4, ..., 1)
        // This ensures the algorithm is robust and efficient for arrays of ANY length.
        for (int gap = n / 2; gap > 0; gap /= 2) {
            
            // Perform a gapped insertion sort for this specific gap size
            for (int i = gap; i < n; i++) {
                int key = elements[i];
                int j = i - gap;
                
                // Shift previously sorted elements up by the 'gap' to make room
                while (j >= 0 && key < elements[j]) {
                    elements[j + gap] = elements[j];
                    j -= gap;
                }
                
                // Insert the key into its mathematically correct gapped position
                elements[j + gap] = key;
                
                if (trace) {
                    Util.traceShellSort(gap, i, elements);
                }
            }
        }
    }
}