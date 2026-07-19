package topics.sorting.mergesort;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Mergesort</h1>
 * <p>
 * A classic divide-and-conquer algorithm. It works by recursively breaking down 
 * an array into two halves until each sub-array consists of a single element, 
 * and then merging those sub-arrays back together in a strictly sorted order.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li><strong>Divide:</strong> Find the midpoint of the array to divide it into two halves.</li>
 * <li><strong>Conquer:</strong> Recursively call Mergesort for the left and right halves.</li>
 * <li><strong>Combine (Merge):</strong> Merge the two sorted halves into a single sorted sequence using an auxiliary array.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N log N)</code> strictly in all cases (Best, Average, Worst).</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Requires an auxiliary array of the same size as the input to merge elements securely.</li>
 * <li><strong>Stability:</strong> Yes - Maintains the relative order of equal elements during the merge phase.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class Mergesort implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        // Memory Optimization: Allocate a SINGLE auxiliary array once, rather than 
        // allocating thousands of small arrays during every recursive merge step.
        int[] auxiliarySpace = new int[elements.length];
        
        mergeSortRecursive(elements, auxiliarySpace, 0, elements.length - 1);
    }

    /**
     * Recursively divides the array into halves.
     */
    private void mergeSortRecursive(int[] elements, int[] aux, int left, int right) {
        if (left < right) {
            // Prevents potential integer overflow mathematically
            int center = left + (right - left) / 2;
            
            // Sort left and right halves
            mergeSortRecursive(elements, aux, left, center);
            mergeSortRecursive(elements, aux, center + 1, right);
            
            // Merge the two sorted halves
            combine(elements, aux, left, center, right);
        }
    }

    /**
     * Combines two sorted sub-arrays into a single strictly sorted segment.
     * Operates in O(N) time for the size of the segment.
     *
     * @param elements The primary array being sorted.
     * @param aux      The pre-allocated auxiliary memory array.
     * @param left     Starting index of the left segment.
     * @param center   Ending index of the left segment.
     * @param right    Ending index of the right segment.
     */
    private void combine(int[] elements, int[] aux, int left, int center, int right) {
        // Copy the target segment into the auxiliary array for safe reading
        for (int i = left; i <= right; i++) {
            aux[i] = elements[i];
        }

        int indexLeft = left;           // Pointer for the left sub-array
        int indexRight = center + 1;    // Pointer for the right sub-array
        int currentIndex = left;        // Pointer for writing back to the original array

        // Compare and merge the two halves back into the original array
        while (indexLeft <= center && indexRight <= right) {
            if (aux[indexLeft] <= aux[indexRight]) {
                elements[currentIndex] = aux[indexLeft];
                indexLeft++;
            } else {
                elements[currentIndex] = aux[indexRight];
                indexRight++;
            }
            currentIndex++;
        }

        // Copy any remaining elements from the left side.
        // (We don't need to copy the right side, as it is already in its final position in the original array).
        while (indexLeft <= center) {
            elements[currentIndex] = aux[indexLeft];
            currentIndex++;
            indexLeft++;
        }
    }
}