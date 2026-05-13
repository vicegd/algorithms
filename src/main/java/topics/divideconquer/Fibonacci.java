package topics.divideconquer;

/**
 * Fibonacci Number Calculation using Divide and Conquer and Iterative Approaches.
 *
 * Problem Definition:
 * Calculate the n-th Fibonacci number where:
 *   F(0) = 0
 *   F(1) = 1
 *   F(n) = F(n-1) + F(n-2) for n >= 2
 *
 * Fibonacci Series Example:
 *   F(0)=0, F(1)=1, F(2)=1, F(3)=2, F(4)=3, F(5)=5, F(10)=55, F(11)=89
 *
 * This class demonstrates TWO iterative solutions, both O(n).
 * Why only iterative here? The naive recursive approach is exponential O(2^n),
 * making it impractical for larger n. See topics.dynamic.Fibonacci for
 * Dynamic Programming approaches that handle large n efficiently.
 *
 * Complexity Comparison:
 * - fib1() Iterative:        Time O(n), Space O(1) - best space efficiency
 * - fib2() Iterative+array:  Time O(n), Space O(n) - stores all intermediate results
 * - Naive Recursive:         Time O(2^n), Space O(n) - too slow for large n
 * - Dynamic Programming:     Time O(n), Space O(n) - best for large n
 *
 * Why Naive Recursion Fails:
 *   fib(5) calls fib(4) and fib(3);
 *   fib(4) calls fib(3) and fib(2);
 *   fib(3) is calculated twice, fib(2) three times!
 *   For fib(40): 300+ million recursive calls (takes minutes).
 *   With fib1(): 40 operations (microseconds).
 *
 * When to Use Each Method:
 * - fib1(): When you only need the final result and want minimal memory
 * - fib2(): When you need all intermediate Fibonacci numbers or want a cache
 * - Dynamic Programming: For very large n (n > 10^6) or when optimization is critical
 *
 * @author vicegd
 * @see topics.dynamic.Fibonacci for Dynamic Programming approach
 * @see BinarySearch for another Divide and Conquer example
 */
public class Fibonacci {
  
  /**
   * Calculates the n-th Fibonacci number using space-optimized iteration.
   *
   * Algorithm - Two-Variable Rolling Window:
   * Keep track of only the two most recent Fibonacci numbers:
   *   n1: F(i-1)
   *   n2: F(i)
   * Each iteration: sum = n1 + n2, then shift: n1 = n2, n2 = sum
   *
   * Example Trace for fib1(5):
   *   Initial: n1=0, n2=1
   *   i=1: sum=1, n1=1, n2=1
   *   i=2: sum=2, n1=1, n2=2
   *   i=3: sum=3, n1=2, n2=3
   *   i=4: sum=5, n1=3, n2=5
   *   Return: n1=5 (correct)
   *
   * Complexity Analysis:
   * - Time:  O(n) - single loop from 1 to n
   * - Space: O(1) - only two variables needed
   *
   * Why This is Better Than Naive Recursion:
   *   Naive fib(50): ~1.1 billion recursive calls, 30+ seconds
   *   This fib1(50): 50 additions, microseconds  ->  1,000,000x faster
   *
   * @param n the position in Fibonacci sequence (n >= 0)
   * @return the n-th Fibonacci number
   * @throws IllegalArgumentException if n < 0
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
   * Calculates the n-th Fibonacci number using dynamic programming with a memoization array.
   *
   * Algorithm - Array-Based Iteration (Classical DP):
   * Store all calculated Fibonacci numbers in array v[]:
   *   v[0] = 0
   *   v[1] = 1
   *   v[i] = v[i-1] + v[i-2] for i >= 2
   *
   * Example Trace for fib2(5, array):
   *   v[0] = 0
   *   v[1] = 1
   *   v[2] = 0 + 1 = 1
   *   v[3] = 1 + 1 = 2
   *   v[4] = 1 + 2 = 3
   *   v[5] = 2 + 3 = 5  <- return value
   *
   * Complexity Analysis:
   * - Time:  O(n) - iterates from 2 to n
   * - Space: O(n) - requires array of size n+1
   *
   * Advantages over fib1():
   * - Easy to understand (classic DP pattern)
   * - Can retrieve any F(i) from the array without recalculation
   * - Good for educational demonstration of DP
   *
   * Disadvantages compared to fib1():
   * - Uses O(n) extra space instead of O(1)
   * - If only F(n) is needed, fib1() is better
   *
   * When to Use fib2():
   * - When you need all intermediate Fibonacci numbers F(0) to F(n)
   * - When teaching Dynamic Programming concepts
   *
   * @param n the position in Fibonacci sequence to calculate (n >= 0)
   * @param v array to store computed Fibonacci values; must have length >= n+1
   * @return the n-th Fibonacci number (same as v[n])
   * @throws ArrayIndexOutOfBoundsException if v.length <= n
   * @throws NullPointerException if v is null
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

