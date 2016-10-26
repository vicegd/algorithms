package topics.introduction;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest {
	/**
	 * Computes the max pairwise product
	 */
	@Ignore("Just to try")
	@Test
	public void testSum() {
		MaxPairWiseProduct maxPairWiseProduct = new MaxPairWiseProduct();
		int result = maxPairWiseProduct.compute();
		assertEquals("The max pairwise product was not calculated correctly", 100_000_000_000L, result);
	}

}
