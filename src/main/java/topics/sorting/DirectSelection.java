package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Sorting algorithm: Direct selection method
 * @author viceg
 */
public class DirectSelection implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(DirectSelection.class);
	
	@Override	
	public void sort(int[] elements) {
		int posMin;
		
		for (int i =  0; i < elements.length-1; i++) {
			posMin = Util.findPosMin(elements, i); //O(n)
			Util.interchange(elements, i, posMin);
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		int posMin;
		
		log.debug("Direct Selection method");
		for (int i =  0; i < elements.length-1; i++) {
			posMin = Util.findPosMin(elements, i); //O(n)
			Util.interchange(elements, i, posMin);
			
			if (trace) Util.trace(i+1, elements);
		}
	}

}
