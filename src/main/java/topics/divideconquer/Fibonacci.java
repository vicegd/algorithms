package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE FIBONACCI NUMBER OF ORDER n
 * Fibonacci Series = 0,1,1,2,3,5,8,13,21,34,55,89,...
 * e.g. the 0 is when n=0 and the 89 is when n=11
 * @author viceg
 */
public class Fibonacci {
	
	/**
	 * First iterative solution with a
	 * temporal complexity O(n)
	 * @param n Positive number to be used as input
	 * @return Fibonacci value for n
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
	 * Second iterative solution with temporal 
	 * complexity O(n) and that uses a vector. 
	 * Simple case of the DYNAMIC PROGRAMMING 
	 * technique (MORE ON THAT LATER IN THE 
	 * COURSE)
	 * @param n Positive number to be used as input
	 * @param v Array to help in the calculation
	 * @return Fibonacci value for n
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
	 * complexity O(n). It is D&C by subtraction
	 * with a=1;b=1;k=0 => O(n) 
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
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
	
	/**
	 * D&C sophisticated solution that is O(log n). 
	 * It is D&V by division with a=1;b=2;k=0 and 
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
