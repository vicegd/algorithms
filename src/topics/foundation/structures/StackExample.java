package topics.foundation.structures;

import java.util.Stack;

/**
 * <h1>Legacy Stack Demonstration</h1>
 * <p>
 * Demonstrates the structural operations of the legacy <code>java.util.Stack</code> class. 
 * While originally designed to represent a Last-In-First-Out (LIFO) collection, its architectural 
 * inheritance from <code>java.util.Vector</code> exposes it to index-based operations that 
 * directly violate strict stack contracts.
 * </p>
 * <p>
 * <strong>Architectural Note:</strong> In modern Java development, 
 * <code>java.util.ArrayDeque</code> is strongly preferred for LIFO stack semantics, 
 * as it avoids the synchronized threading overhead of <code>Vector</code> and enforces a 
 * much stricter operational boundary.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> 
 * <ul>
 * <li><code>O(1)</code> amortized for standard top-of-stack operations (<code>push</code>, <code>pop</code>, <code>peek</code>).</li>
 * <li><code>O(N)</code> for inherited vector operations like indexed insertions or arbitrary object removals, which require array shifting.</li>
 * </ul>
 * </li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> to maintain the underlying dynamically resizing array.</li>
 * </ul>
 *
 * @author vicegd
 */
public class StackExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new Stack<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Standard Vector Additions: Appends elements to the internal array (top of the stack)
        collection.add("A");
        collection.add("B");
        collection.add("C");
        collection.add("D");
        
        // Vector Inheritance Leak: Allows indexed insertion, shifting subsequent elements
        // This explicitly breaks the strict LIFO contract of a traditional stack
        collection.add(1, "A2");
        
        // Standard Stack Operations: Retrieves and removes the top elements ("D" then "C")
        collection.pop();
        collection.pop();
        
        System.out.println("Size after additions and pops: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Vector Inheritance Leak: Allows arbitrary value-based removals
        collection.remove("A2");
        
        // Vector Inheritance Leak: Allows arbitrary indexed removals
        // Note: Depending on the sequence of pops/removes above, this specific index 
        // may trigger an ArrayIndexOutOfBoundsException if the stack size falls below 4
        collection.remove(3);

        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}