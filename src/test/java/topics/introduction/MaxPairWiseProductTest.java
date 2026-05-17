package topics.introduction;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Maximum Pairwise Product</h1>
 * <p>
 * Verifies the mathematical integrity of the product calculation. It specifically 
 * validates that the algorithmic boundary properly handles large integer multiplications 
 * without suffering from silent arithmetic overflow.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest.class);

    /**
     * <p><strong>Scenario:</strong> Computing the product of 100,000 and 1,000,000.</p>
     * <p><strong>Expected Outcome:</strong> The mathematical evaluation actively avoids 
     * 32-bit overflow constraints and strictly matches the expected 64-bit result.</p>
     */
    @Test
    void shouldAccuratelyComputeLargeProductWithoutOverflow() {
        log.trace("Executing Max Pairwise Product Overflow Validation");
        
        var calculator = new MaxPairWiseProduct();
        long result = calculator.compute();
        
        assertEquals(100_000_000_000L, result, 
            "The product must be evaluated in 64-bit memory space to accurately yield 100,000,000,000.");
    }
}