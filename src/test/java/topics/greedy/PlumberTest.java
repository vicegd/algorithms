package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Plumber JUnit tests
 * @author viceg
 */
public class PlumberTest {
	private static Logger log = LoggerFactory.getLogger(PlumberTest.class);
	private Plumber plumber;
	private int[] tasks; //Time for the tasks that are handled
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Plumber - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Plumber - Teardown");
	}
	
	/**
	 * It gives the total waiting time. Not optimal solution
	 * Assuming that tasks arrived in a random order {2, 5, 4, 8, 6, 7, 3}
	 */
	@Ignore("Not ready yet")
	@Test
	public void testWaitingTimeRandom() {
		tasks = new int[] {2, 5, 4, 8, 6, 7, 3}; //Time of each of the tasks that the plumber needs to do
		plumber = new Plumber(tasks);
		
		int result = plumber.getTotalTimeOfWait();
		
		assertEquals(131, result);
	}
	
	/**
	 * It gives the total waiting time. Optimal solution
	 * Assuming that tasks arrived in a smallest-first order {2, 3, 4, 5, 6, 7, 8}
	 */
	@Ignore("Not ready yet")
	@Test
	public void testWaitingTimeSmallestFirst() {
		tasks = new int[] {2, 3, 4, 5, 6, 7, 8};
		plumber = new Plumber(tasks);
		
		int result = plumber.getTotalTimeOfWait();
		
		assertEquals(112, result);
	}
	
	/**
	 * It gives the total waiting time. Not optimal solution
	 * Assuming that tasks arrived in a biggest-first order {8, 7, 6, 5, 4, 3, 2, 1}
	 */
	@Ignore("Not ready yet")
	@Test
	public void testWaitingTimeBiggestFirst() {
		tasks = new int[] {8, 7, 6, 5, 4, 3, 2, 1};
		plumber = new Plumber(tasks);
		
		int result = plumber.getTotalTimeOfWait();
		
		assertEquals(204, result);
	}

	
}
