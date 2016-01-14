package topics.principles;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * GetMaximumFromList JUnit tests
 * @author viceg
 */
public class GetMaximumFromListTest {
	private static GetMaximumFromList op;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		op = new GetMaximumFromList();
	}
	
	/**
	 * Calculates the maximum of the array => 10
	 */
	@Test
	public void max() {
		int[] list = {3, 1, 10, 5, -1};
		int result = op.max(list);
		assertEquals("The operation is not correct. It should be 10", 10, result);
	}
	
	/**
	 * Calculates the maximum of the array => 10
	 */
	@Test
	public void maxNotCorrect() {
		int[] list = {3, 1, 10, 5, -1};
		int result = op.max(list);
		assertNotEquals("The operation is not correct. It should not be 1", 1, result);
	}
	
}
