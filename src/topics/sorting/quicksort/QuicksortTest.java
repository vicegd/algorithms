package topics.sorting.quicksort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import topics.sorting.utils.SortingAlgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * <h1>Validation Suite for Quicksort</h1>
 * <p>
 * Verifies the recursive partitioning and pivot placement logic of 
 * the Quicksort algorithm using JUnit 5.
 * </p>
 *
 * @author vicegd
 */
class QuicksortTest {
    private static SortingAlgorithm sorting;

    @BeforeAll
    static void setup() {
        sorting = new Quicksort();
    }

    /**
     * <p><strong>Scenario:</strong> Small array with mixed inversions.</p>
     */
    @Test
    void shouldSortSmallMixedSequence() {
        int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        sorting.sort(elements);

        assertArrayEquals(expected, elements, "Quicksort failed to partition and sort the sequence.");
    }

    /**
     * <p><strong>Scenario:</strong> Larger array with wider numerical variance.</p>
     */
    @Test
    void shouldSortLargerVariedSequence() {
        int[] elements = {159, 20, 170, 13, 28, 14, 23, 83, 3690, 98, 1561, 70, 65, 41, 42, 15};
        int[] expected = {13, 14, 15, 20, 23, 28, 41, 42, 65, 70, 83, 98, 159, 170, 1561, 3690};

        sorting.sort(elements);

        assertArrayEquals(expected, elements, "Quicksort failed to partition and sort the larger sequence.");
    }

    /**
     * <p><strong>Scenario:</strong> Null, empty, and single-element arrays.</p>
     */
    @Test
    void shouldHandleBoundaryInputsSafely() {
        assertDoesNotThrow(() -> sorting.sort(null));

        int[] empty = {};
        sorting.sort(empty);
        assertArrayEquals(new int[] {}, empty);

        int[] single = {42};
        sorting.sort(single);
        assertArrayEquals(new int[] {42}, single);
    }

    /**
     * <p><strong>Scenario:</strong> Already sorted, reverse-sorted, and duplicate-heavy arrays.</p>
     */
    @Test
    void shouldSortSortedReversedAndDuplicateSequences() {
        int[] sorted = {1, 2, 3, 4, 5};
        sorting.sort(sorted);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, sorted);

        int[] reversed = {5, 4, 3, 2, 1};
        sorting.sort(reversed);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, reversed);

        int[] duplicates = {3, 1, 2, 3, 1, 2, 3};
        sorting.sort(duplicates);
        assertArrayEquals(new int[] {1, 1, 2, 2, 3, 3, 3}, duplicates);
    }
}