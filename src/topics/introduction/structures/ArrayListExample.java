package topics.introduction.structures;

import java.util.ArrayList;

/**
 * <h1>ArrayList Demonstration</h1>
 * <p>
 * Demonstrates fundamental structural operations on an <code>ArrayList</code>, 
 * including element addition, indexed insertion, and element removal by index or value.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> 
 * <ul>
 * <li><code>O(1)</code> amortized for appending an element to the tail.</li>
 * <li><code>O(N)</code> for indexed insertions or removals, as the underlying array elements must shift to preserve sequential memory slots.</li>
 * </ul>
 * </li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> to store the elements, dynamically allocating contiguous underlying memory arrays based on capacity demands.</li>
 * </ul>
 *
 * @author vicegd
 */
public class ArrayListExample {

    /**
     * Main execution entry point.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        var collection = new ArrayList<String>();
        System.out.println("Initial size of the collection: " + collection.size());
        
        // Populate the dynamic array with elements at the tail position
        collection.add("A");
        collection.add("B");
        collection.add("C");
        collection.add("D");
        
        // Indexed Insertion: Inserts an element shifting subsequent entries to the right
        collection.add(1, "A2");
        System.out.println("Size after additions: " + collection.size());
        System.out.println("Contents: " + collection);
        
        // Structural Removal: Deletes by value matching (triggers O(N) linear search and shift)
        collection.remove("A2");
        
        // Structural Removal: Deletes by specific index position (triggers O(N) left-shifting)
        collection.remove(3);
        
        System.out.println("Size after deletions: " + collection.size());
        System.out.println("Contents: " + collection);
    }
}