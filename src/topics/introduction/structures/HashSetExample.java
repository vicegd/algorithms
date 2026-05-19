package topics.introduction.structures;

import java.util.HashSet;

/**
 * <h1>HashSet Demonstration</h1>
 * <p>
 * Demonstrates fundamental operations on a <code>HashSet</code>, including 
 * element addition, structural removal, and presence verification.
 * </p>
 * <p>
 * A <code>HashSet</code> is backed by a hash table structure. It enforces mathematical 
 * set properties by preventing duplicate entries and makes no mathematical or 
 * structural guarantees regarding the iteration order of its elements.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(1)</code> amortized average time for <code>add</code>, <code>remove</code>, and <code>contains</code> operations, assuming a uniform hash distribution function. In the theoretical worst-case scenario (severe hash collisions), this can degrade to <code>O(N)</code>.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> required to store the elements and the underlying bucket arrays.</li>
 * </ul>
 *
 * @author vicegd
 */
public class HashSetExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new HashSet<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Element Addition: Places elements into computed hash buckets
        collection.add("A");
        collection.add("B");
        collection.add("C");
        collection.add("D");
        
        // Set Property Demonstration: Attempting to add a duplicate is safely rejected
        boolean isDuplicateAdded = collection.add("B");
        System.out.println("Was duplicate 'B' added? " + isDuplicateAdded);
        
        System.out.println("Size after additions: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Element Verification: O(1) average lookup
        boolean containsC = collection.contains("C");
        System.out.println("Does the set contain 'C'? " + containsC);
        
        // Structural Removal: Evicts elements by matching their hash and equality
        collection.remove("A");
        collection.remove("C");
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}