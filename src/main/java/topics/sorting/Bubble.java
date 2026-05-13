package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Bubble Sort Algorithm - Educational Sorting Implementation.
 *
 * Algorithm Description:
 * Bubble sort repeatedly steps through the list, compares adjacent elements,
 * and swaps them if they are in the wrong order. Larger elements "bubble" to
 * the end of the list with each pass, hence the name.
 *
 * How It Works:
 * 1. Start with the first element
 * 2. Compare it with the next element
 * 3. If current > next, swap them
 * 4. Move to next element and repeat
 * 5. After each pass, the largest unsorted element is in place
 * 6. Repeat for remaining unsorted portion
 *
 * Complexity Analysis:
 * - Time Complexity: O(n^2) in all cases (best, average, worst)
 * - Space Complexity: O(1) - sorts in-place
 * - Stable: Yes - maintains relative order of equal elements
 * - Comparisons: n(n-1)/2
 * - Swaps (worst case): n(n-1)/2
 *
 * Example Trace for [5, 2, 8, 1, 9]:
 *   Pass 1: [2, 5, 1, 8, 9]  -> 9 bubbles to end
 *   Pass 2: [2, 1, 5, 8, 9]  -> 8 in place
 *   Pass 3: [1, 2, 5, 8, 9]  -> 5 in place
 *   Pass 4: [1, 2, 5, 8, 9]  -> Done
 *
 * When to Use:
 * - Educational purposes (easiest sorting algorithm to understand)
 * - Very small datasets (n < 50)
 * - Nearly sorted data (with early termination optimization)
 * - Not recommended for large datasets (use Quicksort or Mergesort instead)
 *
 * Advantages:
 * - Simple to understand and implement
 * - Requires no extra memory (in-place)
 * - Stable sorting algorithm
 * - Easy to modify for special cases
 *
 * Disadvantages:
 * - Very inefficient for large datasets
 * - Worst-case time complexity: O(n^2)
 * - Many unnecessary comparisons
 * - Better algorithms exist for real-world use
 *
 * @author vicegd
 * @see topics.sorting.utils.ISortingAlgorithm
 */
public class Bubble implements ISortingAlgorithm{
  static Logger log = LoggerFactory.getLogger(Bubble.class);
  
  /**
   * Sorts an array of integers using bubble sort (without tracing).
   *
   * This is the standard bubble sort implementation that sorts the array
   * in-place without logging intermediate steps.
   *
   * Algorithm Steps:
   * 1. For i from 1 to n-1:
   * 2.   For j from n-1 down to i:
   * 3.     If elements[j-1] > elements[j]: swap elements[j-1] and elements[j]
   *
   * Why This Order:
   * We iterate backwards through the unsorted portion because this places
   * the smallest element in its correct position after each inner loop pass.
   *
   * Example:
   *   int[] array = {5, 2, 8, 1, 9};
   *   bubbleSort.sort(array);
   *   // array is now: {1, 2, 5, 8, 9}
   *
   * @param elements the array to sort (modified in-place)
   * @throws NullPointerException if elements is null
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
   * This variant logs intermediate states of the array during sorting.
   * Useful for understanding the algorithm's progression or educational demonstrations.
   *
   * Tracing Output:
   * After each pass, the current state of the array is logged if tracing is enabled:
   *   Initial:      [5, 2, 8, 1, 9]
   *   After pass 1: [2, 5, 1, 8, 9]  (9 in place)
   *   After pass 2: [2, 1, 5, 8, 9]  (8 in place)
   *   After pass 3: [1, 2, 5, 8, 9]  (5 in place)
   *
   * @param elements the array to sort (modified in-place)
   * @param trace if true, logs intermediate states via SLF4J debug level;
   *              if false, no tracing occurs (behaves like {@link #sort(int[])})
   * @throws NullPointerException if elements is null
   * @see #sort(int[]) for sorting without tracing
   * @see Util#trace(int, int[]) for tracing implementation
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

