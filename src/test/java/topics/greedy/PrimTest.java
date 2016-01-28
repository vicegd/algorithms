package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prim JUnit tests
 * @author viceg
 */
public class PrimTest {
	private static Logger log = LoggerFactory.getLogger(PrimTest.class);
	private Prim prim;
	private int weight[][] = new int[50][50]; //At most we will have 50 vertices
    private int numberOfVertices; //Number of vertices we will have
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Prim - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Prim - Teardown");
	}
	
	/**
	 * Calculates the minimum spanning tree using the algorithm of Prim
	 */
	@Ignore("Not ready yet")
	@Test
	public void testPrim() {
        numberOfVertices = 7; 
	    weight[0][1] = weight[1][0] = 7; 
	    weight[0][3] = weight[3][0] = 5; 
	    weight[1][2] = weight[2][1] = 8; 
	    weight[1][3] = weight[3][1] = 9; 
	    weight[1][4] = weight[4][1] = 7; 
	    weight[2][4] = weight[4][2] = 5; 
	    weight[3][4] = weight[4][3] = 15; 
	    weight[3][5] = weight[5][3] = 6; 
	    weight[4][5] = weight[5][4] = 8; 
	    weight[4][6] = weight[6][4] = 9; 
	    weight[5][6] = weight[6][5] = 11; 
	    //the value is zero for others
	    
	    prim = new Prim(weight, numberOfVertices);
	    prim.minimumSpanningTree();
	 	int result = prim.getSolution();
	 	
	 	assertEquals(39, result);
	}
	

}
