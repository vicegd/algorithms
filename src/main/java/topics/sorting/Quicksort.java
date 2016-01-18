package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

/**
 * Sorting algorithm: Quicksort method
 * @author viceg
 */
public class Quicksort implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(Quicksort.class);
	
	@Override
	public void sort(int[] elements) {
		quickSort(elements, 0, elements.length-1, 1);
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		if (trace) log.debug("Quicksort method");
		quickSort(elements, 0, elements.length-1, 1);
	}
	
    /**
     * Gets the position of the median of the three (left, right and 
     * the element which position is in the center between them, and 
     * moves the elements to order them
     * @param elements Array with numbers to calculate the median of three
     * @param left Position of the element on the left
     * @param right Position of the element on the right
     * @return Position of the median of three
     */
	private int median_of_three(int elements[], int left, int right){ 
		int center = (left + right) / 2;
		if (elements[left] > elements[center])
			Util.interchange(elements, left, center);
		if (elements[left] > elements[right])
			Util.interchange(elements, left, right);
		if (elements[center] > elements[right])
			Util.interchange(elements, center, right);
		return center;
	}
	
	private void quickSort(int elements[], int left, int right, int level){
		int i = left;
		int j = right - 1;
		int pivot;
		
		if (left < right){ //if there is one element it is not necessary
			int center = median_of_three(elements, left, right);
			//if there are less than or equal to 3 elements, there are just ordered
			if ((right - left) >= 3){ 
				pivot = elements[center]; //choose the pivot
				Util.interchange(elements, center, right); //hide the pivot

				do {         
			    	while (elements[i] <= pivot && i < right) i++; //first element > pivot
			    	while (elements[j] >= pivot && j > left) j--; //first element < pivot
			        if (i < j) Util.interchange(elements, i, j);
			    } while (i < j);   //end while
				
				//we set the position of the pivot
				Util.interchange(elements, i, right);
				Util.traceMessage("Level: " + level + " Pivot: " + pivot, elements);
				quickSort(elements, left, i-1, level+1);
				quickSort(elements, i+1, right, level+1);		
			}
			else Util.traceMessage("Level: " + level, elements);
		} else Util.traceMessage("Level: " + level, elements);
	}

}
