package topics.sorting.radix;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import topics.sorting.utils.SortingAlgorithm;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * <h1>Validation Suite for Radix Sort</h1>
 * <p>
 * Verifies the digit extraction logic and queue-based bucket distribution 
 * mechanics to ensure stable integer sorting.
 * </p>
 *
 * @author vicegd
 */
class RadixTest {
    private static SortingAlgorithm sorting;

    @BeforeAll
    static void setup() {
        sorting = new Radix();
    }

    /**
     * <p><strong>Scenario:</strong> Small array with single-digit integers.</p>
     */
    @Test
    void shouldSortSingleDigitSequence() {
        int[] elements = {4, 5, 6, 1, 3, 2, 7, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Radix failed to distribute and sort single-digit sequences.");
    }

    /**
     * <p><strong>Scenario:</strong> Larger array containing multi-digit integers spanning different magnitudes (Units to Thousands).</p>
     */
    @Test
    void shouldSortMultiDigitSequence() {
        int[] elements = {159, 20, 170, 13, 28, 14, 23, 83, 3690, 98, 1561, 70, 65, 41, 42, 15};
        int[] expected = {13, 14, 15, 20, 23, 28, 41, 42, 65, 70, 83, 98, 159, 170, 1561, 3690};

        sorting.sort(elements, true);

        assertArrayEquals(expected, elements, "Radix failed to safely handle integers of varying magnitudes.");
    }
}