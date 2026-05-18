package topics.divideconquer.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Divide & Conquer Utilities</h1>
 * <p>
 * Ensures the Lomuto partition logic rigorously enforces the mathematical 
 * boundaries (Left <= Pivot <= Right) regardless of the initial array entropy.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Divide & Conquer Utility - Partition Logic")
class UtilTest {

    @Test
    @DisplayName("Should correctly partition a mixed array around the middle pivot")
    void shouldPartitionMixedArray() {
        int[] elements = {8, 4, 7, 3, 5, 2, 6, 9};
        
        // Mid index is (0+7)/2 = 3. The value there is '3'.
        int pivotIndex = Util.partition(elements, 0, elements.length - 1);
        int pivotValue = elements[pivotIndex];

        // 1. Verify all elements to the LEFT are <= the pivot
        for (int i = 0; i < pivotIndex; i++) {
            assertTrue(elements[i] <= pivotValue, "Element on the left is greater than the pivot.");
        }

        // 2. Verify all elements to the RIGHT are >= the pivot
        for (int i = pivotIndex + 1; i < elements.length; i++) {
            assertTrue(elements[i] >= pivotValue, "Element on the right is less than the pivot.");
        }
    }

    @Test
    @DisplayName("Should handle an already sorted array without breaking")
    void shouldPartitionSortedArray() {
        int[] elements = {1, 2, 3, 4, 5, 6};
        
        // Mid index is (0+5)/2 = 2. Value is '3'. 
        // After partitioning, '3' should naturally land back at index 2.
        int pivotIndex = Util.partition(elements, 0, elements.length - 1);
        
        assertEquals(2, pivotIndex, "Pivot should remain at the correct relative sorted position.");
    }
    
    @Test
    @DisplayName("Should correctly swap two elements")
    void shouldSwapElements() {
        int[] elements = {10, 20};
        Util.swap(elements, 0, 1);
        
        assertEquals(20, elements[0]);
        assertEquals(10, elements[1]);
    }
}