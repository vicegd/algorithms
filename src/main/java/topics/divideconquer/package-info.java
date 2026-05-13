/**
 * Divide-and-Conquer algorithms — break, solve, and combine.
 *
 * <p>Divide and Conquer solves problems by recursively splitting them into
 * smaller sub-problems of the same type, solving each sub-problem independently,
 * and then merging the partial results into a final answer.</p>
 *
 * <h2>Recurrence and the Master Theorem</h2>
 * <p>The running time of a divide-and-conquer algorithm typically satisfies:</p>
 * <pre>
 * T(n) = a &middot; T(n/b) + f(n)
 * </pre>
 * <p>where {@code a} is the number of sub-problems, {@code b} is the size reduction
 * factor, and {@code f(n)} is the cost of dividing / combining.</p>
 *
 * <h2>Distinction from Dynamic Programming</h2>
 * <ul>
 *   <li><strong>Divide &amp; Conquer</strong> — sub-problems are independent; solved once.</li>
 *   <li><strong>Dynamic Programming</strong> — sub-problems overlap; results are cached.</li>
 * </ul>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.divideconquer.BinarySearch} — O(log n) search in sorted arrays</li>
 *   <li>{@link topics.divideconquer.Factorial} — recursive factorial</li>
 *   <li>{@link topics.divideconquer.Fibonacci} — recursive Fibonacci</li>
 *   <li>{@link topics.divideconquer.GCG} — greatest common divisor (Euclidean)</li>
 *   <li>{@link topics.divideconquer.MajoritarianElement} — majority element by D&amp;C</li>
 *   <li>{@link topics.divideconquer.MaxSum} — maximum subarray sum</li>
 *   <li>{@link topics.divideconquer.Median} — median of two sorted arrays</li>
 *   <li>{@link topics.divideconquer.Mergesort} — O(n log n) stable sort</li>
 *   <li>{@link topics.divideconquer.Mode} — mode by D&amp;C</li>
 *   <li>{@link topics.divideconquer.SequentialSearch} — linear search baseline</li>
 *   <li>{@link topics.divideconquer.VectorSum} — parallel vector sum</li>
 * </ul>
 */
package topics.divideconquer;
