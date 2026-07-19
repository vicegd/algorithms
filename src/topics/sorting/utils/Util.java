package topics.sorting.utils;

/**
 * <h1>Sorting Utilities</h1>
 * <p>
 * A stateless collection of helper methods for array manipulation 
 * and element swapping.
 * </p>
 *
 * @author vicegd
 */
public final class Util {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Util() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated.");
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
     * @return The index of the smallest element.
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

}