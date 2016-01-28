package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SomePlumbers JUnit tests
 * @author viceg
 */
public class SomePlumbersTest {
	private static Logger log = LoggerFactory.getLogger(SomePlumbersTest.class);
	private SomePlumbers somePlumbers;
	private int[] times; //Time for the tasks that are handled
	private int[] tasks; //Time for the tasks that are handled (sorted in some way)
	private int[][] plumbers; //Load of each of the available plumbers
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Some Plumbers - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Some Pumbers - Teardown");
	}
	
	/**
	 * It gives the total waiting time. Not optimal solution
	 * Assuming that tasks arrived in this order {2, 5, 4, 8, 6, 7, 3} with 3 plumbers
	 */
	@Test
	public void testWaitingTimeBad() {
		times = new int[] {2, 5, 4, 8, 6, 7, 3}; //Time of each of the tasks that the plumbers need to do
		tasks = new int[times.length]; //We need to assign the times in some order (can be a good or a bad order)
		int numberOfPlumbers = 3; //Number of plumbers
		plumbers = new int[numberOfPlumbers][tasks.length]; //Each plumber can manage at most a number equal to the maximum number of tasks
		
		somePlumbers = new SomePlumbers();
		somePlumbers.orderInWhichTasksAreHandledBADWAY(tasks, times);
		somePlumbers.assignTasksToPlumbersBADWAY(plumbers, tasks);
		int result = somePlumbers.getTotalTimeOfWait(plumbers);
		log.trace("The total time people wait if the plumbers handles calls in a bad way is: " + result);

		//Since I use random numbers, I will not use asserts at this point
	}
	
	/**
	 * It gives the total waiting time. Optimal solution
	 * Assuming that tasks arrived in this order {2, 5, 4, 8, 6, 7, 3} with 3 plumbers
	 */
	@Ignore("Not ready yet")
	@Test
	public void testWaitingTimeOk() {
		times = new int[] {2, 5, 4, 8, 6, 7, 3}; //Time of each of the tasks that the plumbers need to do
		tasks = new int[times.length]; //We need to assign the times in some order (can be a good or a bad order)
		int numberOfPlumbers = 3; //Number of plumbers
		plumbers = new int[numberOfPlumbers][tasks.length]; //Each plumber can manage at most a number equal to the maximum number of tasks
		
		somePlumbers = new SomePlumbers();
		somePlumbers.orderInWhichTasksAreHandledBESTWAY(tasks, times);
		somePlumbers.assignTasksToPlumbersBESTWAY(plumbers, tasks);
		int result = somePlumbers.getTotalTimeOfWait(plumbers);
		log.trace("The total time people wait if the plumbers handles calls in the best way is: " + result);

		assertEquals(51, result);
	}

	
}
