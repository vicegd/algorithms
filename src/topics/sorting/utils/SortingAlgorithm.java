
package topics.sorting.utils;

/**
 * <h1>Sorting Algorithm Contract</h1>
 * <p>
 * Defines the standard API for all sorting algorithms in the repository.
 * </p>
 *
 * @author vicegd
 */
public interface SortingAlgorithm {
    
    /**
     * Sorts the elements in-place.
     *
     * @param elements The array of integers to be sorted.
     */
    void sort(int[] elements);
}