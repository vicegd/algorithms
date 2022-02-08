
package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE FACTORIAL OF A NUMBER
 * @author viceg
 */
public class Factorial {
	
	/**
	 * This method iteratively calculates the 
	 * factorial with a linear complexity O(n) 
	 * @param n Number to calculate greater than or equal to 0
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
	 * DandC by subtraction with a=1,b=1,k=0 
	 * @param n Number to calculate greater than or equal to 0
	 * @return Factorial of the number 
	 */
	public int fact2(int n) { 
		if (n == 0) 
			return 1;
		else 
			return n * fact2(n-1);
	}   
	 
}
