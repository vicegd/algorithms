package topics.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Permutations JUnit tests
 * @author viceg
 */
public class PermutationsTest {
	private static Logger log = LoggerFactory.getLogger(PermutationsTest.class);
	private Permutations permutations;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Permutations Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Permutations Tests - Teardown");
	}
	
	/**
	 * Calculates the number of permutations for 4 elements
	 */
	@Test
	public void testPermutations() {
		permutations = new Permutations(4);
		permutations.backtracking();
		int result = permutations.getNumberOfPermutations();
		assertEquals(24, result);
	}
	
	/**
	 * Calculates the number of permutations for 5 elements
	 */
	@Test
	public void testPermutations2() {
		permutations = new Permutations(5);
		permutations.backtracking();
		int result = permutations.getNumberOfPermutations();
		assertEquals(120, result);
	}
	
}
