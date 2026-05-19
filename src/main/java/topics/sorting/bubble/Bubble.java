package topics.sorting.bubble;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.SortingAlgorithm;
import topics.sorting.Util;

/**
 * <h1>Bubble Sort (Left-Bubbling)</h1>
 * <p>
 * Educational sorting implementation without early-termination optimizations.
 * This specific variant iterates backwards, causing the smallest elements to 
 * "bubble" up to the beginning (left side) of the array with each pass.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Establish a boundary for the sorted portion at the beginning of the array.</li>
 * <li>Start at the very end of the array and iterate backwards to the sorted boundary.</li>
 * <li>Compare the current element with the element immediately before it.</li>
 * <li>If they are out of order (previous > current), swap them.</li>
 * <li>After each full pass, the absolute minimum of the unsorted portion is guaranteed to be placed at the sorted boundary.</li>
 * <li>Repeat strictly <code>N-1</code> times.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N&sup2;)</code> strictly in all cases (Best, Average, and Worst) because the early-termination flag is deliberately omitted.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Sorts entirely in-place.</li>
 * <li><strong>Stability:</strong> Yes - Maintains relative order of equal elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class Bubble implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(Bubble.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to the traceable method with tracing disabled
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return; // Array is trivial; no sorting required
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating strictly O(N^2) left-bubbling Bubble Sort execution");
        }

        // The outer loop defines the expanding boundary of the sorted array on the left (index i)
        for (int i = 0; i < elements.length - 1; i++) {
            
            // The inner loop iterates backwards, pushing the smallest element to the boundary 'i'
            for (int j = elements.length - 1; j > i; j--) {
                if (elements[j - 1] > elements[j]) {
                    Util.swap(elements, j - 1, j);
                }
            }
            
            if (trace) {
                // Log the state of the array after the smallest element has been secured
                Util.trace(i + 1, elements);
            }
        }
    }
}