package topics.divideconquer.utils;

/**
 * Utility class for divide-and-conquer sorting helpers.
 *
 * Provides the partition operation used by Quicksort and related algorithms.
 * The partition method rearranges array elements in-place around a pivot so
 * that all elements to the left are less than or equal to the pivot and all
 * elements to the right are greater than or equal to the pivot.
 *
 * @author vicegd
 * @see topics.sorting.Quick
 */
public class Util {
	/* This is a linear process O(n). At the end it leaves the pivot 
	 * in a position such that: on its left there is no largest 
	 * element and, on its right there is no smallest element */
	public static int partition(int[]elements, int left, int right) {
		int i, pivot;
		permute(elements, (left+right)/2,left); //in this case, we use the center element (not the median)
		//the pivot is the central element, and is changed (hide) with the first one
		pivot = elements[left]; 
		i = left;
		for (int s = left+1; s <= right; s++) 
			if (elements[s] <= pivot) {
				i++;
				permute(elements, i, s);
			}
		permute(elements, left, i); //pivot is restored where it belongs
		return i; //returns the position in which the pivot should be
	}
	
	//it is O(1). It is exactly like the Util.interchange method
	private static void permute(int[]elements, int i, int j) {
		int temp;
		temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
	
} 
