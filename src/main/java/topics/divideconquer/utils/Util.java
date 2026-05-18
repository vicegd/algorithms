package topics.divideconquer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Divide & Conquer Utility</h1>
 * <p>
 * Provides foundational array manipulation and partitioning algorithms 
 * utilized by QuickSort, QuickSelect, and other Divide and Conquer strategies.
 * </p>
 *
 * @author vicegd
 */
public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);

    // Hide public constructor to enforce static utility usage
    private Util() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * <h2>Lomuto Partition Scheme (with Mid-Pivot)</h2>
     * <p>
     * Rearranges the subarray {@code elements[left..right]} in-place. It selects 
     * the middle element as the pivot, moves it to the front to "hide" it, and 
     * then iterates through the array. All elements less than or equal to the 
     * pivot are pushed to the left side boundary. Finally, the pivot is restored 
     * to its mathematically correct absolute position.
     * </p>
     *
     * <h3>Complexity</h3>
     * <ul>
     * <li><strong>Time:</strong> {@code O(N)} strictly, where N is {@code right - left}.</li>
     * <li><strong>Space:</strong> {@code O(1)} in-place swaps.</li>
     * </ul>
     *
     * @param elements The array to be partitioned.
     * @param left     The starting index of the subarray.
     * @param right    The ending index of the subarray.
     * @return The final, absolute index of the pivot element.
     */
    public static int partition(int[] elements, int left, int right) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        if (left < 0 || right >= elements.length || left >= right) {
            return left; // Base case or invalid bounds fallback
        }

        // 1. Prevent integer overflow mathematically when finding the middle
        int mid = left + (right - left) / 2;

        // 2. Hide the pivot at the beginning of the segment
        swap(elements, mid, left);
        int pivotValue = elements[left];
        
        // 'i' marks the boundary of the elements smaller than the pivot
        int i = left;

        // 3. Scan the segment and push smaller elements to the left boundary
        for (int s = left + 1; s <= right; s++) {
            if (elements[s] <= pivotValue) {
                i++;
                swap(elements, i, s);
            }
        }

        // 4. Restore the pivot to its final mathematically correct position
        swap(elements, left, i);

        if (log.isTraceEnabled()) {
            log.trace("Partitioned segment [{} - {}] around pivot {} at final index {}", left, right, pivotValue, i);
        }

        return i; // Returns the position where the pivot settled
    }

    /**
     * Swaps two elements within an array. Operates in O(1) time.
     *
     * @param elements The target array.
     * @param i        The first index.
     * @param j        The second index.
     */
    public static void swap(int[] elements, int i, int j) {
        if (i != j) {
            int temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }
    }
}