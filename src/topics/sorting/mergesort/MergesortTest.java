package topics.sorting.mergesort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import topics.sorting.utils.SortingAlgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Mergesort</h1>
 * <p>
 * Verifies the recursive partitioning and O(N) linear merging capabilities 
 * of the algorithm using JUnit 5.
 * </p>
 *
 * @author vicegd
 */
class MergesortTest {
    private static SortingAlgorithm sorting;

    @BeforeAll
    static void setup() {
        sorting = new Mergesort();
    }

    /**
     * <p><strong>Scenario:</strong> Small array with mixed inversions.</p>
     */
    @Test
    void shouldSortSmallMixedSequence() {
        int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        sorting.sort(elements);

        assertArrayEquals(expected, elements, "Mergesort failed to correctly divide and merge the small sequence.");
    }

    /**
     * <p><strong>Scenario:</strong> A large array consisting of exactly 1,000 elements strictly in reverse order.</p>
     * <p><strong>Observation:</strong> Evaluates the algorithm's performance when encountering the theoretical worst-case structural alignment for many algorithms.</p>
     */
    @Test
    void shouldSortLargeReversedSequence() {
        int size = 1000;  
        int[] elements = new int[size];
        
        // Initialize array in descending order (999 down to 0)
        for (int i = 0; i < size; i++) {
            elements[i] = size - i - 1; 
        }
        
        // Trace is set to false here to prevent generating 1000 lines of console logs
        sorting.sort(elements);
        
        // Validate strict ascending order mathematically
        for (int i = 0; i < size; i++) {
            assertEquals(i, elements[i], "Element at index " + i + " is out of order after Mergesort.");
        }
    }
}