package topics.sorting.utils;

/**
 * Interface for sorting algorithms
 * @author viceg
 */
public interface ISortingAlgorithm {
	/**
	 * Sorts elements without tracing anything
	 * @param elements Array of numbers to be sorted
	 */
	void sort(int[] elements);
	
	/**
	 * Sorts elements with the possibility of tracing the operation
	 * @param elements Array of numbers to be sorted
	 * @param trace Whether to trace the operation performed
	 */
	void sort(int[] elements, boolean trace);
}
