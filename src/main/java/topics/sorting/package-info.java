/**
 * Sorting algorithms — from simple quadratic to optimal linearithmic.
 *
 * <p>Sorting is one of the most studied problems in computer science.
 * This package provides implementations of the major comparison-based sorting
 * algorithms, covering the spectrum from O(n²) educational sorts to
 * O(n log n) efficient sorts, plus a non-comparison-based radix sort.</p>
 *
 * <h2>Algorithm Comparison</h2>
 * <table>
 *   <caption>Complexity and property summary</caption>
 *   <tr><th>Algorithm</th><th>Best</th><th>Average</th><th>Worst</th><th>Space</th><th>Stable</th></tr>
 *   <tr><td>Bubble</td><td>O(n²)</td><td>O(n²)</td><td>O(n²)</td><td>O(1)</td><td>Yes</td></tr>
 *   <tr><td>Improved Bubble</td><td>O(n)</td><td>O(n²)</td><td>O(n²)</td><td>O(1)</td><td>Yes</td></tr>
 *   <tr><td>Direct Insertion</td><td>O(n)</td><td>O(n²)</td><td>O(n²)</td><td>O(1)</td><td>Yes</td></tr>
 *   <tr><td>Direct Selection</td><td>O(n²)</td><td>O(n²)</td><td>O(n²)</td><td>O(1)</td><td>No</td></tr>
 *   <tr><td>Quicksort</td><td>O(n log n)</td><td>O(n log n)</td><td>O(n²)</td><td>O(log n)</td><td>No</td></tr>
 *   <tr><td>Mergesort</td><td>O(n log n)</td><td>O(n log n)</td><td>O(n log n)</td><td>O(n)</td><td>Yes</td></tr>
 * </table>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.sorting.Bubble} — classic bubble sort</li>
 *   <li>{@link topics.sorting.ImprovedBubble} — bubble sort with early-exit optimisation</li>
 *   <li>{@link topics.sorting.DirectInsertion} — insertion sort</li>
 *   <li>{@link topics.sorting.DirectSelection} — selection sort</li>
 *   <li>{@link topics.sorting.Quicksort} — in-place quicksort with partition</li>
 *   <li>{@link topics.sorting.Mergesort} — top-down merge sort (stable)</li>
 * </ul>
 */
package topics.sorting;
