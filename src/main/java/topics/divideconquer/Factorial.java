package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE FACTORIAL OF A NUMBER
 * @author viceg
 */
public class Factorial {
	
	/**
	 * This method iteratively calculates the 
	 * factorial with a linear complexity O(n) 
	 * @param n Number to calculate >= 0
	 * @return Factorial of the number
	 */
	public int fact1(int n) {
		int f = 1;
		for(int i=1; i<=n; i++) 
			f = f*i;
		return f; 
	}    
	
	/**
	 * This method recursively calculates the 
	 * factorial with a linear complexity O(n), 
	 * D&C by subtraction with a=1;b=1;k=0 
	 * @param n Number to calculate >= 0 
	 * @return Factorial of the number
	 */
	public int fact2(int n) { 
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}   
	 
}
