package topics.divideconquer.median;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Median Calculation</h1>
 * <p>
 * Ensures both sorting and Quickselect algorithms accurately locate the median
 * without inadvertently altering the state of the input arrays.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Median Selection Paradigms")
class MedianTest {
    private static Median elem;

    @BeforeAll
    static void setup() {
        elem = new Median();
    }

    @Test
    @DisplayName("Sorting O(N log N): Find median in even-length array")
    void shouldFindMedianEvenSorting() {
        int[] v = {5, 8, 3, 1, 18, 12, 10, 7};
        // Sorted: 1, 3, 5, 7, 8, 10, 12, 18. Length 8. Target index 4 -> Value 8.
        assertEquals(8, elem.medianBySorting(v));
    }  

    @Test
    @DisplayName("Quickselect O(N): Find median in even-length array")
    void shouldFindMedianEvenQuickselect() {
        int[] v = {5, 8, 3, 1, 18, 12, 10, 7};
        assertEquals(8, elem.medianQuickselect(v));
    }
    
    @Test
    @DisplayName("Sorting O(N log N): Find median in odd-length array")
    void shouldFindMedianOddSorting() {
        int[] v = {5, 8, 3, 1, 18, 12, 10};
        // Sorted: 1, 3, 5, 8, 10, 12, 18. Length 7. Target index 3 -> Value 8.
        assertEquals(8, elem.medianBySorting(v));
    }  

    @Test
    @DisplayName("Quickselect O(N): Find median in odd-length array")
    void shouldFindMedianOddQuickselect() {
        int[] v = {5, 8, 3, 1, 18, 12, 10};
        assertEquals(8, elem.medianQuickselect(v));
    }
    
    @Test
    @DisplayName("Validation: Arrays must not be mutated by the calculation")
    void shouldNotMutateOriginalArray() {
        int[] original = {5, 8, 3, 1, 18};
        int[] copy = original.clone();
        
        elem.medianQuickselect(original);
        elem.medianBySorting(original);
        
        // Assert the original array remains completely unchanged
        for (int i = 0; i < original.length; i++) {
            assertEquals(copy[i], original[i], "The array was unexpectedly mutated during processing.");
        }
    }
}