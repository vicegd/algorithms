package topics.introduction.structures;

import java.util.ArrayDeque;

/**
 * <h1>ArrayDeque Demonstration</h1>
 * <p>
 * Demonstrates the dual-purpose nature of the <code>ArrayDeque</code> data structure, 
 * operating efficiently as both a First-In-First-Out (FIFO) queue and a 
 * Last-In-First-Out (LIFO) stack.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> Amortized <code>O(1)</code> for insertions and removals at both the head and the tail.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> to store the elements, dynamically resizing as needed without the memory overhead of allocating individual node objects (unlike a linked list).</li>
 * </ul>
 *
 * @author vicegd
 */
public class ArrayDequeExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        demonstrateFifoQueue();
        demonstrateLifoStack();
    }

    /**
     * Demonstrates standard Queue (FIFO) behavior.
     * <p>
     * Elements are added to the tail of the deque and retrieved from the head.
     * </p>
     */
    private static void demonstrateFifoQueue() {
        var deque = new ArrayDeque<String>();
        
        // Enqueue: Append elements to the tail
        deque.add("B");
        deque.add("A");
        deque.add("C");
        deque.add("D");
        deque.add("A");
        
        System.out.print("FIFO Queue Output: ");
        
        // Dequeue: Retrieve and remove elements from the head
        while (!deque.isEmpty()) {
            System.out.print(deque.poll());
        }
        System.out.println();
    }

    /**
     * Demonstrates Stack (LIFO) behavior mixed with double-ended operations.
     * <p>
     * Elements are primarily pushed to the head and popped from the head, 
     * but the example also showcases appending to the tail mid-operation.
     * </p>
     */
    private static void demonstrateLifoStack() {
        var deque = new ArrayDeque<String>();
        
        // Stack operations: Push elements onto the head
        deque.push("B");
        deque.push("A");
        deque.push("C");
        deque.push("D");
        deque.push("A");
        
        // Double-ended operation: Append an element to the tail of the current state
        deque.add("Z");
        
        // Resume Stack operation: Push onto the head
        deque.push("J");
        
        System.out.print("LIFO Stack Output: ");
        
        // Pop: Retrieve and remove elements from the head
        while (!deque.isEmpty()) {
            System.out.print(deque.pop());
        }
        System.out.println();
    }
}