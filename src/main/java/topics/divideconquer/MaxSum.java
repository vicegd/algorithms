package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: MAXIMUM SUMMATION 
 * OF ALL THE SUBSEQUENCES OF n ELEMENTS
 * @author viceg
 */
public class MaxSum {

	/**
	 * This algorithms is iterative. 
	 * It is cubic O(n^3)
	 * @param v Vector to calculate the values
	 * @return The maximum sum of the subsequences
	 */
	public int maxSum1(int[]v) {
		int n = v.length;
		int max = 0; //keeps the maximum value
		for(int i=0;i<n;i++)
			for(int j=i;j<n;j++) { //we try all possible combinations between i and j
				int sum = 0;
				for (int k=i; k<=j; k++) //we always sum the numbers between i and j
					sum = sum+v[k];
				if(sum > max) 
					max = sum;
			} //for
		return max;
	} 
	
	/**
	 * This algorithms is iterative. 
	 * It is quadratic O(n^2)
	 * @param v Vector to calculate the values
	 * @return The maximum sum of the subsequences
	 */
	public int maxSum2(int[]v) {
		throw new UnsupportedOperationException("This operation needs to be implemented");
	} 
	
	/**
	 * This algorithms is recursive. 
	 * It is DandC by division with 
	 * a=2,b=2,k=1 - O(nlogn)
	 * @param v Vector to calculate the values
	 * @return The maximum sum of the subsequences
	 */
	public int maxSum3(int[]v) {
		return maxSumByDivision(0, v.length-1, v);
	}
	
	private int maxSumByDivision(int left, int right, int[]v) {	
		if (left==right) 
			return v[left];
		else {
			int center = (left+right)/2;
			int maxLeft = maxSumByDivision(left,center, v);
			int maxRight = maxSumByDivision(center+1,right, v);	
			//calculates the maximum of the subsequence that pass from one-half to the other half
			int sum1=0;
			int maxSum1=0;
			for(int i=center; i>=left; i--) {
				sum1 = sum1+v[i];
				if (sum1>maxSum1) 
					maxSum1=sum1;
			}
	  
			int sum2=0;
			int maxSum2=0;
			for(int i=center+1; i<=right; i++) {
				sum2=sum2+v[i];
				if (sum2>maxSum2) 
					maxSum2=sum2;
			}
			return biggest(maxLeft, maxRight, maxSum1+maxSum2);
		}//else
	}
	
	//Returns the largest of the three numbers O(1)
	private int biggest(int a, int b, int c) { 
		if (a>=b && a>=c) return a;
		else if (b>=a && b>=c) return b;
		else return c;
	}
	
}