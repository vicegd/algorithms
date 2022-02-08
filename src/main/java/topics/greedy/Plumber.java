package topics.greedy;

/**
 * GREEDY ALGORITHM PROBLEM: THE DILIGENT PLUMBER
 * It has an optimal solution
 * @author viceg
 */
public class Plumber {
	private int[] tasks; //Time for the tasks that are handled
	
	 /**
	  * Constructor for Plumber objects
	  * @param tasks Time of the different tasks to be done
	  */
	 public Plumber(int[] tasks) {
		 this.tasks = tasks;
	 }
	
	 /**
	  * Calculates the total waiting time of customers  
	  * @return Total waiting time
	  */
	public int getTotalTimeOfWait(){
		int cumulative = 0;
		int time = 0;
		for (int i = 0; i < tasks.length; i++){
			cumulative = cumulative + time;
			time = time + tasks[i];
		}
		return time+cumulative;
	}
	
}
