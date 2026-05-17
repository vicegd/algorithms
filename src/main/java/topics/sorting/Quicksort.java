package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Quicksort Algorithm (Median-of-Three)</h1>
 * <p>
 * A highly efficient, divide-and-conquer sorting algorithm. This implementation 
 * utilizes the "Median-of-Three" heuristic for pivot selection to heavily mitigate 
 * the risk of encountering the worst-case performance on already sorted arrays.
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Select a pivot using the median value among the left, center, and right elements.</li>
 * <li>Partition the array so that elements smaller than the pivot are moved to its left, and larger elements to its right.</li>
 * <li>Recursively apply the same logic to the left and right sub-arrays.</li>
 * <li>Base case: Sub-arrays of 3 or fewer elements are sorted almost instantly by the median-of-three logic.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity (Average/Best):</strong> <code>O(N log N)</code> - The partition generally halves the array.</li>
 * <li><strong>Time Complexity (Worst):</strong> <code>O(N&sup2;)</code> - Occurs if the pivot is repeatedly the absolute maximum/minimum (highly unlikely with Median-of-Three).</li>
 * <li><strong>Space Complexity:</strong> <code>O(log N)</code> - Stack memory required for the recursive calls.</li>
 * <li><strong>Stability:</strong> No - Swapping elements across the pivot boundary disrupts the relative order of equal elements.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.utils.SortingAlgorithm
 */
public class Quicksort implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(Quicksort.class);

    @Override
    public void sort(int[] elements) {
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }
        
        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating Quicksort execution (Median-of-Three)");
        }
        
        quickSortRecursive(elements, 0, elements.length - 1, 1, trace);
    }

    /**
     * Determines the median-of-three pivot and partially orders the boundaries.
     *
     * @param elements The array being sorted.
     * @param left     The left boundary index.
     * @param right    The right boundary index.
     * @return The index of the selected median element.
     */
    private int medianOfThree(int[] elements, int left, int right) { 
        int center = left + (right - left) / 2; // Prevents potential integer overflow
        
        // Partially sort the three target elements to guarantee: left <= center <= right
        if (elements[left] > elements[center]) {
            Util.swap(elements, left, center);
        }
        if (elements[left] > elements[right]) {
            Util.swap(elements, left, right);
        }
        if (elements[center] > elements[right]) {
            Util.swap(elements, center, right);
        }
        
        return center;
    }

    /**
     * Internal recursive execution of the Quicksort partitioning logic.
     */
    private void quickSortRecursive(int[] elements, int left, int right, int level, boolean trace) {
        if (left >= right) {
            return; // Base case: Segment is 1 element or invalid
        }

        int center = medianOfThree(elements, left, right);
        
        // If the segment has more than 3 elements, proceed with full partitioning
        if ((right - left) >= 3) { 
            int pivot = elements[center]; 
            
            // Hide the pivot at the right boundary to keep it out of the partition logic
            Util.swap(elements, center, right); 
            
            int i = left;
            int j = right - 1;

            do {         
                while (elements[i] <= pivot && i < right) i++; // Scan from left for larger elements
                while (elements[j] >= pivot && j > left) j--;  // Scan from right for smaller elements
                
                if (i < j) {
                    Util.swap(elements, i, j);
                }
            } while (i < j);   

            // Restore the pivot to its mathematically correct and final position
            Util.swap(elements, i, right);
            
            if (trace) {
                Util.traceMessage("Level: " + level + " Pivot placed: " + pivot, elements);
            }
            
            // Recursively sort the dynamically created sub-arrays
            quickSortRecursive(elements, left, i - 1, level + 1, trace);
            quickSortRecursive(elements, i + 1, right, level + 1, trace);
        } else {
            // Segment of 3 or fewer elements is already sorted by medianOfThree
            if (trace) {
                Util.traceMessage("Level: " + level + " (Base case sorted)", elements);
            }
        }
    }
}