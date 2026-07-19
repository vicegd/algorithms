package topics.divideconquer.gcd;

/**
 * <h1>Greatest Common Divisor</h1>
 * <p>
 * Demonstrates the massive performance gap between a naive linear search and 
 * the ancient, highly optimized Euclidean Algorithm (a pure Divide & Conquer approach).
 * </p>
 *
 * @author vicegd
 */
public class GCD {

    /**
     * <h2>Naive Algorithm</h2>
     * <p>
     * Iterates linearly from 1 up to the smaller of the two numbers, checking 
     * every single integer to see if it divides both evenly.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(min(a, b))</code></li>
     * <li><strong>Space Complexity:</strong> <code>O(1)</code></li>
     * </ul>
     *
     * @param a First number
     * @param b Second number
     * @return The Greatest Common Divisor
     */
    public long naiveGCD(long a, long b) {
        // Work with absolute values to support negative inputs natively
        a = Math.abs(a);
        b = Math.abs(b);
        
        if (a == 0) return b;
        if (b == 0) return a;

        long gcd = 1;
        long limit = Math.min(a, b);
        
        for (long d = 1; d <= limit; d++) {
            if ((a % d == 0) && (b % d == 0)) {
                gcd = d;
            }
        }
        return gcd;
    }

    /**
     * <h2>Euclidean Algorithm</h2>
     * <p>
     * A recursive Divide and Conquer approach. It relies on the mathematical principle 
     * that the GCD of two numbers also divides their difference (and specifically, their remainder).
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> <code>O(log(min(a, b)))</code> - Exponentially faster than naive.</li>
     * <li><strong>Space Complexity:</strong> <code>O(log(min(a, b)))</code> - Call stack depth.</li>
     * </ul>
     *
     * @param a First number
     * @param b Second number
     * @return The Greatest Common Divisor
     */
    public long euclideanGCD(long a, long b) {
        // Work with absolute values
        a = Math.abs(a);
        b = Math.abs(b);
        
        return euclideanHelper(a, b);
    }

    /**
     * Private tail-recursive helper that assumes positive inputs.
     */
    private long euclideanHelper(long a, long b) {
        if (b == 0) {
            return a;
        }
        // Divide: The problem size is reduced by taking the modulo.
        // Conquer: Recursively find the GCD of the smaller number and the remainder.
        long remainder = a % b;
        return euclideanHelper(b, remainder);
    }
}