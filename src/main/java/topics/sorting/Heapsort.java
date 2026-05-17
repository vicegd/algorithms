package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.SortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * <h1>Heapsort Algorithm</h1>
 * <p>
 * A highly efficient comparison-based sorting algorithm that utilizes a binary 
 * heap data structure. It divides its execution into two distinct phases:
 * </p>
 * <h2>Algorithm Steps</h2>
 * <ol>
 * <li><strong>Build Heap:</strong> Rearrange the array elements into a valid Max-Heap structure in <code>O(N)</code> time.</li>
 * <li><strong>Extract Max:</strong> Repeatedly swap the root of the heap (the maximum value) with the last element of the unsorted segment, reduce the heap size by 1, and restore the heap property via a "down-heap" (sift-down) operation.</li>
 * </ol>
 *
 * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N log N)</code> strictly in all cases (Best, Average, Worst).</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> - The binary tree is mapped implicitly onto the array, requiring no external memory.</li>
 * <li><strong>Stability:</strong> No - Operations on the heap tree structure routinely leapfrog equal elements, destroying relative order.</li>
 * </ul>
 *
 * @author vicegd
 * @see topics.sorting.utils.SortingAlgorithm
 */
public class Heapsort implements SortingAlgorithm {
    private static final Logger log = LoggerFactory.getLogger(Heapsort.class);

    @Override
    public void sort(int[] elements) {
        // Delegate to the traceable method to avoid duplicating the algorithmic logic
        sort(elements, false);
    }

    @Override
    public void sort(int[] elements, boolean trace) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        if (trace && log.isDebugEnabled()) {
            log.debug("Initiating Heapsort execution");
        }

        int n = elements.length;

        // Phase 1: Build the initial Max-Heap from the bottom up
        buildHeap(elements, n, trace);

        // Phase 2: Extract the maximum element and place it at the end of the array
        while (n > 1) {
            n--;
            
            // Move the current maximum (root at index 0) to the end of the unsorted boundary
            Util.swap(elements, 0, n);
            
            if (trace) {
                Util.traceMessage("Extracted Max to index " + n, elements);
            }
            
            // Restore the Max-Heap property for the remaining unsorted elements
            downHeap(elements, 0, n);
            
            if (trace) {
                Util.traceMessage("Downheap restored Max-Heap", elements);
            }
        }
    }

    /**
     * Rearranges the input array to satisfy the Max-Heap property.
     * Operates in O(N) time complexity.
     */
    private void buildHeap(int[] elements, int heapSize, boolean trace) {
        // Start from the last non-leaf node and sift down to the root
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            downHeap(elements, i, heapSize);
            
            if (trace) {
                Util.traceMessage("BUILDING HEAP - Downheap from index " + i, elements);
            }
        }
    }

    /**
     * Sifts a node down the binary heap tree until it satisfies the Max-Heap property.
     * Operates in O(log N) time complexity.
     *
     * @param elements  The array representing the implicit binary heap.
     * @param parentIdx The index of the node to be sifted down.
     * @param heapSize  The current boundary defining the unsorted heap portion.
     */
    private void downHeap(int[] elements, int parentIdx, int heapSize) {
        int largestChild = 2 * parentIdx + 1; // Left child index

        while (largestChild < heapSize) {
            int rightChild = largestChild + 1;
            
            // Determine which child holds the strictly greater value
            if (rightChild < heapSize && elements[rightChild] > elements[largestChild]) {
                largestChild = rightChild;
            }

            // If the parent is already greater than or equal to the largest child, the heap is valid
            if (elements[parentIdx] >= elements[largestChild]) {
                break;
            }

            // Otherwise, swap the parent with the largest child and continue sifting down
            Util.swap(elements, parentIdx, largestChild);
            parentIdx = largestChild;
            largestChild = 2 * parentIdx + 1;
        }
    }
}