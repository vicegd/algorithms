package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Majoritarian element JUnit tests
 * @author viceg
 */
public class MajoritarianElementTest {
	private static MajoritarianElement elem;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		elem = new MajoritarianElement();
	}
	
	/**
	 * Calculates whether there is a majoritarian element using an iterative approach
	 */
	@Test
	public void majoritarianIterativeNo() {
		int[] v = {3, 8, 3, 1, 7, 3};
		boolean result = elem.majoritarian1(v);
		assertEquals(false, result);
	}	
	
	/**
	 * Calculates whether there is a majoritarian element using an iterative approach
	 */
	@Test
	public void majoritarianIterativeYes() {
		int[] v = {3, 8, 3, 1, 3, 3};
		boolean result = elem.majoritarian1(v);
		assertEquals(true, result);
	}	
	
	/**
	 * Calculates whether there is a majoritarian element using a recursive approach
	 */
	@Ignore("Not ready yet")	
	@Test
	public void majoritarianRecursiveNo() {
		int[] v = {3, 8, 3, 1, 7, 3};
		boolean result = elem.majoritarian2(v);
		assertEquals(false, result);
	}	
	
	/**
	 * Calculates whether there is a majoritarian element using a recursive approach
	 */
	@Ignore("Not ready yet")
	@Test
	public void majoritarianRecursiveYes() {
		int[] v = {3, 8, 3, 1, 3, 3};
		boolean result = elem.majoritarian2(v);
		assertEquals(true, result);
	}
	
	/**
	 * Calculates whether there is a majoritarian element using recursive approach
	 */
	@Test
	public void majoritarianRecursive2No() {
		int[] v = {3, 8, 3, 1, 7, 3};
		boolean result = elem.majoritarian3(v);
		assertEquals(false, result);
	}	
	
	/**
	 * Calculates whether there is a majoritarian element using a recursive approach
	 */
	@Test
	public void majoritarianRecursive2Yes() {
		int[] v = {3, 8, 3, 1, 3, 3};
		boolean result = elem.majoritarian3(v);
		assertEquals(true, result);
	}	
	

	
}
