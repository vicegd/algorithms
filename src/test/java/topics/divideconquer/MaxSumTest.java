package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * MaxSum JUnit tests
 * @author viceg
 */
public class MaxSumTest {
	private static MaxSum max;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		max = new MaxSum();
	}
	
	/**
	 * Calculates the maximum sum of the subsequences in an iterative way
	 */
	@Test
	public void maxSumIterative() {
		int[] v = {5, -4, 3, 2, 5, -1};
		int result = max.maxSum1(v);
		assertEquals(11, result);
	}	
	
	/**
	 * Calculates the maximum sum of the subsequences in an iterative (improved) way
	 */
	@Ignore("Not ready yet")
	@Test
	public void maxSumIterative2() {
		int[] v = {5, -4, 3, 2, 5, -1};
		int result = max.maxSum2(v);
		assertEquals(11, result);
	}
	
	/**
	 * Calculates the maximum sum of the subsequences in a recursive way
	 */
	@Test
	public void maxSumRecursive() {
		int[] v = {5, -4, 3, 2, 5, -1};
		int result = max.maxSum3(v);
		assertEquals(11, result);
	}
	
}
