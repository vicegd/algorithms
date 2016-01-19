package topics.divideconquer;

import topics.divideconquer.utils.Util;

/**
 * //DIVIDE AND CONQUER PROBLEM: THE MEDIAN OF n ELEMENTS
 * @author viceg
 */
public class Median {

	/**
	 * This method sorts the vector O(nlogn) and then 
	 * it is a very basic operation O(1), so its 
	 * calculation is O(nlogn) + O(1) => O(nlogn)
	 * @param v Array to calculate the median
	 * @return Median value in the array
	 */
	public int median1(int[]v) {
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}    
	
	/**
	 * This method is recursive and is based on the 
	 * concept of partition seen for the Quicksort
	 * algorithm. It is D&C by division with a=1;
	 * b=2; k=1 => O(n)
	 * @param v Array to calculate the median
	 * @return Median value in the array
	 */
	public int median2(int[]v) {
		int med = medianByDivision(0, v.length-1, v);
		return med;
	}
	
	private int medianByDivision(int left, int right, int[]v) { 
		int pivotPosition = Util.partition(v, left, right); //O(n) using as the pivot the central element
		int centerPosition = v.length/2;
		/* We find the median when we find the element that corresponds with the central position. 
		 * Note that when an element is partitionated it is sure that it is in its correct position */
		if (pivotPosition == centerPosition) 
			return v[pivotPosition];
		else if (pivotPosition > centerPosition) 
			return medianByDivision(left, pivotPosition-1, v);
		else 
			return medianByDivision(pivotPosition+1, right, v);
	}
	
}