package topics.dynamic;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Combinations JUnit tests
 * @author viceg
 */
public class CombinationsTest {
	private static Logger log = LoggerFactory.getLogger(CombinationsTest.class);
	private Combinations comb;
	int[][] table; //To calculate the combinations in a dynamic programming way
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Combinations Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Combinations Tests - Teardown");
	}
	
	/**
	 * It gives the combinations value (n taken k by k)
	 */
	@Test
	public void testCombinationsDP() {
		int n = 52; 
		int k = 5;

		table = new int[n+1][k+1];
		comb = new Combinations();
		int result = comb.combinations(table, n, k);
		//comb.writeSolution(table, n, k);		
		assertEquals(2598960, result);
	}
	
	/**
	 * It gives the combinations value (n taken k by k)
	 */
	@Test
	public void testCombinationsDP2() {
		int n = 40; 
		int k = 30;

		table = new int[n+1][k+1];
		comb = new Combinations();
		int result = comb.combinations(table, n, k);
		//comb.writeSolution(table, n, k);
		//This result is too big to be calculated with D&C instead
		assertEquals(847660528, result);
	}

	/**
	 * It gives the combinations value (n taken k by k)
	 */
	@Ignore("Not ready yet")
	@Test
	public void testCombinationsDC() {
		int n = 52; 
		int k = 5;

		comb = new Combinations();
		long result = comb.combinationsDivideAndConquer(n, k);	
		assertEquals(2598960, result);
	}
}
