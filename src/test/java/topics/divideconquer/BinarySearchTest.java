package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Binary search JUnit tests
 * @author viceg
 */
public class BinarySearchTest {
	private static BinarySearch search;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		search = new BinarySearch();
	}
	
	/**
	 * Searches the position of a number in an array in an iterative way
	 */
	@Test
	public void binarySearchIterative() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 109;
		int result = search.binarySearch1(v, x);
		assertEquals(3, result);
	}	
	
	/**
	 * Searches the position of a number in an array in an iterative way
	 * The number is not in the array
	 */
	@Test
	public void binarySearchIterative2() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 99;
		int result = search.binarySearch1(v, x);
		assertEquals(Integer.MIN_VALUE, result);
	}	
	
	/**
	 * Searches the position of a number in an array in recursive way
	 */
	@Test
	public void binarySearchRecursive() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 109;
		int result = search.binarySearch2(v, x);
		assertEquals(3, result);
	}	
	
	/**
	 * Searches the position of a number in an array in a recursive way
	 * The number is not in the array
	 */
	@Test
	public void binarySearchRecursive2() {
		int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
		int x = 99;
		int result = search.binarySearch2(v, x);
		assertEquals(Integer.MIN_VALUE, result);
	}	

	
}
