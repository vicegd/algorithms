package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

/**
 * Sorting algorithm: Bubble method
 * @author viceg
 */
public class Bubble implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(Bubble.class);
	
	@Override
	public void sort(int[] elements) {
		for (int i = 1; i < elements.length; i++) {
			for (int j = elements.length - 1; j >= i; j--) {
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
				}
			}
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		log.debug("Bubble method");
		for (int i = 1; i < elements.length; i++) {
			for (int j = elements.length - 1; j >= i; j--) {
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
				}
			}
			if (trace) Util.trace(i, elements);
		}
	}

}
