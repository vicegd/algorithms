package topics.sorting.heapsort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import topics.sorting.SortingAlgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for Heapsort</h1>
 * <p>
 * Verifies both the structural heap-building logic and the subsequent 
 * extraction phase to ensure absolute array ordering.
 * </p>
 *
 * @author vicegd
 */
class HeapsortTest {
    private static SortingAlgorithm sorting;

    @BeforeAll
    static void setup() {
        sorting = new Heapsort();
    }

    /**
     * <p><strong>Scenario:</strong> Small array with mixed inversions.</p>
     */
    @Test
    void shouldSortSmallMixedSequence() {
        int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Heapsort failed to accurately build and extract the heap structure.");
    }

    /**
     * <p><strong>Scenario:</strong> Larger array with wider numerical variance.</p>
     */
    @Test
    void shouldSortLargerVariedSequence() {
        int[] elements = {159, 20, 170, 13, 28, 14, 23, 83, 3690, 98, 1561, 70, 65, 41, 42, 15};
        int[] expected = {13, 14, 15, 20, 23, 28, 41, 42, 65, 70, 83, 98, 159, 170, 1561, 3690};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Heapsort failed to sort the larger sequence.");
    }
}