package topics.branchandbound;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
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
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Agents Tasks Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Agents Tasks Tests - Teardown");
	}
	
	/**
	 * It gives the solution for a specific cost matrix
	 */
	@Test
	public void testAgentsTasks() {
		int n = 4; //Size of the table
		int[][] c = new int[n][n];
    	c[0][0]=11;c[0][1]=12;c[0][2]=18;c[0][3]=40;
 	   	c[1][0]=14;c[1][1]=15;c[1][2]=13;c[1][3]=22;
 	   	c[2][0]=11;c[2][1]=17;c[2][2]=19;c[2][3]=23;
 	   	c[3][0]=17;c[3][1]=14;c[3][2]=20;c[3][3]=28; 
		
		tasks = new AgentsTasks(n, c); 
		tasks.branchAndBound(tasks.getRootNode()); 
		tasks.printSolutionTrace(); 
		int result = tasks.getBestNode().getHeuristicValue();
		assertEquals(61, result);
	}
	
}
