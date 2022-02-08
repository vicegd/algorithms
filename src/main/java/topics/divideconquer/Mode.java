package topics.divideconquer;

import topics.sorting.Quicksort;

/**
 * //DIVIDE AND CONQUER PROBLEM: MODE OF n ELEMENTS
 * @author viceg
 */
public class Mode {
	
	/**
	 * This method iteratively computes the 
	 * predominant element (mode). It is 
	 * quadratic O(n^2)
	 * @param v Array used to calculate the mode
	 * @param mo Array with the solution (value and repetitions)
	 */
	public void mode1(int[]v, int[]mo) {
		int n = v.length;
		mo[0] = v[0]; //first, we consider the first element as the mode
		mo[1] = 1; //..so the mode is repeated only once at this point
		int counter = 0;
		for (int i=0; i<n; i++) {
			counter=0; //we count for each number
			for (int j=i; j<n; j++)
				if (v[j]==v[i]) counter++;
			if (counter > mo[1]) { //we check if we have detected a new value for the mode
				mo[0] = v[i]; //the value
				mo[1] = counter; //the number of times the mode is repeated
			}     
		}
	}    
	
	/**
	 * This method previously orders the vector O(nlogn) and then it 
	 * looks for the mode O(n). It is O(nlogn) + O(n) -
	 * O(nlogn). It is DandC because of the Quicksort sorting
	 * @param v Array used to calculate the mode
	 * @param mo Array with the solution (value and repetitions)
	 */
	public void mode2(int[]v, int[]mo) {
		Quicksort quicksort = new Quicksort();
		quicksort.sort(v);
		
		int n = v.length;
		mo[0] = v[0]; //first, we consider the first element as the mode
		mo[1] = 1; //..so the mode is repeated only once at this point
		int counter = 1;
		for (int i=1; i<n; i++) { //O(n)
			if (v[i] == v[i-1]) { //all repeated elements must be one after another
				counter++;
				if (counter > mo[1]) {
					mo[0] = v[i];
					mo[1] = counter;
				}     
			}
			else counter=1; //when we begin with a non-repeated element, we restart the counter
		}//for  
	}    
	
	/*
	public mode3(int[]v)
	  There is a very advanced D&C method (very difficult)
	  which improves the previous one a little and is
	  explained in the base book (section 3.11)
	*/
	
}