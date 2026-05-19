package topics.introduction.maxpairwise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Maximum Pairwise Product</h1>
 * <p>
 * Evaluates the product of two specified integers. This example serves to 
 * demonstrate the critical vulnerability of <strong>Integer Overflow</strong> 
 * in arithmetic synthesis, strictly enforcing the promotion of 32-bit operands 
 * to 64-bit evaluations prior to multiplication.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(1)</code> - The calculation executes natively in constant time.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Operates using strictly constant memory.</li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct.class);
    
    private final int operandA;
    private final int operandB;
    
    /**
     * Initializes the computational context with hardcoded large integers 
     * designed to trigger a 32-bit arithmetic overflow if not handled correctly.
     */
    public MaxPairWiseProduct() {
        this.operandA = 100_000;
        this.operandB = 1_000_000;
    }
    
    /**
     * Computes the mathematical product of the predefined integers.
     *
     * @return The exact mathematical product safely evaluated as a 64-bit integer.
     */
    public long compute() {
        // Casting the first operand to a 'long' forces the arithmetic processor 
        // to evaluate the multiplication in 64-bit space, preventing overflow truncation.
        long result = (long) operandA * operandB;
        
        log.info("The result is = {}", result);
        return result;
    }
}