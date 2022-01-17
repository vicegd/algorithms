package topics.introduction;

import static org.junit.Assert.assertEquals;
import java.time.Duration;
import java.time.Instant;
import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest5 {
	/**
	 * Computes the max pairwise product
	 */
	@Test
	public void test() {
		MaxPairWiseProduct5 maxPairWiseProduct = new MaxPairWiseProduct5();
		Instant start = Instant.now();
		long result = maxPairWiseProduct.compute();
		Instant end = Instant.now();
		
		assertEquals("The max pairwise product was not calculated correctly", 9801, result);
		
		Duration duration = Duration.between(start, end);

		System.out.println(duration.getSeconds() + " seconds.");
		
        //get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        //calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(memory + " bytes");
	}

}
