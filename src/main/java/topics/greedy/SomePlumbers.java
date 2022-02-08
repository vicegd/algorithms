package topics.greedy;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.Quicksort;

/**
 * GREEDY ALGORITHM PROBLEM: SOME DILIGENT PLUMBERS
 * It has an optimal solution
 * @author viceg
 */
public class SomePlumbers {
	private static Logger log = LoggerFactory.getLogger(SomePlumbers.class);
	
	/**
	 * Calculates the total waiting time of customers
	 * @param plumbers Information about the tasks that any plumber performs
	 * @return Total waiting time
	 */
	public int getTotalTimeOfWait(int[][] plumbers){
		int totalWaitingTime = 0;
		for (int i = 0; i < plumbers.length; i++) { //Number of plumbers
			int time = 0;
			int cumulative = 0;
			for (int j = 0; j < plumbers[i].length; j++) { //Number of tasks for each plumber
				if ((plumbers[i][j]) != 0) { //The plumber has a task assigned
					cumulative = cumulative + time;
					time = time + plumbers[i][j];
				}
			}
			log.trace("\tThe waiting time of customers for plumber " + i + " is " + (time+cumulative) + " units of time");
			totalWaitingTime += time+cumulative;
		}
		return totalWaitingTime;
	}
	
	/**
	 * Sorts the tasks in a random order. In fact, it does not change anything
	 * O(n)
	 * @param tasks Final order in which the different tasks are going to be done
	 * @param times Times for the different tasks to be done
	 */
	public void orderInWhichTasksAreHandledBADWAY(int[] tasks, int[] times) {
		for (int i = 0; i < times.length; i++) {
			tasks[i] = times[i]; //The tasks are handled in the same order the customers call. It is a bad idea. 
		}
	}
	
	/**
	 * Assigns tasks to plumbers
	 * @param plumbers To save the tasks that any of the plumbers are going to perform
	 * @param tasks Final order in which the different tasks are going to be done
	 */
	public void assignTasksToPlumbersBADWAY(int[][] plumbers, int[] tasks) {
		Random random = new Random();
		for (int i = 0; i < tasks.length; i++) { //The tasks are handled randomly by any of the plumbers. It is again a bad idea
			int plumber = random.nextInt(plumbers.length);
			plumbers[plumber][i] = tasks[i];
			log.trace("\tPlumber: " + plumber + " is going to handled tasks of time: " + tasks[i]);
		}
	}
	
	/**
	 * Sorts the tasks from smallest to biggest
	 * O(n) + O(nlogn) - O(nlogn)
	 * @param tasks Final order in which the different tasks are going to be done
	 * @param times Times for the different tasks to be done
	 */
	public void orderInWhichTasksAreHandledBESTWAY(int[] tasks, int[] times) {
		for (int i = 0; i < times.length; i++) { //O(n)
			tasks[i] = times[i]; //The tasks are handled in the same order the customers call
		}
		Quicksort quicksort = new Quicksort();
		quicksort.sort(tasks); //we order the array using Quicksort O(nlogn)
	}
	
	/**
	 * Assigns tasks to the less busy plumber
	 * @param plumbers To save the tasks that any of the plumbers are going to perform
	 * @param tasks Final order in which the different tasks are going to be done
	 */
	public void assignTasksToPlumbersBESTWAY(int[][] plumbers, int[] tasks) {
		int nPlumber = 0; //Index of the current plumber
		for (int i = 0; i < tasks.length; i++) { //Each of the tasks
			plumbers[nPlumber][i] = tasks[i]; //The current plumber performs the task
			log.trace("\tPlumber: " + nPlumber + " is going to handled tasks of time: " + tasks[i]);
			nPlumber++; //We go to the next plumber 
			if (nPlumber == plumbers.length) //If it is the last plumber, we start again
				nPlumber = 0; //Again, first plumber
		}
	}

}
