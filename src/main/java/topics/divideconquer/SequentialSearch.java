package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE POSITION OF AN ELEMENT x IN A 
 * VECTOR (SORTED OR UNSORTED) OF n DIFFERENT ELEMENTS
 * @author viceg
 */
public class SequentialSearch {

	/**
	 * This method iteratively calculates the position 
	 * of x in the vector. It is O(n) 
	 * @param v Array with numbers
	 * @param x Value that is being searched
	 * @return The position of the value x in v
	 */
	public int sequentialSearch1(int[]v, int x){
		int n = v.length;
		int i = 0;
		while (i < n) { 
			if (v[i] == x) 
				return i;
			i=i+1;
		}
		return Integer.MIN_VALUE; //the element x does not exist
	}
	
	/**
	 * This method recursively calculates the 
	 * position of x in a vector. 
	 * DandC by subtraction with a=1,b=1,k=0 - O(n) 
	 * @param v Array with numbers
	 * @param x Value that is being searched
	 * @return The position of the value x in v
	 */
	public int sequentialSearch2(int[]v, int x) {
		return searchBySubtraction(0, v, x);
	}
	private int searchBySubtraction(int i, int[]v, int x){
		if (i==v.length) 
			return Integer.MIN_VALUE; //the element x does not exist
		else { 
			if (v[i]==x) 
				return i;
			else 
				return searchBySubtraction(i+1, v, x);
		}
	}
	
}