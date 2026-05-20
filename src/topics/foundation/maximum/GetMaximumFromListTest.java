package topics.foundation.maximum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * <h1>Validation Suite for Maximum Value Extraction</h1>
 * <p>
 * Validates the arithmetic accuracy of the linear search algorithm against 
 * predefined arrays to ensure boundary conditions and maximum constraints 
 * are strictly honored.
 * </p>
 *
 * @author vicegd
 */
class GetMaximumFromListTest {
    private static final Logger log = LoggerFactory.getLogger(GetMaximumFromListTest.class);
    private static GetMaximumFromList calculator;

    /**
     * Initializes context and instantiates the computational engine 
     * prior to executing the validations.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Maximum Value Extraction Validation Context");
        calculator = new GetMaximumFromList();
    }

    /**
     * <p><strong>Scenario:</strong> Extracting the maximum from an array containing assorted integers.</p>
     * <p><strong>Expected Outcome:</strong> The sequential search correctly identifies and extracts exactly 10.</p>
     */
    @Test
    void shouldAccuratelyExtractMaximumElement() {
        int[] numbers = {3, 1, 10, 5, -1};
        
        int result = calculator.max(numbers);
        
        assertEquals(10, result, 
            "The algorithmic extraction for {3, 1, 10, 5, -1} must equate to exactly 10.");
    }

    /**
     * <p><strong>Scenario:</strong> Verifying the algorithm avoids mathematically incorrect extractions.</p>
     * <p><strong>Expected Outcome:</strong> The calculated maximum strictly rejects a falsely presumed maximum of 1.</p>
     */
    @Test
    void shouldRejectIncorrectMaximumValues() {
        int[] numbers = {3, 1, 10, 5, -1};
        
        int result = calculator.max(numbers);
        
        assertNotEquals(1, result, 
            "The algorithmic extraction must mathematically reject an incorrect maximum such as 1.");
    }
}