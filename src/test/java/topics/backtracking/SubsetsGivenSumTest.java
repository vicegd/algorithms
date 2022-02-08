package topics.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SubsetsGivenSum JUnit tests
 * @author viceg
 */
public class SubsetsGivenSumTest {
	private static Logger log = LoggerFactory.getLogger(SubsetsGivenSumTest.class);
	private SubsetsGivenSum sum;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Subsets Given Sum Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Subsets Given Sum Tests - Teardown");
	}
	
	/**
	 * Calculates the number of subsets that results in 10 with 5 numbers (assumption 1)
	 */
	@Test
	public void testSubset() {
		sum = new SubsetsGivenSum(5, 10);
		sum.assumption1();
		sum.backtracking(0);
		int result = sum.getNumberOfSolutions();
		assertEquals(3, result);
	}
	
	/**
	 * Calculates the number of subsets that results in 14 for 4 numbers (assumption 2)
	 */
	@Test
	public void testSubset2() {
		sum = new SubsetsGivenSum(4, 14);
		sum.assumption2();
		sum.backtracking(0);
		int result = sum.getNumberOfSolutions();
		assertEquals(1, result);
	}
	
}
