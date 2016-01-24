package topics.parallel;

import java.util.concurrent.*;  

/**
 * To calculate the cubee root of the values of an array
 * @author viceg
 */
public class RecursiveActionComparison extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private int[] data; //Array with numbers (data)
	private int start, end; //Determines what part of data to process.  
	//In real word code, its optimal value can be determined by experimentation
	private int threshold = 100; //Arbitrary set at 100
	 
	RecursiveActionComparison(int[] data, int start, int end, int threshold) { 
		this.data = data; 
		this.start = start; 
		this.end = end; 
		this.threshold = threshold;
	} 
	
	@Override
	protected void compute() {
	    //If number of elements is below the sequential threshold, then process sequentially 
	    if((end - start) < threshold) { 
	      //Time consuming task so the effects of concurrent execution are more observable
	      for(int i = start; i < end; i++) { 
	         data[i] = (int)(Math.cbrt(data[i])); 
	      } 
	    } 
	    else { //Continue to break the data into smaller components 
	      int middle = (start + end) / 2; //Find the midpoint
	      //Invoke new subtasks
	      invokeAll(new RecursiveActionComparison(data, start, middle, threshold), 
	                new RecursiveActionComparison(data, middle, end, threshold)); 
	    } 
	}
}


