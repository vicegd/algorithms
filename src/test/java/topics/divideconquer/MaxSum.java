package topics.divideconquer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Maximum Subarray Sum</h1>
 * <p>
 * Ensures all three algorithmic paradigms produce mathematically identical 
 * results, including handling edge cases like all-negative arrays.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Maximum Subarray Sum Paradigms")
class MaxSumTest {
    private static MaxSum max;

    @BeforeAll
    static void setup() {
        max = new MaxSum();
    }

    @Test
    @DisplayName("Cubic O(N^3): Find max sum in mixed array")
    void shouldFindMaxSumCubic() {
        int[] v = {5, -4, 3, 2, 5, -1};
        // Max subarray is [5, -4, 3, 2, 5] = 11
        assertEquals(11, max.maxSubarrayCubic(v));
    }  

    @Test
    @DisplayName("Quadratic O(N^2): Find max sum in mixed array")
    void shouldFindMaxSumQuadratic() {
        int[] v = {5, -4, 3, 2, 5, -1};
        assertEquals(11, max.maxSubarrayQuadratic(v));
    }

    @Test
    @DisplayName("Divide & Conquer O(N log N): Find max sum in mixed array")
    void shouldFindMaxSumDivideAndConquer() {
        int[] v = {5, -4, 3, 2, 5, -1};
        assertEquals(11, max.maxSubarrayDivideAndConquer(v));
    }
    
    @Test
    @DisplayName("Edge Case: All algorithms should handle all-negative arrays correctly")
    void shouldHandleAllNegativeArrays() {
        int[] v = {-5, -2, -9, -12};
        // The maximum non-empty subarray is just [-2]
        int expected = -2;
        
        assertEquals(expected, max.maxSubarrayCubic(v), "Cubic failed on negatives");
        assertEquals(expected, max.maxSubarrayQuadratic(v), "Quadratic failed on negatives");
        assertEquals(expected, max.maxSubarrayDivideAndConquer(v), "D&C failed on negatives");
    }
}