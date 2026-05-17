package topics.sorting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <h1>Sorting Utilities</h1>
 * <p>
 * A stateless collection of helper methods for array manipulation, 
 * element swapping, and educational execution tracing.
 * </p>
 *
 * @author vicegd
 */
public final class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);
  
    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Util() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated.");
    }

    /**
     * Logs the current state of the array during a specific iteration.
     *
     * @param iteration The current algorithmic loop iteration.
     * @param elements  The array being sorted.
     */
    public static void trace(int iteration, int[] elements) {
        if (log.isTraceEnabled()) {
            log.trace("Iteration: {} - {}", iteration, formatArray(elements));
        }
    }
  
    /**
     * Logs the specific state for the Shell Sort algorithm.
     *
     * @param k        Value of the gap sequence parameter.
     * @param pos      Value of the current position parameter.
     * @param elements The array being sorted.
     */
    public static void traceShellSort(int k, int pos, int[] elements) {
        if (log.isTraceEnabled()) {
            log.trace("K: {} - POS: {} - {}", k, pos, formatArray(elements));
        }
    }
  
    /**
     * Logs a custom contextual message alongside the array contents.
     *
     * @param message  The message to be logged.
     * @param elements The array being sorted.
     */
    public static void traceMessage(String message, int[] elements) {
        if (log.isTraceEnabled()) {
            log.trace("{} - {}", message, formatArray(elements));
        }
    }
  
    /**
     * Interchanges (swaps) two elements within an array in-place.
     *
     * @param elements The target array.
     * @param i        Position of the first element.
     * @param j        Position of the second element.
     */
    public static void swap(int[] elements, int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
  
    /**
     * Locates the position of the smallest element within a specific sub-array.
     *
     * @param elements     The target array.
     * @param firstElement The starting index for the search boundary.
     * @return The index of the smallest element.
     */
    public static int findPosMin(int[] elements, int firstElement) {
        int minPos = firstElement;
        for (int i = firstElement + 1; i < elements.length; i++) {
            if (elements[i] < elements[minPos]) {
                minPos = i;
            }
        }
        return minPos;
    }
  
    /**
     * Locates the position of the largest element within a specific sub-array.
     *
     * @param elements     The target array.
     * @param firstElement The starting index for the search boundary.
     * @return The index of the largest element.
     */
    public static int findPosMax(int[] elements, int firstElement) {
        int maxPos = firstElement;
        for (int i = firstElement + 1; i < elements.length; i++) {
            if (elements[i] > elements[maxPos]) {
                maxPos = i;
            }
        }
        return maxPos;
    }

    /**
     * Internal helper to format arrays into space-separated strings cleanly.
     */
    private static String formatArray(int[] elements) {
        return Arrays.stream(elements)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}