package topics.introduction.examples;

import java.util.LinkedHashSet;

/**
 * <h1>LinkedHashSet Demonstration</h1>
 * <p>
 * Demonstrates fundamental operations on a <code>LinkedHashSet</code>.
 * Unlike a standard <code>HashSet</code>, this collection maintains a doubly-linked 
 * list running through all of its entries. This architectural addition preserves 
 * the exact sequence in which elements were inserted, guaranteeing a predictable 
 * iteration order.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(1)</code> amortized for <code>add</code>, 
 * <code>remove</code>, and <code>contains</code> operations. Iteration is linear <code>O(N)</code> 
 * based strictly on the size of the set, avoiding the capacity-based iteration penalty 
 * of a standard <code>HashSet</code>.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> required to store the elements, 
 * the underlying hash buckets, and the additional doubly-linked list pointers.</li>
 * </ul>
 *
 * @author vicegd
 */
public class LinkedHashSetExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new LinkedHashSet<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Element Addition: Elements are hashed for O(1) access, but also linked to preserve sequence
        collection.add("B");
        collection.add("A");
        collection.add("C");
        collection.add("D");
        
        System.out.println("Size after additions: " + collection.size());
        // Output will reliably be: [B, A, C, D]
        System.out.println("Contents: " + collection);

        // Structural Removal: Evicts the element, resolves hash collisions, and bridges the linked list pointers
        collection.remove("A");
        collection.remove("D");
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}