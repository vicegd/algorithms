package topics.parallel;

import java.util.concurrent.*;  

/**
 * To calculate the square of the values of an array
 * @author viceg
 */
public class RecursiveActionSquare extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private int[] data; //Array with numbers (data)
	private int start, end; //Determines what part of data to process.  
	//In real word code, its optimal value can be determined by experimentation
	private final int seqThreshold = 100; //Arbitrary set at 100
	 
	public RecursiveActionSquare(int[] data, int start, int end) { 
		this.data = data; 
		this.start = start; 
		this.end = end; 
	} 
	
	@Override
	protected void compute() {
	    //If number of elements is below the sequential threshold, then process sequentially 
	    if((end - start) < seqThreshold) { 
	      for(int i = start; i < end; i++) { 
	         data[i] = data[i]*data[i]; //Transform each element into its square 
	      } 
	    } 
	    else { //Continue to break the data into smaller components 
	      int middle = (start + end) / 2; //Find the midpoint
	      //Invoke new subtasks
	      invokeAll(new RecursiveActionSquare(data, start, middle), 
	                new RecursiveActionSquare(data, middle, end)); 
	    } 
	}
}


