package topics.introduction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * <h1>Test Suite for Array Summation</h1>
 * <p>
 * Validates the arithmetic accuracy of the linear aggregation algorithm against 
 * predefined arrays containing both positive and negative sequences.
 * </p>
 *
 * @author vicegd
 */
class GetAdditionFromListTest {
    private static final Logger log = LoggerFactory.getLogger(GetAdditionFromListTest.class);
    private static GetAdditionFromList calculator;

    /**
     * Initializes context and instantiates the computational engine 
     * prior to executing the validations.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Array Summation Validation Context");
        calculator = new GetAdditionFromList();
    }

    /**
     * <p><strong>Scenario:</strong> Calculating the sum of an array containing positive and negative integers.</p>
     * <p><strong>Expected Outcome:</strong> The sequential summation correctly aggregates the total to exactly 18.</p>
     */
    @Test
    void shouldAccuratelySumArrayElements() {
        int[] numbers = {3, 1, 10, 5, -1};
        
        int result = calculator.sum(numbers);
        
        assertEquals(18, result, 
            "The algorithmic aggregation of {3, 1, 10, 5, -1} must equate to exactly 18.");
    }

    /**
     * <p><strong>Scenario:</strong> Verifying the algorithm avoids mathematically incorrect aggregations.</p>
     * <p><strong>Expected Outcome:</strong> The calculated sum strictly rejects the falsely presumed total of 19.</p>
     */
    @Test
    void shouldRejectIncorrectSummationTotals() {
        int[] numbers = {3, 1, 10, 5, -1};
        
        int result = calculator.sum(numbers);
        
        assertNotEquals(19, result, 
            "The algorithmic aggregation must mathematically reject an incorrect total such as 19.");
    }
}