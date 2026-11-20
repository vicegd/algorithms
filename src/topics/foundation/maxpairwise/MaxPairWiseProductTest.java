package topics.foundation.maxpairwise;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * <h1>Validation Suite for Maximum Pairwise Product (32-bit Overflow Demonstration)</h1>
 * <p>
 * Demonstrates why evaluating large integer multiplications in 32-bit {@code int} 
 * arithmetic causes silent overflow, motivating the 64-bit {@code long} fix in 
 * {@link MaxPairWiseProduct2}.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest.class);

    /**
     * <p><strong>Scenario:</strong> Computing the product of 100,000 and 1,000,000 using 32-bit {@code int}.</p>
     * <p><strong>Expected Outcome:</strong> The 32-bit multiplication overflows {@link Integer#MAX_VALUE}
     * (2,147,483,647) and wraps around to 1,215,752,192 instead of 100,000,000,000.</p>
     */
    @Test
    void shouldAccuratelyComputeLargeProductWithoutOverflow() {
        log.trace("Executing Max Pairwise Product 32-bit Overflow Demonstration");
        
        var calculator = new MaxPairWiseProduct();
        int result = calculator.compute();
        
        assertNotEquals(100_000_000_000L, result,
            "32-bit int multiplication cannot hold 100,000,000,000 and must overflow.");
        assertEquals(1_215_752_192, result,
            "The 32-bit overflowed value of 100,000 * 1,000,000 should wrap around to 1,215,752,192.");
    }
}