package topics.divideconquer;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * GCG JUnit tests
 * @author viceg
 */
public class GCGTest {
	private static GCG gcg;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		gcg = new GCG();
	}
	
	/**
	 * Calculates the GCG of two numbers in a very slow way
	 */
	@Ignore("Too slow but it works")
	@Test
	public void GCGNaive() {
		long result = gcg.naiveGCD(Integer.MAX_VALUE, Integer.MAX_VALUE-1);
		assertEquals(1, result);
	}	
	
	/**
	 * Calculates the GCG of two numbers in a very slow way
	 */
	@Test
	public void GCGNaive2() {
		long result = gcg.naiveGCD(3918848, 1653264);
		assertEquals(61232, result);
	}	
	
	/**
	 * Calculates the GCG of two numbers in a fast way
	 */
	@Test
	public void GCGEuclidean() {
		long result = gcg.euclideanGCG(Integer.MAX_VALUE, Integer.MAX_VALUE-1);
		assertEquals(1, result);
	}	
	
	/**
	 * Calculates the GCG of two numbers in a fast way
	 */
	@Test
	public void GCGEuclidean2() {
		long result = gcg.euclideanGCG(3918848, 1653264);
		assertEquals(61232, result);
	}	
	
	/**
	 * Calculates the GCG of two numbers in a fast way
	 */
	@Test
	public void GCGEuclidean3() {
		long result = gcg.euclideanGCG(300, 120);
		assertEquals(60, result);
	}	

	
}
