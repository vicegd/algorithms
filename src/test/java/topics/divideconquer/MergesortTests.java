package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Mergesort JUnit tests
 * @author viceg
 */
public class MergesortTests {
	private static Mergesort sort;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		sort = new Mergesort();
	}
	
	/**
	 * Sorts an array that is inversely sorted at the beginning
	 */
	@Test
	public void mergesort() {
		int n = 1000;  
		int[] v = new int[n];
		for (int i=0; i<n; i++) 
			v[i] = n-i-1; //e.g., reverse order
		sort.mergesort(v);
		for (int i=0; i<n; i++) 
			assertEquals(i, v[i]);
	}	
	

	
}
