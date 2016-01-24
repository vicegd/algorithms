package topics.parallel;

/**
 * To solve the classical Fibonacci algorithm 
 * using a recursive and inefficient algorithm
 * @author viceg
 *
 */
public class FibonacciAlgorithm {	
	public int n;
	
	/**
	 * Constructor
	 * @param n The number to calculate the result
	 */
	public FibonacciAlgorithm(int n){
		this.n = n;
	}
	
	/**
	 * Initializes the process calling the recursive method
	 * @return The Fibonacci results for the value of n
	 */
	public long solve(){
		return fibonacci(this.n);
	}

	private long fibonacci(int n) {
		if (n <= 1)
			return n;
		else 
			return fibonacci(n-1) + fibonacci(n-2);
	}

}

