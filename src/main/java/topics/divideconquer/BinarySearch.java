package topics.divideconquer;

/**
 * Binary Search implementation for finding an element in a sorted array.
 *
 * Algorithm Overview:
 * Implements the classic binary search algorithm using two approaches:
 * - Iterative: Uses a while loop, O(1) space complexity
 * - Recursive: Uses method recursion, O(log n) space for call stack
 *
 * Divide and Conquer Strategy:
 * 1. Divide:  Split array into two halves at midpoint
 * 2. Conquer: Compare target with middle element
 * 3. Combine: Recursively search appropriate half
 *
 * Time Complexity:
 * - Best case:           O(1) - element at middle position
 * - Average case:        O(log n)
 * - Worst case:          O(log n) - element at end or not found
 * - Space (iterative):   O(1)
 * - Space (recursive):   O(log n) for recursion stack
 *
 * Precondition: Array must be sorted in ascending order.
 *
 * Example:
 *   int[] sortedArray = {1, 3, 5, 7, 9, 11, 13};
 *   BinarySearch searcher = new BinarySearch();
 *   int position = searcher.binarySearch1(sortedArray, 7);  // Returns 3
 *   int notFound = searcher.binarySearch1(sortedArray, 6);  // Returns Integer.MIN_VALUE
 *
 * @author vicegd
 * @see #binarySearch1(int[], int) Iterative implementation
 * @see #binarySearch2(int[], int) Recursive implementation
 */
public class BinarySearch {
	/**
	 * Iterative binary search implementation.
	 *
	 * Uses a while loop to repeatedly divide the search space in half.
	 * More efficient than the recursive version (no stack overhead).
	 *
	 * Algorithm Steps:
	 * 1. Initialize left = 0, right = array length - 1
	 * 2. While left <= right:
	 *    - Calculate middle = (left + right) / 2
	 *    - If array[middle] == target: Return middle
	 *    - If array[middle] > target: Search left half (right = middle - 1)
	 *    - If array[middle] < target: Search right half (left = middle + 1)
	 * 3. If element not found: Return Integer.MIN_VALUE
	 *
	 * Example:
	 *   int index = binarySearch1(new int[]{1, 3, 5, 7, 9}, 5);  // Returns 2
	 *
	 * @param v the sorted array to search in (must be in ascending order)
	 * @param x the value to search for
	 * @return the index of x in array v, or Integer.MIN_VALUE if not found
	 * @throws NullPointerException if array v is null
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
	 * Uses method recursion to divide the search space. Demonstrates
	 * the recursive divide-and-conquer pattern, though the iterative version
	 * is generally preferred due to lower memory overhead.
	 *
	 * Recurrence Relation:
	 *   T(n) = T(n/2) + O(1)  ->  O(log n)
	 *
	 * Why Divide and Conquer Works:
	 * - Optimal substructure: Solution to finding x in array depends on
	 *   finding x in left or right half
	 * - Independent subproblems: Left and right searches do not interfere
	 * - Exponential speedup: Each step eliminates half the remaining elements
	 *   -> logarithmic total steps
	 *
	 * Example:
	 *   int index = binarySearch2(new int[]{1, 3, 5, 7, 9, 11, 13}, 7);  // Returns 3
	 *
	 * @param v the sorted array to search in (must be in ascending order)
	 * @param x the value to search for
	 * @return the index of x in array v, or Integer.MIN_VALUE if not found
	 * @see #binarySearch1(int[], int) for iterative alternative
	 */
	public int binarySearch2(int[]v,int x) {
		return searchByDivision(0, v.length-1, v, x);
	}
	
	/**
	 * Private recursive helper for binary search.
	 *
	 * Maintains the bounds of the search space (left and right indices) and
	 * recursively narrows them until the element is found or the space is empty.
	 *
	 * Base Cases:
	 * - If left > right:       Search space exhausted, return Integer.MIN_VALUE
	 * - If array[center] == x: Element found, return center
	 *
	 * Recursive Cases:
	 * - If array[center] > x: Recursively search left half
	 * - If array[center] < x: Recursively search right half
	 *
	 * Stack usage: This recursive approach uses O(log n) stack space.
	 * The iterative version ({@link #binarySearch1}) is safer for very large arrays.
	 *
	 * @param left  the left boundary (inclusive) of current search space
	 * @param right the right boundary (inclusive) of current search space
	 * @param v     the array being searched
	 * @param x     the value to search for
	 * @return the index of x, or Integer.MIN_VALUE if not found
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
