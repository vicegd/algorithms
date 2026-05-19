package topics.introduction.maxpairwise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Maximum Pairwise Product (64-bit Resolution)</h1>
 * <p>
 * Evaluates the product of two specified integers. This iteration permanently 
 * resolves the <strong>Integer Overflow</strong> vulnerability by natively defining 
 * the fundamental operands within a 64-bit (<code>long</code>) memory space. 
 * This architectural choice guarantees safe arithmetic scaling without requiring 
 * inline mathematical casting during execution.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(1)</code> - The calculation executes natively in constant time.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - Operates using strictly constant memory.</li>
 * </ul>
 *
 * @author vicegd
 */
public class MaxPairWiseProduct2 {
    private static final Logger log = LoggerFactory.getLogger(MaxPairWiseProduct2.class);
    
    private final long operandA;
    private final long operandB;
    
    /**
     * Initializes the computational context with predefined large integers 
     * stored securely within 64-bit architectural bounds.
     */
    public MaxPairWiseProduct2() {
        this.operandA = 100_000L;
        this.operandB = 1_000_000L;
    }
    
    /**
     * Computes the mathematical product of the natively safe 64-bit integers.
     * <p>
     * <strong>Architectural Note:</strong> Maximum 32-bit integer limit is 2,147,483,647.
     * By utilizing 64-bit space (maximum limit 9,223,372,036,854,775,807), the 
     * expected result of 100,000,000,000 is safely accommodated.
     * </p>
     *
     * @return The exact mathematical product.
     */
    public long compute() {
        long result = operandA * operandB;
        log.info("The computed result is = {}", result);
        return result;
    }
}