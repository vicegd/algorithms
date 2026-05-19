package topics.divideconquer.vectorsum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Vector Summation Paradigms</h1>
 * <p>
 * Verifies structural calculations produce mathematical parity across all 
 * three operational variants.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Vector Summation Paradigms")
class VectorSumTest {
    private static VectorSum sumCalculator;
    
    @BeforeAll
    static void setup() {
        sumCalculator = new VectorSum();
    }
    
    @Test
    @DisplayName("Iterative O(N): Accumulate element sequence via standard loop register")
    void shouldSumIteratively() {
        int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
        assertEquals(225, sumCalculator.sumIterative(v), "Iterative summation loop mismatch.");
    }  
    
    @Test
    @DisplayName("Subtraction O(N): Accumulate element sequence via linear recursion bounds")
    void shouldSumRecursivelyWithSubtraction() {
        int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
        assertEquals(225, sumCalculator.sumRecursiveSubtraction(v), "Subtraction-based recursive mismatch.");
    }
    
    @Test
    @DisplayName("Division O(N): Accumulate element sequence via symmetric binary split tree")
    void shouldSumRecursivelyWithDivision() {
        int[] v = {1, 30, 40, 13, 92, 34, 5, 2, 8};
        assertEquals(225, sumCalculator.sumRecursiveDivision(v), "Division-based binary recursive mismatch.");
    }
}