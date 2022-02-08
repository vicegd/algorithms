package topics.divideconquer;

/**
 * GREATEST COMMON DIVISORS: Calculate the GCG of two positive integers
 * @author viceg
 */
public class GCG {
	
	/**
	 * Naive algorithm to solve the GCG problem
	 * @param a First number
	 * @param b Second number
	 * @return GCD
	 */
	public long naiveGCD(long a, long b) {
		long gcd = 0;
		for (long d = 1; d <= (a>b?b:a); d++) {
			if ((a % d == 0) && (b % d == 0)) {
				gcd = d;
			}
		}
		return gcd;
	}  
	

	/**
	 * Euclidean algorithm to solve the GCG problem
	 * @param a First number
	 * @param b Second number
	 * @return GCD
	 */
	public long euclideanGCG(long a, long b) {
		if (a == 0) return b;
		else if (b == 0) return a;
		else {
			long r = a % b;
			return euclideanGCG(b, r);
		}
	}
	
}
