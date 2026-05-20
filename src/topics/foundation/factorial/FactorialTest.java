package topics.foundation.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Validation Suite for Factorial Algorithm</h1>
 * <p>
 * Verifies the mathematical exactness of the recursive factorial implementations, 
 * ensuring boundary conditions are respected and arithmetic outputs are strictly 
 * honored. Evaluates logic transparently utilizing native language assertions.
 * </p>
 *
 * @author vicegd
 */
public class FactorialTest {
    private static final Logger log = LoggerFactory.getLogger(FactorialTest.class);
    private Factorial factorialEngine;

    /**
     * Initializes the context and instantiates the computational engine 
     * prior to executing mathematical validations.
     */
    public void setup() {
        log.trace("Initializing Factorial Validation Context");
        factorialEngine = new Factorial();
    }
    
    /**
     * Cleans up resources after the validations have completed execution.
     */
    public void teardown() {
        log.trace("Tearing down Factorial Validation Context");
    }

    /**
     * <p><strong>Scenario:</strong> Invoking the safe computation with a negative integer (-4).</p>
     * <p><strong>Expected Outcome:</strong> The algorithmic boundaries must reject the mathematical anomaly and safely return -1.</p>
     */
    public void shouldRejectNegativeInputs() {
        long result = factorialEngine.computeSafe(-4);
        
        assert result == -1 : "The factorial calculation of a negative integer must be rejected, yielding -1.";
    }

    /**
     * <p><strong>Scenario:</strong> Invoking the safe computation with a valid positive integer (4).</p>
     * <p><strong>Expected Outcome:</strong> The mathematical cascade must accurately evaluate and strictly equate to 24.</p>
     */
    public void shouldComputePositiveFactorials() {
        long result = factorialEngine.computeSafe(4);
        
        assert result == 24 : "The factorial evaluation of 4 must yield exactly 24.";
    }
}