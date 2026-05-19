package topics.divideconquer.mode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Mode Algorithms</h1>
 * <p>
 * Verifies both quadratic and linearithmic approaches accurately calculate 
 * the mode and its frequency.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Mode Calculation Paradigms")
class ModeTest {
    private static Mode elem;

    @BeforeAll
    static void setup() {
        elem = new Mode();
    }

    @Test
    @DisplayName("Naive O(N^2): Calculate the mode of a mixed array")
    void shouldCalculateModeNaive() {
        int[] v = {3, 7, 7, 1, 7, 3};
        int[] result = elem.calculateModeNaive(v);
        
        // Expected: Value = 7, Repetitions = 3
        int[] expected = {7, 3};
        assertArrayEquals(expected, result, "Naive algorithm failed to find the correct mode and frequency.");
    }  

    @Test
    @DisplayName("Sorting O(N log N): Calculate the mode of a mixed array")
    void shouldCalculateModeSorting() {
        int[] v = {3, 7, 7, 1, 7, 3};
        int[] result = elem.calculateModeSorting(v);
        
        // Expected: Value = 7, Repetitions = 3
        int[] expected = {7, 3};
        assertArrayEquals(expected, result, "Sorting algorithm failed to find the correct mode and frequency.");
    }
    
    @Test
    @DisplayName("Validation: Arrays must not be mutated by the calculation")
    void shouldNotMutateOriginalArray() {
        int[] original = {3, 7, 7, 1, 7, 3};
        int[] copy = original.clone();
        
        elem.calculateModeSorting(original);
        
        // Assert the original array remains completely unchanged
        for (int i = 0; i < original.length; i++) {
            assertEquals(copy[i], original[i], "The array was unexpectedly mutated during processing.");
        }
    }
}