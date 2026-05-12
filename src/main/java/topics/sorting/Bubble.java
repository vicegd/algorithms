package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Bubble Sort Algorithm - Educational Sorting Implementation.
 * 
 * <h2>Algorithm Description</h2>
 * Bubble sort repeatedly steps through the list, compares adjacent elements,
 * and swaps them if they're in the wrong order. Larger elements "bubble" to
 * the end of the list with each pass, hence the name.
 * 
 * <h2>How It Works</h2>
 * <ol>
 *   <li>Start with the first element</li>
 *   <li>Compare it with the next element</li>
 *   <li>If current > next, swap them</li>
 *   <li>Move to next element and repeat</li>
 *   <li>After each pass, the largest unsorted element is in place</li>
 *   <li>Repeat for remaining unsorted portion</li>
 * </ol>
 * 
 * <h2>Complexity Analysis</h2>
 * <ul>
 *   <li><strong>Time Complexity:</strong> O(n²) in all cases (best, average, worst)</li>
 *   <li><strong>Space Complexity:</strong> O(1) - sorts in-place</li>
 *   <li><strong>Stable:</strong> Yes - maintains relative order of equal elements</li>
 *   <li><strong>Comparisons:</strong> n(n-1)/2</li>
 *   <li><strong>Swaps (worst case):</strong> n(n-1)/2</li>
 * </ul>
 * 
 * <h2>Example Trace</h2>
 * Initial array: [5, 2, 8, 1, 9]
 * <pre>
 * Pass 1: [2, 5, 1, 8, 9]  → 9 bubbles to end
 * Pass 2: [2, 1, 5, 8, 9]  → 8 in place
 * Pass 3: [1, 2, 5, 8, 9]  → 5 in place
 * Pass 4: [1, 2, 5, 8, 9]  → Done
 * </pre>
 * 
 * <h2>When to Use</h2>
 * <ul>
 *   <li>✓ Educational purposes (easiest sorting algorithm to understand)</li>
 *   <li>✓ Very small datasets (n < 50)</li>
 *   <li>✓ Nearly sorted data (with early termination optimization)</li>
 *   <li>✗ Large datasets (use Quicksort or Mergesort instead)</li>
 * </ul>
 * 
 * <h2>Advantages</h2>
 * <ul>
 *   <li>Simple to understand and implement</li>
 *   <li>Requires no extra memory (in-place)</li>
 *   <li>Stable sorting algorithm</li>
 *   <li>Easy to modify for special cases</li>
 * </ul>
 * 
 * <h2>Disadvantages</h2>
 * <ul>
 *   <li>Very inefficient for large datasets</li>
 *   <li>Worst-case time complexity: O(n²)</li>
 *   <li>Many unnecessary comparisons</li>
 *   <li>Better algorithms exist for real-world use</li>
 * </ul>
 * 
 * @author vicegd
 * @see topics.sorting.utils.ISortingAlgorithm
 * @see Bubble vs other sorting algorithms in the sorting module README
 */
public class Bubble implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(Bubble.class);
	
	/**
	 * Sorts an array of integers using bubble sort (without tracing).
	 * 
	 * <p>This is the standard bubble sort implementation that sorts the array
	 * in-place without logging intermediate steps. Use this when you don't need
	 * to see the algorithm's progression.</p>
	 * 
	 * <h3>Algorithm Steps</h3>
	 * <ol>
	 *   <li>For i from 1 to n-1:</li>
	 *   <li>&nbsp;&nbsp;For j from n-1 down to i:</li>
	 *   <li>&nbsp;&nbsp;&nbsp;&nbsp;If elements[j-1] > elements[j]:</li>
	 *   <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Swap elements[j-1] and elements[j]</li>
	 * </ol>
	 * 
	 * <h3>Why This Order?</h3>
	 * We iterate backwards through the unsorted portion because this places
	 * the smallest element in its correct position after each inner loop pass.
	 * 
	 * @param elements the array to sort (modified in-place)
	 * @throws NullPointerException if elements is null
	 * 
	 * @example
	 * <pre>
	 * int[] array = {5, 2, 8, 1, 9};
	 * bubbleSort.sort(array);
	 * // array is now: {1, 2, 5, 8, 9}
	 * </pre>
	 */
	@Override
	public void sort(int[] elements) {
		for (int i = 1; i < elements.length; i++) {
			for (int j = elements.length - 1; j >= i; j--) {
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
				}
			}
		}
	}
	
	/**
	 * Sorts an array of integers using bubble sort with execution tracing.
	 * 
	 * <p>This variant logs intermediate states of the array during sorting.
	 * Useful for understanding the algorithm's progression or educational demonstrations.</p>
	 * 
	 * <h3>Tracing Output</h3>
	 * After each pass through the unsorted portion, the current state of the array
	 * is logged if tracing is enabled. This shows how the array evolves:
	 * <pre>
	 * Initial: [5, 2, 8, 1, 9]
	 * After pass 1: [2, 5, 1, 8, 9]  (9 in place)
	 * After pass 2: [2, 1, 5, 8, 9]  (8 in place)
	 * After pass 3: [1, 2, 5, 8, 9]  (5 in place)
	 * </pre>
	 * 
	 * @param elements the array to sort (modified in-place)
	 * @param trace if true, logs intermediate states via SLF4J debug level;
	 *              if false, no tracing occurs (behaves like {@link #sort(int[])})
	 * @throws NullPointerException if elements is null
	 * 
	 * @see #sort(int[]) for sorting without tracing
	 * @see Util#trace(int, int[]) for tracing implementation
	 * 
	 * @example
	 * <pre>
	 * int[] array = {5, 2, 8, 1, 9};
	 * bubbleSort.sort(array, true);  // Logs each pass
	 * // Logs show progression: [5,2,8,1,9] → [2,5,1,8,9] → ... → [1,2,5,8,9]
	 * </pre>
	 */
	@Override
	public void sort(int[] elements, boolean trace) {
		log.debug("Bubble method");
		for (int i = 1; i < elements.length; i++) {
			for (int j = elements.length - 1; j >= i; j--) {
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
				}
			}
			if (trace) Util.trace(i, elements);
		}
	}

}

