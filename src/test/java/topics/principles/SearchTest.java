package topics.principles;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Search JUnit tests
 * @author viceg
 */
public class SearchTest {
	private static Search search;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		search = new Search();
	}
	
	/**
	 * Searches number 10 in the array (Sequential)
	 */
	@Test
	public void searchSequentialOK() {
		int[] list = {3, 1, 10, 5, -1};
		boolean result = search.searchSequential(list, 10);
		assertEquals("The operation is not correct. The number should be found", true, result);
	}
	
	/**
	 * Searches number 100 in the array (Sequential)
	 */
	@Test
	public void searchSequentialNo() {
		int[] list = {3, 1, 10, 5, -1};
		boolean result = search.searchSequential(list, 100);
		assertEquals("The operation is not correct. The number should not be found", false, result);
	}
	
	/**
	 * Searches number 10 in the array (Sequential with sentinel)
	 */
	@Test
	public void searchSequentialSentinelOK() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(3); list.add(1); list.add(10); list.add(5); list.add(-1);
		boolean result = search.searchSequentialSentinel(list, 10);
		assertEquals("The operation is not correct. The number should be found", true, result);
	}
	
	/**
	 * Searches number 100 in the array (Sequential with sentinel)
	 */
	@Test
	public void searchSequentialSentinelNo() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(3); list.add(1); list.add(10); list.add(5); list.add(-1);
		boolean result = search.searchSequentialSentinel(list, 100);
		assertEquals("The operation is not correct. The number should not be found", false, result);
	}
	
	/**
	 * Searches number 15 in the array (Binary)
	 */
	@Test
	public void searchBinaryOK() {
		int[] list = {-1, 1, 3, 4, 15, 100};
		boolean result = search.searchBinary(list, 15);
		assertEquals("The operation is not correct. The number should be found", true, result);
	}
	
	/**
	 * Searches number 92 in the array (Binary)
	 */
	@Test
	public void searchBinaryNo() {
		int[] list = {-1, 1, 3, 4, 15, 100};
		boolean result = search.searchBinary(list, 92);
		assertEquals("The operation is not correct. The number should not be found", false, result);
	}
	
}
