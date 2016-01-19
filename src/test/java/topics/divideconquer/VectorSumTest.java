package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * VectorSum JUnit tests
 * @author viceg
 */
public class VectorSumTest {
	private static VectorSum sum;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		sum = new VectorSum();
	}
	
	/**
	 * Calculates the addition of the numbers in an array in an iterative way
	 */
	@Test
	public void sumIterative() {
		int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
		int result = sum.sum1(v);
		assertEquals(225, result);
	}	
	
	/**
	 * Calculates the addition of the numbers in an array in a recursive way (subtraction)
	 */
	@Test
	public void sumRecursiveSubtraction() {
		int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
		int result = sum.sum2(v);
		assertEquals(225, result);
	}
	
	/**
	 * Calculates the addition of the numbers in an array in a recursive way (division)
	 */
	@Test
	public void sumRecursiveDivision() {
		int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
		int result = sum.sum3(v);
		assertEquals(225, result);
	}
	
}
