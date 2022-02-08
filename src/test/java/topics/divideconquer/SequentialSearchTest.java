package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Sequential search JUnit tests
 * @author viceg
 */
public class SequentialSearchTest {
	private static SequentialSearch search;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		search = new SequentialSearch();
	}
	
	/**
	 * Searches the position of a number in an array in an iterative way
	 */
	@Test
	public void sequeantialSearchiIterative() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 109;
		int result = search.sequentialSearch1(v, x);
		assertEquals(3, result);
	}	
	
	/**
	 * Searches the position of a number in an array in an iterative way
	 * The number is not in the array
	 */
	@Test
	public void sequeantialSearchIterative2() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 99;
		int result = search.sequentialSearch1(v, x);
		assertEquals(Integer.MIN_VALUE, result);
	}	
	
	/**
	 * Searches the position of a number in an array in recursive way
	 */
	@Test
	public void sequeantialSearchRecursive() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 109;
		int result = search.sequentialSearch2(v, x);
		assertEquals(3, result);
	}	
	
	/**
	 * Searches the position of a number in an array in a recursive way
	 * The number is not in the array
	 */
	@Test
	public void sequeantialSearchRecursive2() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 99;
		int result = search.sequentialSearch2(v, x);
		assertEquals(Integer.MIN_VALUE, result);
	}	

	
}
