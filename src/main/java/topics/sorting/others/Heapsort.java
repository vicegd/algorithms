package topics.sorting.others;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

/**
 * Sorting algorithm: Heapsort method
 * @author viceg
 */
public class Heapsort implements ISortingAlgorithm{
    private int[] elements;
    private int n;
    
	@Override
	public void sort(int[] elements) {
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
        this.elements = elements;
        this.n = elements.length;

    	buildheap();
        while (n > 1){
            n--;
            Util.interchange(elements, 0, n);
            String message = "Downheap:" + elements[0];
            downheap(0);
            Util.traceMessage(message, elements);
        } 
	}

    private void buildheap() //O(n)
    {
        for (int i=n/2-1; i >= 0; i--){
        	String message = "BUILDING HEAP. Downheap:" + elements[i];
            downheap(i);
            Util.traceMessage(message, elements);
        }
    }

    private void downheap(int v){ //O(log n)
        int w = 2*v+1; //first descendant of v
        while (w < n){
            if (w+1<n) //if there is a second descendant...
            	//w is the descendant of v with maximum value
                if (elements[w+1] > elements[w]) w++; 

            if (elements[v] >= elements[w]) 
            	break; //v is heap
            else { 
            	//exchange labels of v and w
	            Util.interchange(elements, v, w); 
	            v= w; //we check next level
	            w= 2*v+1;
            }
        }
    }

}
