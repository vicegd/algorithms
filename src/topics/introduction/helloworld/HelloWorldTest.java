package topics.introduction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * <h1>Validation Suite for Arithmetic Engine</h1>
 * <p>
 * Verifies the exactness and operational integrity of foundational 
 * mathematical evaluations. It enforces strict boundary assertions 
 * to guarantee arithmetic reliability.
 * </p>
 *
 * @author vicegd
 */
class HelloWorldTest {
    private static final Logger log = LoggerFactory.getLogger(HelloWorldTest.class);
    private static HelloWorld arithmeticEngine;
    
    /**
     * Initializes the context and instantiates the computational engine 
     * prior to executing the structural validations.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Arithmetic Validation Context");
        arithmeticEngine = new HelloWorld();
    }
    
    /**
     * <p><strong>Scenario:</strong> Executing a standard mathematical summation (10 + 40).</p>
     * <p><strong>Expected Outcome:</strong> The algorithmic aggregation strictly yields 50.</p>
     */
    @Test
    void shouldAccuratelyComputeStandardSummation() {
        int expectedTotal = 50;
        int computedResult = arithmeticEngine.sum(10, 40);
        
        assertEquals(expectedTotal, computedResult, 
            "The operational synthesis of 10 and 40 must mathematically evaluate to exactly 50.");
    }
    
    /**
     * <p><strong>Scenario:</strong> Evaluating deviation limits for a standard summation (10 + 40).</p>
     * <p><strong>Expected Outcome:</strong> The mathematical bounds strictly reject adjacent numerical values.</p>
     */
    @Test
    void shouldRejectIncorrectMathematicalDeviations() {
        int computedResult = arithmeticEngine.sum(10, 40);
        
        assertNotEquals(51, computedResult, 
            "The arithmetic constraint must mathematically reject a superior deviation.");
        assertNotEquals(49, computedResult, 
            "The arithmetic constraint must mathematically reject an inferior deviation.");
    }
}