package topics.introduction;

import static org.junit.Assert.*;
import java.time.Duration;
import java.time.Instant;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest3 {
	/**
	 * Computes the max pairwise product
	 */
	@Ignore("Just to try")
	@Test
	public void testSum() {
		MaxPairWiseProduct3 maxPairWiseProduct = new MaxPairWiseProduct3();
		Instant start = Instant.now(); //Java8
		long result = maxPairWiseProduct.compute();
		Instant end = Instant.now();
		
		assertEquals("The max pairwise product was not calculated correctly", 9801, result);
		
		Duration duration = Duration.between(start, end);
		assertThat("The execution time was too big", duration.getSeconds(), Matchers.lessThan(3L));
	}

}
