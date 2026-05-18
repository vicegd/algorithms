package topics.dynamic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <h1>Validation Suite for Fibonacci Implementations</h1>
 * <p>
 * Ensures mathematical parity across all five algorithmic paradigms.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Fibonacci Paradigms Validation")
class FibonacciTest {
    private static Fibonacci fib;

    @BeforeAll
    static void setup() {
        fib = new Fibonacci();
    }

    @Test
    @DisplayName("Iterative O(N) Space O(1)")
    void testFibonacciIterative() {
        assertEquals(89L, fib.fibonacciIterative(11));
        assertEquals(12586269025L, fib.fibonacciIterative(50), "Handles large bounds effectively");
    }

    @Test
    @DisplayName("Dynamic Programming O(N) Space O(N)")
    void testFibonacciDP() {
        assertEquals(89L, fib.fibonacciDP(11));
    }

    @Test
    @DisplayName("Tail Recursive O(N)")
    void testFibonacciTailRecursive() {
        assertEquals(89L, fib.fibonacciTailRecursive(11));
    }

    @Test
    @DisplayName("Naive Recursive O(2^N) - Small bound only")
    void testFibonacciNaiveRecursive() {
        // We only test up to N=11 here. If we tested N=50, the test suite would hang indefinitely.
        assertEquals(89L, fib.fibonacciNaiveRecursive(11));
    }

    @Test
    @DisplayName("Logarithmic Fast Doubling O(log N)")
    void testFibonacciLogarithmic() {
        assertEquals(89L, fib.fibonacciLogarithmic(11));
        assertEquals(12586269025L, fib.fibonacciLogarithmic(50), "Handles large bounds near instantly");
    }
    
    @Test
    @DisplayName("Exception Guard: Negative Input")
    void testNegativeInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> fib.fibonacciIterative(-5));
    }
}