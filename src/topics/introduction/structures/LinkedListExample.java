package topics.introduction.structures;

import java.util.LinkedList;

/**
 * <h1>LinkedList Demonstration</h1>
 * <p>
 * Demonstrates fundamental structural operations on a <code>LinkedList</code>, 
 * which operates natively as a doubly-linked list. This architecture allows for 
 * highly efficient insertions and removals at both the extreme head and tail of 
 * the collection.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> 
 * <ul>
 * <li><code>O(1)</code> for adding or removing elements at either the head or the tail boundaries.</li>
 * <li><code>O(N)</code> for indexed access, interior insertions, or value-based removals, as the structure must be traversed sequentially to locate the target node.</li>
 * </ul>
 * </li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> required to store the elements, bearing a notable memory overhead per element to maintain the forward and backward positional reference pointers.</li>
 * </ul>
 *
 * @author vicegd
 */
public class LinkedListExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new LinkedList<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Standard Appending: Elements are attached to the tail
        collection.add("B");
        collection.add("C");
        collection.add("D");
        
        // Explicit Boundary Insertions: O(1) pointer reassignments
        collection.addLast("E");
        collection.addFirst("A");
        
        // Indexed Insertion: Triggers an O(N) linear traversal to locate the insertion point
        collection.add(1, "A2");
        
        System.out.println("Size after additions: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Value-based Removal: Triggers an O(N) linear search to match the object
        collection.remove("A2");
        
        // Indexed Removal: Triggers an O(N) linear traversal to locate the specified node
        collection.remove(3);
        
        // Explicit Boundary Removals: O(1) pointer pruning at the extremes
        collection.removeFirst();
        collection.removeLast();
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}