package topics.sorting;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import topics.sorting.utils.ISortingAlgorithm;

/**
 * Direct selection sorting JUnit tests
 * @author viceg
 */
public class DirectSelectionTest {
	private static ISortingAlgorithm sorting;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		sorting = new DirectSelection();
	}
	
	/**
	 * Sorts the elements of the array using the algorithm
	 */
	@Test
	public void sortSequence() {
		int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
		int[] elementsSorted = {1, 2, 3, 4, 5, 6, 7, 8};
		sorting.sort(elements, true);
		for (int i = 0; i < elements.length; i++) {
			assertEquals("The array is not sorted in position " + i, elementsSorted[i], elements[i]);
		}
	}
	
	/**
	 * Sorts the elements of the array using the algorithm
	 */
	@Test
	public void sortSequence2() {
		int[] elements = {159, 20, 170, 13, 28, 14, 23, 83, 3690, 98, 1561, 70, 65, 41, 42, 15};
		int[] elementsSorted = {13, 14, 15, 20, 23, 28, 41, 42, 65, 70, 83, 98, 159, 170, 1561, 3690};
		sorting.sort(elements, true);
		for (int i = 0; i < elements.length; i++) {
			assertEquals("The array is not sorted in position " + i, elementsSorted[i], elements[i]);
		}
	}
	

	
}
