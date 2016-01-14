package topics.principles;

import java.util.List;

/**
 * To use different methods for searching numbers in an array
 * @author viceg
 */
public class Search {

	/**
	 * Performs a sequential search in an array
	 * @param list Array with numbers 
	 * @param value Value to be found in the array
	 * @return True if the element was found, false otherwise
	 */
	public boolean searchSequential(int[] list, int value){
		int i = 0;
		int n = list.length - 1;
	    while ((i <= n) && (list[i] != value))
	    	i++;
	    if (i == n+1)
	    	return false;
	    else 
	    	return true;
	}
	
	/**
	 * Performs a sequential search in an array using a sentinel to avoid checking if we are inside the limits of the array
	 * @param list Array with numbers 
	 * @param value Value to be found in the array
	 * @return True if the element was found, false otherwise
	 */
	public boolean searchSequentialSentinel(List<Integer> list, int value){
		list.add(value); //we know now that the element is in the array
		int i = 0;
	    while (list.get(i) != value)
	    	i++;
	    if (i == list.size()-1) //element n+1
	    	return false;
	    else 
	    	return true;
	}
	
	/**
	 * Performs a binary search in an array, reducing the complexity when the number is not there -> O(nlogn)
	 * @param list Array with numbers 
	 * @param value Value to be found in the array
	 * @return True if the element was found, false otherwise
	 */
	public boolean searchBinary(int[] list, int value){
		int left = 0;
		int right = list.length-1;
		int k;
		
		do {
			k = (left + right) / 2;
			if (value > list[k])
				left = k+1;
			else 
				right = k-1;
		} while ((list[k] != value) && (left < right));
		
	    if (list[k] != value)
	    	return false;
	    else 
	    	return true;
	}

}