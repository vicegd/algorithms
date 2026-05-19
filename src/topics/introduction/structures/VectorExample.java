package topics.introduction.structures;

import java.util.Vector;

/**
 * <h1>Legacy Thread-Safe Vector Demonstration</h1>
 * <p>
 * Demonstrates the structural operations of a <code>Vector</code>, a legacy 
 * collection acting as a synchronized, dynamically resizing array. 
 * </p>
 * <p>
 * <strong>Architectural Note:</strong> Because all of its core mathematical operations 
 * are globally synchronized, <code>Vector</code> incurs a notable thread-locking 
 * performance overhead. Modern architectural standards heavily favor unsynchronized 
 * dynamic arrays for single-threaded contexts, or specialized concurrent structures 
 * for multi-threaded environments.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> 
 * <ul>
 * <li><code>O(1)</code> amortized for appending an element to the tail.</li>
 * <li><code>O(N)</code> for indexed insertions or value-based removals, as the underlying elements must be shifted in memory.</li>
 * </ul>
 * </li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> to maintain the contiguous backing array, which doubles in capacity upon saturation by default.</li>
 * </ul>
 *
 * @author vicegd
 */
public class VectorExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new Vector<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Standard Element Addition: Appends elements (acquires and releases thread locks per operation)
        collection.add("A");
        collection.add("B");
        collection.add("C");
        collection.add("D");
        
        // Indexed Insertion: Forces an O(N) right-shift of subsequent array elements
        collection.add(1, "A2");
        
        System.out.println("Size after additions: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Value-based Removal: Triggers an O(N) linear search followed by an O(N) left-shift
        collection.remove("A2");
        
        // Indexed Removal: Directly accesses the index in O(1) but requires an O(N) left-shift to close the gap
        collection.remove(3);
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}