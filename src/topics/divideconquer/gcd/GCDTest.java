package topics.divideconquer.gcd;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Greatest Common Divisor</h1>
 * <p>
 * Contrasts the runtime execution bounds of naive scanning versus 
 * logarithmic recursion.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Greatest Common Divisor Paradigms")
class GCDTest {
    private static GCD gcdCalculator;

    @BeforeAll
    static void setup() {
        gcdCalculator = new GCD();
    }

    @Disabled("Demonstration: This test is conceptually correct but structurally too slow (O(N) ceiling).")
    @Test
    @DisplayName("Naive: Fails gracefully on maximum boundaries due to time constraints")
    void shouldCalculateNaiveGCDMaxBounds() {
        long result = gcdCalculator.naiveGCD(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Naive: Calculate standard large numbers")
    void shouldCalculateNaiveGCDStandard() {
        long result = gcdCalculator.naiveGCD(3918848, 1653264);
        assertEquals(61232, result);
    }

    @Test
    @DisplayName("Euclidean: Solves maximum boundaries instantly (O(log N))")
    void shouldCalculateEuclideanGCDMaxBounds() {
        // This takes milliseconds compared to the disabled Naive test
        long result = gcdCalculator.euclideanGCD(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Euclidean: Calculate standard large numbers")
    void shouldCalculateEuclideanGCDStandard() {
        long result = gcdCalculator.euclideanGCD(3918848, 1653264);
        assertEquals(61232, result);
    }

    @Test
    @DisplayName("Euclidean: Calculate small scale numbers")
    void shouldCalculateEuclideanGCDSmall() {
        long result = gcdCalculator.euclideanGCD(300, 120);
        assertEquals(60, result);
    }
    
    @Test
    @DisplayName("Edge Case: Handle negative inputs gracefully")
    void shouldHandleNegativeInputs() {
        long result = gcdCalculator.euclideanGCD(-300, 120);
        assertEquals(60, result, "GCD should be resolved using absolute mathematical values.");
    }
}