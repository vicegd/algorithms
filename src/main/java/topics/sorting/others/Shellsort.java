package topics.sorting.others;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

/**
 * Sorting algorithm: Shellsort method
 * @author viceg
 */
public class Shellsort implements ISortingAlgorithm{
	@Override
	public void sort(int[] elements) {
		int h[] = {7, 3, 1};
		
		for (int m = 0; m < h.length; m++){
			int k = h[m]; 
			
			for (int i = k; i < elements.length; i++){
				int pivot = elements[i];
				int j = i-k;
				
				while (j >= 0 && pivot < elements[j]){
					elements[j+k] = elements[j];
					j = j-k;
				}
				elements[j+k] = pivot;
			}
		}
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		int h[] = {7,3,1};
		
		for (int m = 0; m < h.length; m++){
			int k = h[m]; 
			
			for (int i = k; i < elements.length; i++){
				int pivot = elements[i];
				int j = i-k;
				
				while (j >= 0 && pivot < elements[j]){
					elements[j+k] = elements[j];
					j = j-k;
				}
				elements[j+k] = pivot;
				
				if (trace) Util.traceShellSort(k, i, elements);
			}
		}
	}

}
