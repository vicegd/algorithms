package topics.divideconquer.majority;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Majority Element Algorithms</h1>
 * <p>
 * Ensures all three paradigms correctly identify whether a majority 
 * element (> N/2 occurrences) exists within an array.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Majority Element - Algorithmic Paradigms")
class MajorityElementTest {
    private static MajorityElement elem;

    @BeforeAll
    static void setup() {
        elem = new MajorityElement();
    }

    @Test
    @DisplayName("Iterative O(N^2): Should return false when no majority exists")
    void shouldFindNoMajorityIterative() {
        int[] v = {3, 8, 3, 1, 7, 3}; // '3' appears 3 times. Majority threshold is 4.
        assertFalse(elem.hasMajorityNaive(v), "Naive algorithm incorrectly identified a majority.");
    }

    @Test
    @DisplayName("Iterative O(N^2): Should return true when majority exists")
    void shouldFindMajorityIterative() {
        int[] v = {3, 8, 3, 1, 3, 3}; // '3' appears 4 times. Majority threshold is 4.
        assertTrue(elem.hasMajorityNaive(v), "Naive algorithm failed to identify the majority.");
    }

    @Test
    @DisplayName("Sorting O(N log N): Should return false when no majority exists")
    void shouldFindNoMajoritySorting() {
        int[] v = {3, 8, 3, 1, 7, 3};
        assertFalse(elem.hasMajoritySorting(v), "Sorting algorithm incorrectly identified a majority.");
    }

    @Test
    @DisplayName("Sorting O(N log N): Should return true when majority exists")
    void shouldFindMajoritySorting() {
        int[] v = {3, 8, 3, 1, 3, 3};
        assertTrue(elem.hasMajoritySorting(v), "Sorting algorithm failed to identify the majority.");
    }

    @Test
    @DisplayName("Divide & Conquer O(N): Should return false when no majority exists")
    void shouldFindNoMajorityDivideAndConquer() {
        int[] v = {3, 8, 3, 1, 7, 3};
        assertFalse(elem.hasMajorityDivideAndConquer(v), "D&C algorithm incorrectly identified a majority.");
    }

    @Test
    @DisplayName("Divide & Conquer O(N): Should return true when majority exists")
    void shouldFindMajorityDivideAndConquer() {
        int[] v = {3, 8, 3, 1, 3, 3};
        assertTrue(elem.hasMajorityDivideAndConquer(v), "D&C algorithm failed to identify the majority.");
    }
}