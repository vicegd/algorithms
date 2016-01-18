package topics.principles;

/**
 * To calculate the factorial of a number
 * @author viceg
 */
public class Factorial {
	
	/**
	 * Calculates the factorial of a number with a recursive algorithm
	 * @param n Value used to calculate the factorial
	 * @return Factorial of n
	 */
	public int factorialOK(int n){
		if (n < 0)
			return -1;
		else if (n == 0) 
	    	return 1;
        return n * factorialOK(n-1);
	}
	
	/**
	 * Calculates the factorial of a number with a recursive algorithm
	 * It has a problem: if n is negative, it never stops
	 * @param n Value used to calculate the factorial
	 * @return Factorial of n
	 */
	public int factorialWrong(int n){
	    if (n == 0) 
	    	return 1;
        return n * factorialWrong(n-1);
	}

}
