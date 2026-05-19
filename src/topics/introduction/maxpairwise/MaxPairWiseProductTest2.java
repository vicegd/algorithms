package topics.introduction.maxpairwise;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Maximum Pairwise Product (Native 64-bit)</h1>
 * <p>
 * Verifies the mathematical integrity of the product calculation. It strictly 
 * validates that the algorithmic structure inherently prevents arithmetic overflow 
 * by operating entirely within 64-bit limits, without requiring execution-time adjustments.
 * </p>
 *
 * @author vicegd
 */
class MaxPairWiseProductTest2 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProductTest2.class);

    /**
     * <p><strong>Scenario:</strong> Computing the product of 100,000 and 1,000,000 using native long types.</p>
     * <p><strong>Expected Outcome:</strong> The mathematical evaluation strictly matches 
     * the expected 100,000,000,000 boundary safely.</p>
     */
    @Test
    void shouldAccuratelyComputeLargeProductNatively() {
        log.trace("Executing Native 64-bit Overflow Resolution Validation");
        
        var calculator = new MaxPairWiseProduct2();
        long result = calculator.compute();
        
        assertEquals(100_000_000_000L, result, 
            "The native 64-bit operands must accurately compute to exactly 100,000,000,000 without arithmetic truncation.");
    }
}