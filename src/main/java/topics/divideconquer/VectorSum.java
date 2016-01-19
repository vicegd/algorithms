package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: CALCULATE THE SUM OF n ELEMENTS IN A VECTOR
 * @author viceg
 */
public class VectorSum {
	
	/**
	 * This method iteratively calculates the 
	 * sum with a linear complexity O(n)
	 * @param v Array to be used for the addition
	 * @return Addition of all the elements of the array
	 */
	public int sum1(int[]v) {
		int n= v.length;
		int s=0;
		for (int i=0;i<n;i++) 
			s=s+v[i];
		return s; 
	}    
	
	/**
	 * This method recursively calculates the 
	 * sum with a linear complexity O(n). 
	 * DandC by subtraction with a=1,b=1,k=0 - O(n)
	 * @param v Array to be used for the addition
	 * @return Addition of all the elements of the array
	 */
	public int sum2(int[]v) {
		return sumBySubtraction(0, v);
	}   
	private int sumBySubtraction(int i, int[]v) {
		if (i == v.length) 
			return 0;
		else 
			return v[i] + sumBySubtraction(i+1, v);
	}  
	
	/**
	 * This method recursively calculates the 
	 * sum with a linear complexity O(n). 
	 * DandC by division with a=2,b=2,k=0 - O(n)
	 * @param v Array to be used for the addition
	 * @return Addition of all the elements of the array
	 */
	public int sum3(int[]v) {
		return sumByDivision(0, v.length-1, v);
	} 
	private int sumByDivision(int left, int right, int[]v) {
		if (left == right) 
			return v[left];
		else { 
			int m = (left+right)/2;
			return sumByDivision(left, m, v) + sumByDivision(m+1, right, v);
		}
	}  

}
