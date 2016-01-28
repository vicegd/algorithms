package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AgentsTasks JUnit tests
 * @author viceg
 */
public class AgentsTasksTest {
	private static Logger log = LoggerFactory.getLogger(AgentsTasksTest.class);
	private AgentsTasks tasks;
	private int [][] c; //Matrix of costs
	private int [] sol; //Array for solution
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Agents Tasks - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Agents Tasks - Teardown");
	}
	
	/**
	 * Gives the result if each agent is assigned to the less expensive task
	 */
	@Test
	public void testAgentsTasks() {
		int n = 4; //Number of tasks and workers (size of the problem)
		c = new int[n][n]; //Matrix of costs
	    c[0][0]=11;c[0][1]=12;c[0][2]=18;c[0][3]=40;
	    c[1][0]=14;c[1][1]=15;c[1][2]=13;c[1][3]=22;
	    c[2][0]=11;c[2][1]=17;c[2][2]=19;c[2][3]=23;
	    c[3][0]=17;c[3][1]=14;c[3][2]=20;c[3][3]=28;  
	    sol = new int[n]; //Array for solution
	    
	    tasks = new AgentsTasks(c);
		tasks.greedy1(sol);
		int result = tasks.getCost1(sol);
		
		assertEquals(69, result);
	}
	
	/**
	 * Gives the result if each tasks is assigned to the less expensive agent
	 */
	@Ignore("Not ready yet")
	@Test
	public void testTasksAgents() {
		int n = 4; //Number of tasks and workers (size of the problem)
		c = new int[n][n]; //Matrix of costs
	    c[0][0]=11;c[0][1]=12;c[0][2]=18;c[0][3]=40;
	    c[1][0]=14;c[1][1]=15;c[1][2]=13;c[1][3]=22;
	    c[2][0]=11;c[2][1]=17;c[2][2]=19;c[2][3]=23;
	    c[3][0]=17;c[3][1]=14;c[3][2]=20;c[3][3]=28;  
	    sol = new int[n]; //Array for solution
	    
	    tasks = new AgentsTasks(c);
		tasks.greedy2(sol);
		int result = tasks.getCost2(sol);
		
		assertEquals(61, result);
	}

}
