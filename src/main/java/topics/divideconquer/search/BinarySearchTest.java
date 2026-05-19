package topics.divideconquer.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Binary Search</h1>
 * <p>
 * Ensures correct target location across both Iterative and Recursive 
 * paradigms. Requires a strictly sorted array to function correctly.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Binary Search Optimization")
class BinarySearchTest {
    private static BinarySearch search;

    @BeforeAll
    static void setup() {
        search = new BinarySearch();
    }

    @Test
    @DisplayName("Iterative: Should find existing element in sorted array")
    void shouldFindElementIteratively() {
        // CRITICAL FIX: The array must be explicitly sorted for Binary Search to work mathematically.
        int[] v = {1, 2, 3, 6, 9, 10, 89, 109};
        int target = 109;
        
        // Target '109' is now at index 7.
        assertEquals(7, search.binarySearchIterative(v, target), "Failed to find existing target iteratively.");
    }

    @Test
    @DisplayName("Iterative: Should return MIN_VALUE when element does not exist")
    void shouldReturnMinWhenNotFoundIteratively() {
        int[] v = {1, 2, 3, 6, 9, 10, 89, 109};
        int target = 99;
        
        assertEquals(Integer.MIN_VALUE, search.binarySearchIterative(v, target), "Failed to handle missing target iteratively.");
    }

    @Test
    @DisplayName("Recursive: Should find existing element in sorted array")
    void shouldFindElementRecursively() {
        int[] v = {1, 2, 3, 6, 9, 10, 89, 109};
        int target = 109;
        
        assertEquals(7, search.binarySearchRecursive(v, target), "Failed to find existing target recursively.");
    }

    @Test
    @DisplayName("Recursive: Should return MIN_VALUE when element does not exist")
    void shouldReturnMinWhenNotFoundRecursively() {
        int[] v = {1, 2, 3, 6, 9, 10, 89, 109};
        int target = 99;
        
        assertEquals(Integer.MIN_VALUE, search.binarySearchRecursive(v, target), "Failed to handle missing target recursively.");
    }
}