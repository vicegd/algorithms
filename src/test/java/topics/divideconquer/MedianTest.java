package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Median JUnit tests
 * @author viceg
 */
public class MedianTest {
	private static Median elem;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		elem = new Median();
	}
	
	/**
	 * Calculates the median using an iterative approach
	 */
	@Ignore("Not ready yet")
	@Test
	public void medianIterative() {
		int[] v = {5, 8, 3, 1, 18, 12, 10, 7};
		int result = elem.median1(v);
		assertEquals(8, result);
	}	
	
	/**
	 * Calculates the median using a recursive approach
	 */
	@Test
	public void medianRecursive() {
		int[] v = {5, 8, 3, 1, 18, 12, 10, 7};
		int result = elem.median2(v);
		assertEquals(8, result);
	}
	
}
