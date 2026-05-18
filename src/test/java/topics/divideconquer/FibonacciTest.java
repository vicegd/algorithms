package topics.divideconquer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <h1>Validation Suite for Divide & Conquer Fibonacci</h1>
 *
 * @author vicegd
 */
@DisplayName("Fibonacci Paradigms - Divide & Conquer")
class FibonacciTest {
    private static Fibonacci fib;

    @BeforeAll
    static void setup() {
        fib = new Fibonacci();
    }

    @Test
    @DisplayName("Iterative O(N)")
    void shouldCalculateIterative() {
        assertEquals(89L, fib.fibonacciIterative(11));
    }

    @Test
    @DisplayName("Array-Based O(N)")
    void shouldCalculateArrayBased() {
        assertEquals(89L, fib.fibonacciArray(11));
    }

    @Test
    @DisplayName("Tail Recursive D&C O(N)")
    void shouldCalculateTailRecursive() {
        assertEquals(89L, fib.fibonacciTailRecursive(11));
    }

    @Test
    @DisplayName("Naive Recursive D&C O(2^N)")
    void shouldCalculateNaiveRecursive() {
        assertEquals(89L, fib.fibonacciNaiveRecursive(11));
    }

    @Test
    @DisplayName("Logarithmic D&C O(log N)")
    void shouldCalculateLogarithmic() {
        assertEquals(89L, fib.fibonacciLogarithmic(11));
        assertEquals(12586269025L, fib.fibonacciLogarithmic(50), "Logarithmic approach handles large bounds instantly.");
    }
    
    @Test
    @DisplayName("Exception Guard: Negative Input")
    void shouldThrowExceptionOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> fib.fibonacciIterative(-5));
    }
}