package topics.sorting.others;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Sorting algorithm: Binary Insertion method
 * @author viceg
 */
public class BinaryInsertion implements ISortingAlgorithm{
	@Override
	public void sort(int[] elements) {
		int left, right;
		int center;
		int pivot;
		
		for (int i = 1; i < elements.length; i++) {
			pivot = elements[i];
			left = 0;
			right = i-1;
			
			while (left <= right) {
				center = (left + right) / 2;
				if (pivot < elements[center])
					right = center-1;
				else
					left = center+1;
			}
			
			for (int j = i-1; j >= left; j--){
				elements[j+1] = elements[j];
			}
			
			elements[left] = pivot;
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		int left, right;
		int center;
		int pivot;
		
		for (int i = 1; i < elements.length; i++) {
			pivot = elements[i];
			left = 0;
			right = i-1;
			
			while (left <= right) {
				center = (left + right) / 2;
				if (pivot < elements[center])
					right = center-1;
				else
					left = center+1;
			}
			
			for (int j = i-1; j >= left; j--){
				elements[j+1] = elements[j];
			}
			
			elements[left] = pivot;
			
			if (trace) Util.trace(i, elements);
		}
	}

}
