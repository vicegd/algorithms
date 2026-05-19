package topics.divideconquer;

import topics.sorting.quicksort.Quicksort;

/**
 * <h1>Mode Calculation</h1>
 * <p>
 * Finds the statistical mode (the most frequently occurring element) in an array,
 * alongside its frequency. Demonstrates how preprocessing data (sorting) can 
 * optimize a quadratic problem into a linearithmic $O(N \log N)$ one.
 * </p>
 *
 * @author vicegd
 */
public class Mode {

    /**
     * <h2>1. Naive Iterative Approach</h2>
     * <p>
     * Checks every single element against every other element to count frequencies.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> $O(N^2)$ - Quadratic.</li>
     * <li><strong>Space Complexity:</strong> $O(1)$ - Constant.</li>
     * </ul>
     *
     * @param v Array of integers.
     * @return An integer array of size 2 where: 
     * index 0 = Mode Value, index 1 = Number of Repetitions.
     */
    public int[] calculateModeNaive(int[] v) {
        if (v == null || v.length == 0) {
            throw new IllegalArgumentException("Cannot calculate the mode of an empty array.");
        }

        int n = v.length;
        int modeValue = v[0];
        int maxRepetitions = 1;

        for (int i = 0; i < n; i++) {
            int currentCounter = 0;
            // Count occurrences of v[i] in the rest of the array
            for (int j = i; j < n; j++) {
                if (v[j] == v[i]) {
                    currentCounter++;
                }
            }
            // Update global mode if the current element appears more times
            if (currentCounter > maxRepetitions) {
                modeValue = v[i];
                maxRepetitions = currentCounter;
            }
        }
        
        return new int[]{modeValue, maxRepetitions};
    }    

    /**
     * <h2>2. Sorting Approach (Divide & Conquer)</h2>
     * <p>
     * Leverages Quicksort to group identical elements together. Once sorted, 
     * finding the mode only requires a single linear pass $O(N)$ counting 
     * contiguous identical values.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> $O(N \log N)$ - Bound by the Quicksort step.</li>
     * <li><strong>Space Complexity:</strong> $O(N)$ - To clone the array and prevent side-effects.</li>
     * </ul>
     *
     * @param v Array of integers.
     * @return An integer array of size 2 where: 
     * index 0 = Mode Value, index 1 = Number of Repetitions.
     */
    public int[] calculateModeSorting(int[] v) {
        if (v == null || v.length == 0) {
            throw new IllegalArgumentException("Cannot calculate the mode of an empty array.");
        }

        // Prevent side-effects: Do not mutate the original user array
        int[] copy = v.clone();
        
        Quicksort quicksort = new Quicksort();
        quicksort.sort(copy);
        
        int n = copy.length;
        int modeValue = copy[0];
        int maxRepetitions = 1;
        
        int currentCounter = 1;
        
        // Linear scan O(N) over the sorted array
        for (int i = 1; i < n; i++) {
            if (copy[i] == copy[i - 1]) {
                currentCounter++;
                if (currentCounter > maxRepetitions) {
                    modeValue = copy[i];
                    maxRepetitions = currentCounter;
                }
            } else {
                // Sequence broken, reset counter for the new number
                currentCounter = 1;
            }
        }
        
        return new int[]{modeValue, maxRepetitions};
    }    

    /*
     * Pedagogical Note:
     * public int[] calculateModeAdvancedDC(int[] v)
     * There is a highly advanced Divide & Conquer method for computing the mode
     * that slightly improves constant factors, explained in advanced algorithm
     * literature (e.g., section 3.11 of the base book). However, the Sorting 
     * Approach is the industry standard for simplicity and robust performance.
     */
}