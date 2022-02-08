package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE POSITION OF AN ELEMENT x IN A 
 * SORTED VECTOR OF n DIFFERENT ELEMENTS
 * @author viceg
 */
public class BinarySearch {
	/**
	 * This method iteratively calculates the position 
	 * of x in the ordered vector. It is O(log n), 
	 * using DandC with iterative programming
	 * @param v Array with numbers
	 * @param x Value that is being searched
	 * @return The position of the value x in 
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
	 * This method recursively calculates the 
	 * position of x in a sorted vector. DandV 
	 * by division with a=1,b=2,k=0 - O(logn) 
	 * @param v Array with numbers
	 * @param x Value that is being searched
	 * @return The position of the value x in
	 */
	public int binarySearch2(int[]v,int x) {
		return searchByDivision(0, v.length-1, v, x);
	}
	
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