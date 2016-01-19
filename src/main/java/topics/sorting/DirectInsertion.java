package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Sorting algorithm: Direct insertion method
 * @author viceg
 */
public class DirectInsertion implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(DirectInsertion.class);
	
	@Override
	public void sort(int[] elements) {
		int j;
		int pivot;
		
		for (int i =  1; i < elements.length; i++) {
			pivot = elements[i];
			j = i-1;
			
			while (j >= 0 && pivot < elements[j]) {
				elements[j+1] = elements[j];
				j--;
			}
			
			elements[j+1] = pivot;
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		int j;
		int pivot;
		
		log.debug("Direct Insertion method");
		for (int i =  1; i < elements.length; i++) {
			pivot = elements[i];
			j = i-1;
			
			while (j >= 0 && pivot < elements[j]) {
				elements[j+1] = elements[j];
				j--;
			}
			
			elements[j+1] = pivot;
			
			if (trace) Util.trace(i, elements);
		}
	}

}
