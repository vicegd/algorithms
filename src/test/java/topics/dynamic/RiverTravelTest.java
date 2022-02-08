package topics.dynamic;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RiverTravel JUnit tests
 * @author viceg
 */
public class RiverTravelTest {
	private static Logger log = LoggerFactory.getLogger(RiverTravelTest.class);
	private RiverTravel travel;
	private int[][]t; //Fees from Ei to Ej (i<j)
	private int[][]c; //Minimum cost for each pair of docks (to be calculated)
	private int[][]expectedResult; //Expected Result
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("River Travel Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("River Travel Tests - Teardown");
	}
	
	/**
	 * It gives the minimum cost for each pair of docks
	 */
	@Test
	public void testRiverTravel() {
		int n=5; //Number of docks
		t = new int[n][n]; //Fees from Ei to Ej (i<j)
		t[0][1]=3;t[0][2]=8;t[0][3]=9;t[0][4]=20;
		t[1][2]=5;t[1][3]=5;t[1][4]=2;
		t[2][3]=3;t[2][4]=6;
		t[3][4]=2;
			
		c = new int[n][n]; //Minimum cost for each pair of docks (to be calculated)
		
		expectedResult = new int[n][n]; 
		expectedResult[0][1]=3;expectedResult[0][2]=8;expectedResult[0][3]=8;expectedResult[0][4]=5;
		expectedResult[1][2]=5;expectedResult[1][3]=5;expectedResult[1][4]=2;
		expectedResult[2][3]=3;expectedResult[2][4]=5;
		expectedResult[3][4]=2;
		
		travel = new RiverTravel();
		travel.riverTravel(t, c);
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				assertEquals(expectedResult[i][j], c[i][j]);
			}
		}
		travel.writeMatrix(t);
		travel.writeMatrix(c);
	}
	
	/**
	 * It gives the minimum cost for each pair of docks
	 */
	@Test
	public void testRiverTravel2() {
		int n=5; //Number of docks
		t = new int[n][n]; //Fees from Ei to Ej (i<j)
		t[0][1]=6;t[0][2]=9;t[0][3]=12;t[0][4]=22;
		t[1][2]=5;t[1][3]=12;t[1][4]=17;
		t[2][3]=4;t[2][4]=14;
		t[3][4]=9;
			
		c = new int[n][n]; //Minimum cost for each pair of docks (to be calculated)
		
		expectedResult = new int[n][n]; 
		expectedResult[0][1]=6;expectedResult[0][2]=9;expectedResult[0][3]=12;expectedResult[0][4]=21;
		expectedResult[1][2]=5;expectedResult[1][3]=9;expectedResult[1][4]=17;
		expectedResult[2][3]=4;expectedResult[2][4]=13;
		expectedResult[3][4]=9;
		
		travel = new RiverTravel();
		travel.riverTravel(t, c);
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				assertEquals(expectedResult[i][j], c[i][j]);
			}
		}
		travel.writeMatrix(t);
		travel.writeMatrix(c);
	}
	
}
