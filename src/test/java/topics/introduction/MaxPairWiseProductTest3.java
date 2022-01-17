package topics.introduction;

import static org.junit.Assert.*;
import java.time.Duration;
import java.time.Instant;
import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest3 {
	/**
	 * Computes the max pairwise product
	 */
	@Test
	public void testSum() {
		MaxPairWiseProduct3 maxPairWiseProduct = new MaxPairWiseProduct3();
		Instant start = Instant.now(); //Java8
		long result = maxPairWiseProduct.compute();
		Instant end = Instant.now();
		
		assertEquals("The max pairwise product was not calculated correctly", 9801, result);
		
		Duration duration = Duration.between(start, end);
		System.out.println(duration.getSeconds() + " seconds.");
	}

}
