package topics.foundation.structures;

import java.util.TreeSet;

/**
 * <h1>TreeSet Demonstration</h1>
 * <p>
 * Demonstrates fundamental operations on a <code>TreeSet</code>. This collection 
 * is internally backed by a self-balancing binary search tree (specifically a 
 * <code>TreeMap</code> utilizing a Red-Black tree architecture). 
 * </p>
 * <p>
 * It enforces strict set properties (preventing duplicate entries) while guaranteeing 
 * that all elements are continually maintained in a sorted, ascending sequence 
 * according to their natural mathematical or alphabetical ordering.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(log N)</code> guaranteed for fundamental operations (<code>add</code>, <code>remove</code>, <code>contains</code>) due to the strict height-balancing algorithms of the Red-Black tree structure.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> required to store the elements. Each entry incurs notable memory overhead to maintain object references, left/right child pointers, and tree balancing metadata (node color).</li>
 * </ul>
 *
 * @author vicegd
 */
public class TreeSetExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new TreeSet<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Element Addition: Elements are inserted and the binary tree rebalances in O(log N) time.
        // Notice the out-of-order insertion.
        collection.add("D");
        collection.add("A");
        collection.add("B");
        collection.add("C");
        collection.add("E");
        
        System.out.println("Size after additions: " + collection.size());
        // Iteration traverses the tree in-order, reliably yielding: [A, B, C, D, E]
        System.out.println("Contents: " + collection);
        
        // Structural Removal: Locates the node in O(log N) time, removes it, and rebalances the tree
        collection.remove("A");
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Range-View Operation: Generates a sorted subset in O(log N) time.
        // Note that subSet(fromElement, toElement) is inclusive of the start bound and exclusive of the end bound.
        System.out.println("Subset from 'B' (inclusive) to 'E' (exclusive): " + collection.subSet("B", "E"));
    }
}