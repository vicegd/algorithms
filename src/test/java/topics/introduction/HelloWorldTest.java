package topics.introduction;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * HelloWorld JUnit tests
 * @author vicegs
 */
public class HelloWorldTest {
	private static HelloWorld helloWorld;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		helloWorld = new HelloWorld();
	}
	
	/**
	 * Adds 10 + 40 and expects the result is 50
	 */
	@Test
	public void testSum() {
		int sum = helloWorld.sum(10, 40);
		assertEquals("The addition was not executed correctly", 50, sum);
	}
	
	/**
	 * Adds 10 + 40 and expects the result is not 49 or 51
	 */
	@Test
	public void testSum2() {
		int sum = helloWorld.sum(10, 40);
		assertNotEquals("The addition was not executed correctly", 51, sum);
		assertNotEquals("The addition was not executed correctly", 49, sum);
	}

}
