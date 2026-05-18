package topics.divideconquer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <h1>Validation Suite for Factorial</h1>
 * <p>
 * Ensures mathematical parity across iterative and recursive approaches, 
 * validates base cases, and confirms execution stability up to the 64-bit 
 * long ceiling (20!).
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Factorial Paradigms")
class FactorialTest {
    private static Factorial factorial;

    @BeforeAll
    static void setup() {
        factorial = new Factorial();
    }

    @Test
    @DisplayName("Iterative: Calculate standard factorial (6!)")
    void shouldCalculateIterativeFactorial() {
        long result = factorial.factorialIterative(6);
        assertEquals(720L, result, "Iterative calculation failed for 6!");
    }

    @Test
    @DisplayName("Iterative: Handle mathematical base case (0!)")
    void shouldHandleIterativeBaseCase() {
        assertEquals(1L, factorial.factorialIterative(0), "By definition, 0! must equal 1.");
    }

    @Test
    @DisplayName("Recursive: Calculate standard factorial (6!)")
    void shouldCalculateRecursiveFactorial() {
        long result = factorial.factorialRecursive(6);
        assertEquals(720L, result, "Recursive calculation failed for 6!");
    }

    @Test
    @DisplayName("Recursive: Handle mathematical base case (0!)")
    void shouldHandleRecursiveBaseCase() {
        assertEquals(1L, factorial.factorialRecursive(0), "By definition, 0! must equal 1.");
    }

    @Test
    @DisplayName("Both: Safely calculate maximum 64-bit bounds (20!)")
    void shouldHandleLongCeiling() {
        long expected = 2432902008176640000L; // 20!
        assertEquals(expected, factorial.factorialIterative(20));
        assertEquals(expected, factorial.factorialRecursive(20));
    }

    @Test
    @DisplayName("Both: Reject negative inputs safely")
    void shouldRejectNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> factorial.factorialIterative(-5));
        assertThrows(IllegalArgumentException.class, () -> factorial.factorialRecursive(-5));
    }
}