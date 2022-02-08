 package topics.divideconquer;

import topics.sorting.Quicksort;

/**
 * //DIVIDE AND CONQUER PROBLEM: IS THERE A MAJORITARIAN ELEMENT 
 * IN n ELEMENTS?
 * @author viceg
 */
public class MajoritarianElement {
	
	/**
	 * This method iteratively calculates whether there is 
	 * or not majority element. It is quadratic O(n^2) 
	 * @param v Array with numbers to be used for the calculation
	 * @return Whether there is a majoritarian element
	 */
	public boolean majoritarian1(int[]v) {
		int n = v.length;
		int majority = n/2 + 1; //to be the majoritarian element, it should be at least n/2 +1 times
		int counter = 0;
		for (int i=0; i<n/2; i++) {
			counter=0; //we count for each number 
			for (int j=i; j<n; j++)
				if (v[j]==v[i]) 
					counter++;
			if (counter >= majority) return true; 
		}
		return false;
	}    
	
	/**
	 * This method previously orders the vector O(nlogn) and then it 
	 * looks for the majoritarian element (if there is one), 
	 * knowing that if there is one, it should be in the central 
	 * position (among others) v[n/2]. It is O(nlogn) + O(n) -
	 * O(nlogn)
	 * @param v Array with numbers to be used for the calculation
	 * @return Whether there is a majoritarian element
	 */
	public boolean majoritarian2(int[]v) {
		Quicksort quicksort = new Quicksort();
		quicksort.sort(v);
		
		int n = v.length;
		int majority = n/2+1;
		int counter = 0;
		//if there is a majoritarian element, it should be placed in the center
		for (int i=0; i<n; i++)
			if (v[i] == v[n/2]) counter++;
		if (counter >= majority) 
			return true; 
		else return false; 
	}    
	
	/**
	 * This method is recursive and difficult to understand. 
	 * It is explained in the base book  (section 3.11) and 
	 * has a linear complexity O(n). It is DandV by division 
	 * with a=1,b=2,k=1 - O(n) 
	 * @param v Array with numbers to be used for the calculation
	 * @return Whether there is a majoritarian element
	 */
	public boolean majoritarian3(int[]v) {
		int n = v.length;
		int majority = n/2+1;
		int counter = 0;
		int[]candi = new int[1];
		//the vector can be changed by majoritarianByDivision
		int[]w = new int[n];
		for (int i=0; i<n; i++) 
			w[i]=v[i]; 
		
		boolean b = (majoritarianByDivision(0, v.length-1, candi, v));
		
		//we restore the vector
		for (int i=0;i<n;i++) 
			v[i]=w[i];
		
		if (b) {
			for (int i=0;i<n;i++)
				if (v[i]==candi[0]) 
					counter++;
		  		if (counter>=majority) 
		  			return true; 
		}  
		return false; 
	}
	
	private boolean majoritarianByDivision(int left,int right, int[]candi, int[]v) { 
		 int t=right-left+1;
		 if (t<=0) 
			 return false;
		 candi[0]=v[left];
		 if (t==1)
			 return true;
		 int j=left;
		 if (t%2==0) { //it is even
			for (int i=left+1; i<=right; i+=2)
				if (v[i-1] == v[i]) {
					 v[j] = v[i];
					 j=j+1;
				}
			 	return majoritarianByDivision(left, j-1, candi, v);
		 }
		 else { //it is odd
			 for (int i=left+1; i<=right-1; i+=2)
				if (v[i-1] == v[i]) {
					v[j] = v[i];
					j=j+1;
				}
			 	if (!majoritarianByDivision(left, j-1, candi, v))
			 		candi[0]=v[right];
			 	return true;
		 } //else
	}
	  
}