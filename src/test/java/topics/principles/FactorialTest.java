package topics.principles;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Factorial JUnit tests
 * @author viceg
 */
public class FactorialTest {
	private static Factorial fact;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		fact = new Factorial();
	}
	
	/**
	 * Calculates the factorial of -4: NO OK
	 */
	@Test
	public void factorialNegative() {
		int result = fact.factorialOK(-4);
		assertEquals("The factorial of an integer value should not be calculated", -1, result);
	}
	
	/**
	 * Calculates the factorial of 4 = 24
	 */
	@Test
	public void factorialPositive() {
		int result = fact.factorialOK(4);
		assertEquals("The factorial of 4 should be 24", 24, result);
	}
	
}
