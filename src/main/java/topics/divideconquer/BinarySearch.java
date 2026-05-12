package topics.divideconquer;

/**
 * Binary Search implementation for finding an element in a sorted array.
 * 
 * <h2>Algorithm Overview</h2>
 * Implements the classic binary search algorithm using two approaches:
 * <ul>
 *   <li>Iterative: Uses a while loop, O(1) space complexity</li>
 *   <li>Recursive: Uses method recursion, O(log n) space for call stack</li>
 * </ul>
 * 
 * <h2>Divide & Conquer Strategy</h2>
 * <ol>
 *   <li><strong>Divide:</strong> Split array into two halves at midpoint</li>
 *   <li><strong>Conquer:</strong> Compare target with middle element</li>
 *   <li><strong>Combine:</strong> Recursively search appropriate half</li>
 * </ol>
 * 
 * <h2>Time Complexity</h2>
 * <ul>
 *   <li>Best case: O(1) - element at middle position</li>
 *   <li>Average case: O(log n)</li>
 *   <li>Worst case: O(log n) - element at end or not found</li>
 *   <li>Space (iterative): O(1)</li>
 *   <li>Space (recursive): O(log n) for recursion stack</li>
 * </ul>
 * 
 * <h2>Precondition</h2>
 * <strong>Array must be sorted in ascending order!</strong> Binary search requires a sorted input.
 * 
 * <h2>Example Usage</h2>
 * <pre>
 * int[] sortedArray = {1, 3, 5, 7, 9, 11, 13};
 * BinarySearch searcher = new BinarySearch();
 * 
 * int position = searcher.binarySearch1(sortedArray, 7);  // Returns 3
 * int notFound = searcher.binarySearch1(sortedArray, 6);  // Returns Integer.MIN_VALUE
 * </pre>
 * 
 * @author vicegd
 * @see #binarySearch1(int[], int) Iterative implementation
 * @see #binarySearch2(int[], int) Recursive implementation
 */
public class BinarySearch {
	/**
	 * Iterative binary search implementation.
	 * 
	 * <p>Uses a while loop to repeatedly divide search space in half.
	 * More efficient than recursive version (no stack overhead).</p>
	 * 
	 * <h3>Algorithm Steps</h3>
	 * <ol>
	 *   <li>Initialize left = 0, right = array length - 1</li>
	 *   <li>While left ≤ right:
	 *     <ul>
	 *       <li>Calculate middle = (left + right) / 2</li>
	 *       <li>If array[middle] == target: Return middle</li>
	 *       <li>If array[middle] > target: Search left half (right = middle - 1)</li>
	 *       <li>If array[middle] < target: Search right half (left = middle + 1)</li>
	 *     </ul>
	 *   </li>
	 *   <li>If element not found: Return Integer.MIN_VALUE</li>
	 * </ol>
	 * 
	 * @param v the sorted array to search in (must be in ascending order)
	 * @param x the value to search for
	 * @return the index of x in array v, or Integer.MIN_VALUE if not found
	 * 
	 * @throws NullPointerException if array v is null
	 * 
	 * @example
	 * // Searching for element 5 in array {1, 3, 5, 7, 9}
	 * int index = binarySearch1(new int[]{1, 3, 5, 7, 9}, 5);  // Returns 2
	 */
	public int binarySearch1(int[]v, int x) {
		int left = 0;
		int right = v.length-1;
		int center;
		while (left <= right) {
			center = (left+right) / 2;
			if (v[center]==x) 
				return center;
			else if (v[center]>x) //the element is on the left
				right=center-1;
			else left=center+1;  //the element is on the right	 	 	       
		}
		return Integer.MIN_VALUE; //x does not exist
	}
	
	/**
	 * Recursive binary search implementation.
	 * 
	 * <p>Uses method recursion to divide search space. Demonstrates
	 * the recursive divide-and-conquer pattern, though iterative version
	 * is generally preferred due to lower memory overhead.</p>
	 * 
	 * <h3>Recurrence Relation</h3>
	 * T(n) = T(n/2) + O(1) → <strong>O(log n)</strong>
	 * 
	 * <h3>Why Divide & Conquer Works</h3>
	 * <ul>
	 *   <li><strong>Optimal substructure:</strong> Solution to finding x in array
	 *       depends on finding x in left or right half</li>
	 *   <li><strong>Independent subproblems:</strong> Left and right searches
	 *       don't interfere with each other</li>
	 *   <li><strong>Exponential speedup:</strong> Each step eliminates half the
	 *       remaining elements → logarithmic total steps</li>
	 * </ul>
	 * 
	 * @param v the sorted array to search in (must be in ascending order)
	 * @param x the value to search for
	 * @return the index of x in array v, or Integer.MIN_VALUE if not found
	 * 
	 * @see #binarySearch1(int[], int) For iterative alternative
	 * 
	 * @example
	 * // Recursively find element 7 in array {1, 3, 5, 7, 9, 11, 13}
	 * int index = binarySearch2(new int[]{1, 3, 5, 7, 9, 11, 13}, 7);  // Returns 3
	 */
	public int binarySearch2(int[]v,int x) {
		return searchByDivision(0, v.length-1, v, x);
	}
	
	/**
	 * Private recursive helper for binary search.
	 * 
	 * <p>This is the actual recursive implementation. It maintains the bounds
	 * of the search space (left and right indices) and recursively narrows them
	 * until the element is found or the search space is empty.</p>
	 * 
	 * <h3>Base Cases</h3>
	 * <ul>
	 *   <li>If left > right: Search space exhausted, return Integer.MIN_VALUE</li>
	 *   <li>If array[center] == x: Element found, return center</li>
	 * </ul>
	 * 
	 * <h3>Recursive Cases</h3>
	 * <ul>
	 *   <li>If array[center] > x: Recursively search left half</li>
	 *   <li>If array[center] < x: Recursively search right half</li>
	 * </ul>
	 * 
	 * @param left the left boundary (inclusive) of current search space
	 * @param right the right boundary (inclusive) of current search space
	 * @param v the array being searched
	 * @param x the value to search for
	 * @return the index of x, or Integer.MIN_VALUE if not found
	 * 
	 * @implNote
	 * <strong>Stack usage:</strong> This recursive approach uses O(log n) stack space.
	 * In extreme cases (array.length > 2^31), stack overflow is possible.
	 * The iterative version ({@link #binarySearch1}) is safer for very large arrays.
	 */
	private int searchByDivision(int left,int right, int[]v, int x) {
		if (left > right) 
			return Integer.MIN_VALUE;  //x does not exist
		else {
			int center = (left + right)/2;
			if (v[center] == x) 
				return center;
			else if (v[center] > x) //the element is on the left
				return searchByDivision(left,center-1, v, x);
			else return searchByDivision(center+1, right, v, x); //the element is on the right  	 	 	       
		}
	}
	
}
