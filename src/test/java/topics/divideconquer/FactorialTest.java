package topics.divideconquer;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Factorial JUnit tests
 * @author viceg
 */
public class FactorialTest {
	private static Factorial factorial;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		factorial = new Factorial();
	}
	
	/**
	 * Calculates the factorial of a number in an iterative way
	 */
	@Test
	public void factorialIterative() {
		int result = factorial.fact1(6);
		assertEquals(720, result);
	}	

	/**
	 * Calculates the factorial of a number in a recursive way
	 */
	@Ignore("Not ready yet")
	@Test
	public void factorialRecursive() {
		int result = factorial.fact2(6);
		assertEquals(720, result);
	}	
	
}
