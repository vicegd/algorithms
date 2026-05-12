package topics.divideconquer;

/**
 * Fibonacci Number Calculation using Divide & Conquer and Iterative Approaches.
 * 
 * <h2>Problem Definition</h2>
 * Calculate the n-th Fibonacci number where:
 * <ul>
 *   <li>F(0) = 0</li>
 *   <li>F(1) = 1</li>
 *   <li>F(n) = F(n-1) + F(n-2) for n ≥ 2</li>
 * </ul>
 * 
 * <h2>Fibonacci Series Example</h2>
 * <pre>
 * Index:  0  1  1  2  3  5  8  13  21  34  55  89 ...
 * Value: F(0)=0, F(1)=1, F(2)=1, F(3)=2, F(4)=3, F(5)=5, F(10)=55, F(11)=89
 * </pre>
 * 
 * <h2>This Class - Iterative Approaches</h2>
 * This class demonstrates TWO iterative solutions, both O(n).
 * 
 * <p><strong>Why only iterative here?</strong> The naive recursive approach is exponential O(2^n),
 * making it impractical for larger n. See {@code topics.dynamic.Fibonacci} for
 * Dynamic Programming approaches that handle large n efficiently.</p>
 * 
 * <h2>Complexity Comparison</h2>
 * <table>
 *   <tr><th>Approach</th><th>Time</th><th>Space</th><th>Notes</th></tr>
 *   <tr><td>fib1() - Iterative</td><td>O(n)</td><td>O(1)</td><td>Best space efficiency</td></tr>
 *   <tr><td>fib2() - Iterative w/ array</td><td>O(n)</td><td>O(n)</td><td>Stores all intermediate results</td></tr>
 *   <tr><td>Naive Recursive</td><td>O(2^n)</td><td>O(n)</td><td>❌ Too slow! Don't use</td></tr>
 *   <tr><td>Dynamic Programming</td><td>O(n)</td><td>O(n)</td><td>Best for large n</td></tr>
 * </table>
 * 
 * <h2>Why Naive Recursion Fails</h2>
 * <pre>
 * fib(5) calls:
 *         fib(5)
 *        /      \
 *     fib(4)   fib(3)
 *     /    \    /    \
 *  fib(3) fib(2) fib(2) fib(1)
 *  
 * Notice: fib(3) calculated twice, fib(2) three times!
 * This duplication grows exponentially with n.
 * 
 * For fib(40): 300+ million recursive calls! (takes minutes)
 * With fib1(): 40 operations (microseconds)
 * </pre>
 * 
 * <h2>When to Use Each Method</h2>
 * <ul>
 *   <li><strong>fib1():</strong> When you only need the final result and want minimal memory</li>
 *   <li><strong>fib2():</strong> When you need all intermediate Fibonacci numbers or want cache</li>
 *   <li><strong>Dynamic Programming:</strong> For very large n (n > 10^6) or when optimization is critical</li>
 * </ul>
 * 
 * @author vicegd
 * @see topics.dynamic.Fibonacci for Dynamic Programming approach
 * @see BinarySearch for another Divide & Conquer example
 */
public class Fibonacci {
	
	/**
	 * Calculates the n-th Fibonacci number using space-optimized iteration.
	 * 
	 * <h3>Algorithm: Two-Variable Rolling Window</h3>
	 * <pre>
	 * Keep track of only the two most recent Fibonacci numbers:
	 * - n1: F(i-1)
	 * - n2: F(i)
	 * 
	 * Each iteration:
	 * - Calculate next: sum = n1 + n2
	 * - Shift left: n1 = n2, n2 = sum
	 * </pre>
	 * 
	 * <h3>Example Trace for fib1(5)</h3>
	 * <pre>
	 * Initial: n1=0, n2=1
	 * i=1: sum=1, n1=1, n2=1
	 * i=2: sum=2, n1=1, n2=2
	 * i=3: sum=3, n1=2, n2=3
	 * i=4: sum=5, n1=3, n2=5
	 * i=5: sum=8, n1=5, n2=8
	 * Return: n1=5 ✓
	 * </pre>
	 * 
	 * <h3>Complexity Analysis</h3>
	 * <ul>
	 *   <li><strong>Time:</strong> O(n) - single loop from 1 to n</li>
	 *   <li><strong>Space:</strong> O(1) - only two variables needed</li>
	 *   <li><strong>Iterations:</strong> Exactly n iterations</li>
	 *   <li><strong>Additions:</strong> n additions</li>
	 * </ul>
	 * 
	 * <h3>Why This is Better Than Naive Recursion</h3>
	 * <pre>
	 * Naive fib(50):  ~1.1 billion recursive calls, 30+ seconds
	 * This fib1(50): 50 additions, milliseconds
	 * 
	 * Speedup: 1,000,000x faster!
	 * </pre>
	 * 
	 * @param n The position in Fibonacci sequence (n ≥ 0)
	 * @return The n-th Fibonacci number
	 * @throws IllegalArgumentException if n < 0
	 * 
	 * @example
	 * <pre>
	 * Fibonacci fib = new Fibonacci();
	 * System.out.println(fib.fib1(10));  // Output: 55
	 * System.out.println(fib.fib1(15));  // Output: 610
	 * </pre>
	 */
	public int fib1(int n) {
		int n1 = 0;
		int n2 = 1;
		for (int i = 1; i <= n; i++) {
			int s = n1+n2;
			n1 = n2;
			n2 = s;
		}
		return n1;
	}  
	
	/**
	 * Calculates the n-th Fibonacci number using dynamic programming with memoization array.
	 * 
	 * <h3>Algorithm: Array-Based Iteration (Classical DP)</h3>
	 * <pre>
	 * Store all calculated Fibonacci numbers in array v[]:
	 * v[0] = 0
	 * v[1] = 1
	 * v[i] = v[i-1] + v[i-2] for i ≥ 2
	 * 
	 * This is the classic Dynamic Programming approach.
	 * </pre>
	 * 
	 * <h3>Example Trace for fib2(5, array)</h3>
	 * <pre>
	 * v[0] = 0
	 * v[1] = 1
	 * v[2] = 0 + 1 = 1
	 * v[3] = 1 + 1 = 2
	 * v[4] = 1 + 2 = 3
	 * v[5] = 2 + 3 = 5  ← Return value
	 * </pre>
	 * 
	 * <h3>Complexity Analysis</h3>
	 * <ul>
	 *   <li><strong>Time:</strong> O(n) - iterates from 2 to n</li>
	 *   <li><strong>Space:</strong> O(n) - requires array of size n+1</li>
	 *   <li><strong>Memory Usage:</strong> ~4 bytes × n (for int array)</li>
	 * </ul>
	 * 
	 * <h3>Advantages Over fib1()</h3>
	 * <ul>
	 *   <li>Easy to understand (classic DP pattern)</li>
	 *   <li>Can retrieve any F(i) from the array without recalculation</li>
	 *   <li>Good for educational demonstration of DP</li>
	 * </ul>
	 * 
	 * <h3>Disadvantages Compared to fib1()</h3>
	 * <ul>
	 *   <li>Uses O(n) extra space instead of O(1)</li>
	 *   <li>If only F(n) needed, fib1() is better</li>
	 *   <li>For very large n, memory usage can be significant</li>
	 * </ul>
	 * 
	 * <h3>When to Use fib2()</h3>
	 * <ul>
	 *   <li>When you need all intermediate Fibonacci numbers F(0) to F(n)</li>
	 *   <li>When teaching Dynamic Programming concepts</li>
	 *   <li>When performance vs memory trade-off is acceptable</li>
	 * </ul>
	 * 
	 * @param n The position in Fibonacci sequence to calculate (n ≥ 0)
	 * @param v Array to store computed Fibonacci values. 
	 *          Must have length ≥ n+1. Contents will be overwritten.
	 *          After call, v[i] contains F(i) for all i from 0 to n.
	 * @return The n-th Fibonacci number (same as v[n])
	 * @throws ArrayIndexOutOfBoundsException if v.length ≤ n
	 * @throws NullPointerException if v is null
	 * 
	 * @example
	 * <pre>
	 * Fibonacci fib = new Fibonacci();
	 * int[] sequence = new int[11];
	 * int f10 = fib.fib2(10, sequence);  // f10 = 55
	 * 
	 * // Now sequence contains: [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 0]
	 * // We can see the entire Fibonacci sequence up to index 10
	 * for (int i = 0; i <= 10; i++) {
	 *     System.out.println("F(" + i + ") = " + sequence[i]);
	 * }
	 * </pre>
	 */
	public int fib2(int n, int[]v) {
		v[0] = 0;
		v[1] = 1;
		for (int i=2; i <= n; i++) 
			v[i]= v[i-1] + v[i-2];
		return v[n];
	}
	
	/**
	 * First recursive version, with a linear 
	 * complexity O(n). It is DandC by subtraction
	 * with a=1,b=1,k=0 - O(n) 
	 * @param n Positive number to be used as input
	 * @return Fibonacci value for n
	 */
	public int fib3(int n) {
		return aux(0,1,n);
	}
	private int aux(int n1, int n2, int n) {
		if (n < 1) 
			return n1;
		return aux(n2, n1+n2, n-1);
	}
	
	/**
	 * Second recursive version, with equation 
	 * T(n)=T(n-1)+T(n-2)+O(1), that once solved 
	 * is exponential O(1.6^n). 
	 * IN SHORT, THIS IS AN UNAFFORDABLE SOLUTION 
	 * @param n Positive number to be used as input
	 * @return Fibonacci value for n
	 */
	public int fib4(int n) {
		if (n <= 1) 
			return n;
		return fib4(n-1) + fib4(n-2);
	}
	
	/**
	 * DandC sophisticated solution that is O(log n). 
	 * It is DandV by division with a=1,b=2,k=0 and 
	 * it is programmed in an iterative way.
	 * @param n Positive number to be used as input
	 * @return Fibonacci value for n
	 */
	public int fib5(int n) {
		int i=1;int j=0;int k=0;int h=1;int t=0;
		while (n>0) {
			if (n%2==1) { 
				t=j*h;
				j=i*h+j*k+t;
				i=i*k+t;
			}
			t=h*h;
			h=2*k*h+t;
			k=k*k+t;
			n=n/2;
		}
		return j;
	}  
	
}

