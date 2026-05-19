
package topics.sorting;

/**
 * <h1>Sorting Algorithm Contract</h1>
 * <p>
 * Defines the standard API for all sorting algorithms in the repository.
 * Provides options for standard execution or step-by-step traceable 
 * execution for educational visualization.
 * </p>
 *
 * @author vicegd
 */
public interface SortingAlgorithm {
    
    /**
     * Sorts the elements in-place silently.
     *
     * @param elements The array of integers to be sorted.
     */
    void sort(int[] elements);
  
    /**
     * Sorts the elements in-place, optionally emitting step-by-step 
     * traces to visualize the algorithmic progression.
     *
     * @param elements The array of integers to be sorted.
     * @param trace    If {@code true}, logs the internal state during execution.
     */
    void sort(int[] elements, boolean trace);
}