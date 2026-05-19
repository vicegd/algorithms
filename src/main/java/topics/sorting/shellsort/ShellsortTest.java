package topics.sorting.shellsort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import topics.sorting.SortingAlgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for Shellsort</h1>
 * <p>
 * Verifies the mathematical integrity of the gapped shifting logic and 
 * ensures the algorithm correctly reduces to a standard insertion sort 
 * on the final pass (gap = 1).
 * </p>
 *
 * @author vicegd
 */
class ShellsortTest {
    private static SortingAlgorithm sorting;

    @BeforeAll
    static void setup() {
        sorting = new Shellsort();
    }

    /**
     * <p><strong>Scenario:</strong> Small array with mixed inversions.</p>
     */
    @Test
    void shouldSortSmallMixedSequence() {
        int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Shellsort failed to order the small sequence.");
    }

    /**
     * <p><strong>Scenario:</strong> Larger array containing multi-digit integers spanning different magnitudes.</p>
     */
    @Test
    void shouldSortLargerVariedSequence() {
        int[] elements = {159, 20, 170, 13, 28, 14, 23, 83, 3690, 98, 1561, 70, 65, 41, 42, 15};
        int[] expected = {13, 14, 15, 20, 23, 28, 41, 42, 65, 70, 83, 98, 159, 170, 1561, 3690};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Shellsort failed to order the larger array sequence.");
    }
}