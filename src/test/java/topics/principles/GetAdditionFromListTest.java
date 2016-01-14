package topics.principles;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * GetAdditionFromList JUnit tests
 * @author viceg
 */
public class GetAdditionFromListTest {
	private static GetAdditionFromList op;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		op = new GetAdditionFromList();
	}
	
	/**
	 * Calculates 3 + 1 + 10 + 5 -1 = 18
	 */
	@Test
	public void addition() {
		int[] list = {3, 1, 10, 5, -1};
		int result = op.sum(list);
		assertEquals("The operation is not correct. It should results 18", 18, result);
	}
	
	/**
	 * Calculates 3 + 1 + 10 + 5 -1 = 18
	 */
	@Test
	public void additionNotCorrect() {
		int[] list = {3, 1, 10, 5, -1};
		int result = op.sum(list);
		assertNotEquals("The operation is not correct. It should not results 19", 19, result);
	}
	
}
