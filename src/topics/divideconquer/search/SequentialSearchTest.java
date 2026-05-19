package topics.divideconquer.search;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Sequential Search</h1>
 * <p>
 * Validates tracking accuracy for existing and missing elements across 
 * both iteration and stack-based recursion bounds.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Sequential Search Verification")
class SequentialSearchTest {
    private static SequentialSearch search;

    @BeforeAll
    static void setup() {
        search = new SequentialSearch();
    }

    @Test
    @DisplayName("Iterative: Should find existing element in an unsorted array")
    void shouldFindElementIteratively() {
        int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
        int target = 109;
        
        assertEquals(3, search.searchIterative(v, target), "Iterative search failed to find existing target.");
    }  
  
    @Test
    @DisplayName("Iterative: Should return MIN_VALUE when element does not exist")
    void shouldReturnMinWhenNotFoundIteratively() {
        int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
        int target = 99;
        
        assertEquals(Integer.MIN_VALUE, search.searchIterative(v, target), "Iterative search failed to return fallback on missing target.");
    }  
  
    @Test
    @DisplayName("Recursive: Should find existing element in an unsorted array")
    void shouldFindElementRecursively() {
        int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
        int target = 109;
        
        assertEquals(3, search.searchRecursive(v, target), "Recursive search failed to find existing target.");
    }  
  
    @Test
    @DisplayName("Recursive: Should return MIN_VALUE when element does not exist")
    void shouldReturnMinWhenNotFoundRecursively() {
        int[] v = {10, 3, 9, 109, 89, 1, 2, 6};
        int target = 99;
        
        assertEquals(Integer.MIN_VALUE, search.searchRecursive(v, target), "Recursive search failed to return fallback on missing target.");
    }  
}