package topics.introduction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.Instant;

import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * MaxPairWiseProduct JUnit tests
 * @author viceg
 */
public class MaxPairWiseProductTest6 {
	/**
	 * Computes the max pairwise product
	 */
	@Test
	public void test() {
		MaxPairWiseProduct6 maxPairWiseProduct = new MaxPairWiseProduct6();
		Instant start = Instant.now();
		long result = maxPairWiseProduct.compute();
		Instant end = Instant.now();
		
		assertEquals("The max pairwise product was not calculated correctly", 9801, result);
		
		Duration duration = Duration.between(start, end);
		assertThat("The execution time was too big", duration.getSeconds(), Matchers.lessThan(3L));
	
        //get the Java runtime
        Runtime runtime = Runtime.getRuntime();
        //calculate the used memory
        long memory = (runtime.totalMemory() - runtime.freeMemory()) / 1024;
        assertThat("The memory usage is too big", memory, Matchers.lessThan(20000L));
	}

}
