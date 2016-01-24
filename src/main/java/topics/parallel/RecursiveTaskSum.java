package topics.parallel;

import java.util.concurrent.*;  

/**
 * To calculate the addition of the values of an array
 * @author viceg
 */
public class RecursiveTaskSum extends RecursiveTask<Double> {
	private static final long serialVersionUID = 1L;
	private double[] data; //Array with numbers (data)
	private int start, end; //Determines what part of data to process.  
	private int threshold = 10000; //Arbitrary set
	 
	RecursiveTaskSum(double[] data, int start, int end) { 
		this.data = data; 
		this.start = start; 
		this.end = end; 
	} 
	
	@Override
	protected Double compute() {
		double sum = 0;
	    if((end - start) < threshold) { 
	      for(int i = start; i < end; i++) { 
	         sum += data[i]; 
	      } 
	    } 
	    else { //Continue to break the data into smaller components	
	      int middle = (start + end) / 2; //Find the midpoint
	      //Invoke new subtasks
	      RecursiveTaskSum subTaskA = new RecursiveTaskSum(data, start, middle); 
	      RecursiveTaskSum subTaskB = new RecursiveTaskSum(data, middle, end); 
	      subTaskA.fork(); //Start each subtask by forking
	      //Wait for the subtasks to return, and aggregate the results 
	      sum = subTaskB.compute() + subTaskA.join();
	      //sum = subTaskA.invoke() + subTaskB.invoke();
	    } 
	    return sum;
	}
}


