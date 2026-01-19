package topics.introduction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest2 {
	/**
	 * Computes the max pairwise product
	 */
	@Test
	public void testSum() {
		MaxPairWiseProduct2 maxPairWiseProduct = new MaxPairWiseProduct2();
		long result = maxPairWiseProduct.compute();
		assertEquals("The max pairwise product was not calculated correctly", 100_000_000_000L, result);
	}

}
