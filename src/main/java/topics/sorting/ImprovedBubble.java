package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Sorting algorithm: Bubble method with sentinel
 * @author viceg
 */
public class ImprovedBubble implements ISortingAlgorithm{
	static Logger log = LoggerFactory.getLogger(ImprovedBubble.class);
	
	@Override
	public void sort(int[] elements) {
		int i = 1;
		boolean hasChange = true;
		
		while (hasChange && (i < elements.length)){
			hasChange = false;
			for (int j = elements.length - 1; j >= i; j--){
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
					hasChange = true;
				}
			}
			i++;
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		log.debug("Improved Bubble method");
		
		int i = 1;
		boolean hasChange = true;
		
		while (hasChange && (i < elements.length)){
			hasChange = false;
			for (int j = elements.length - 1; j >= i; j--){
				if (elements[j-1] > elements[j]){
					Util.interchange(elements, j-1, j);
					hasChange = true;
				}
			}
			if (trace) Util.trace(i, elements);
			i++;
		}
	}

}
