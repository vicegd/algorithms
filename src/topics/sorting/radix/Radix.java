package topics.sorting.radix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <h1>Radix Sort (LSD - Least Significant Digit)</h1>
 * <p>
 * A non-comparative integer sorting algorithm that groups keys by individual 
 * digits that share the same significant position and value. This implementation 
 * processes digits from the Least Significant Digit (units) to the Most 
 * Significant Digit using 10 Base-10 Queues (Buckets).
 * </p>
 *
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li>Identify the maximum value in the array to determine the max digit count (K).</li>
 * <li>For each digit position (Units, Tens, Hundreds...), distribute the elements into 10 buckets based on their digit at that position.</li>
 * <li>Re-collect the elements from the buckets back into the original array, preserving their stable order.</li>
 * <li>Repeat for the next significant digit.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(K &times; N)</code> strictly in all cases, where <code>N</code> is the number of elements and <code>K</code> is the number of digits in the largest number. If <code>K</code> is small, it behaves almost linearly.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N + Base)</code> - Requires additional memory to store the elements inside the buckets (Queues).</li>
 * <li><strong>Stability:</strong> Yes - It is strictly required to be stable for the digit-by-digit sorting logic to work correctly.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.SortingAlgorithm
 */
public class Radix implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(Radix.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to prevent logic duplication
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating LSD Radix Sort execution");
        }

        // Initialize empty buckets locally to ensure the algorithm is strictly stateless and thread-safe
        @SuppressWarnings("unchecked")
        Queue<Integer>[] buckets = new ArrayDeque[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        int maxNumberOfDigits = calculateMaxDigitCount(elements);
        int divisor = 1; // Used to extract positions (1=Units, 10=Tens, 100=Hundreds, etc.)

        // Iterate exactly K times (where K is the number of digits of the largest number)
        for (int pass = 0; pass < maxNumberOfDigits; pass++) {
            
            // Phase 1: Distribute elements into buckets based on the current active digit
            for (int element : elements) {
                int digit = getDigitAtPlace(element, divisor);
                buckets[digit].add(element);
            }
            
            // Phase 2: Re-collect elements sequentially from the buckets
            int index = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    elements[index++] = bucket.remove(); // Removes and retrieves the head
                }
            }
            
            if (trace) {
                // Formatting mathematically (pass 0 -> 1s, pass 1 -> 10s, pass 2 -> 100s)
                Util.traceMessage(String.format("Distributed by %ds place", divisor), elements);
            }
            
            divisor *= 10;
        }
    }

    /**
     * Finds the maximum value in the array and mathematically computes its digit count.
     * Operates in O(N) time.
     */
    private int calculateMaxDigitCount(int[] elements) {
        int maxPos = Util.findPosMax(elements, 0);
        int maxValue = elements[maxPos];
        
        // Mathematical optimization: log10 provides the digit count much faster than String.valueOf()
        if (maxValue == 0) return 1;
        return (int) Math.log10(Math.abs(maxValue)) + 1;
    }

    /**
     * Extracts a specific digit from an integer using modular arithmetic.
     *
     * @param number  The target integer.
     * @param divisor The place multiplier (1, 10, 100, etc.).
     * @return The isolated digit (0-9).
     */
    private int getDigitAtPlace(int number, int divisor) {
        return (number / divisor) % 10;
    }
}