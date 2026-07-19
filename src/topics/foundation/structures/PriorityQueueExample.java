package topics.foundation.structures;

import java.util.PriorityQueue;

/**
 * <h1>PriorityQueue Demonstration</h1>
 * <p>
 * Demonstrates fundamental operations on a <code>PriorityQueue</code>. 
 * This collection is backed by a priority heap data structure, which orders elements 
 * according to their natural mathematical ordering (or a specified comparator) 
 * rather than their chronological insertion sequence (FIFO).
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> 
 * <ul>
 * <li><code>O(log N)</code> for insertion (<code>add</code>) and extraction (<code>poll</code>) as the binary tree structurally rebalances.</li>
 * <li><code>O(N)</code> for arbitrary value-based removals (<code>remove(Object)</code>), as the structure requires a linear scan to locate the target node before repairing the heap.</li>
 * <li><code>O(1)</code> for inspecting the highest-priority element at the root (<code>peek</code>).</li>
 * </ul>
 * </li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> to maintain the dynamically resizing contiguous array representing the binary heap.</li>
 * </ul>
 *
 * @author vicegd
 */
public class PriorityQueueExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var queue = new PriorityQueue<String>();
        System.out.println("Initial size of the collection: " + queue.size());
        
        // Element Insertion: Elements are appended and 'bubble up' the heap based on alphabetical natural ordering
        queue.add("B");
        queue.add("A");
        queue.add("C");
        queue.add("D");
        queue.add("A");
        
        // Value-based Removal: Finds and removes the first occurrence of "A", then repairs the heap structure
        queue.remove("A");
        
        System.out.println("Size after operations: " + queue.size());
        System.out.print("Extracted Elements: ");
        
        // Priority Extraction: Continuously pops the root of the min-heap
        // Despite the insertion order, the output will reliably follow the natural ordering
        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
        }
        System.out.println();
    }
}