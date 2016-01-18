package topics.sorting.others;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

/**
 * Sorting algorithm: Bidirectional Bubble method
 * @author viceg
 */
public class BidirectionalBubble implements ISortingAlgorithm{
	@Override
	public void sort(int[] elements) {
		int left = 0;
		int right = elements.length -1;
		boolean hasChange = true;
		int i;
		
		while (hasChange && (left < right)) {
			hasChange = false;
			
			for (i = left; i < right; i++) {
				if (elements[i] > elements[i+1]) {
					Util.interchange(elements, i, i+1);
					hasChange = true;
				}
			}
		  
			for (i = right - 1; i >= left; i--) {
				if (elements[i] > elements[i+1]) {
					Util.interchange(elements, i, i+1);
					hasChange = true;
				}
			}

			left++;
			right--;
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		int left = 0;
		int right = elements.length -1;
		boolean hasChange = true;
		int i;
		
		while (hasChange && (left < right)) {
			hasChange = false;
			
			for (i = left; i < right; i++) {
				if (elements[i] > elements[i+1]) {
					Util.interchange(elements, i, i+1);
					hasChange = true;
				}
			}
		  
			for (i = right - 1; i >= left; i--) {
				if (elements[i] > elements[i+1]) {
					Util.interchange(elements, i, i+1);
					hasChange = true;
				}
			}
			
			if (trace) Util.trace(left+1, elements);
			
			left++;
			right--;
		}
	}

}
