package topics.principles.examples;

import java.util.Stack;

/**
 * Demonstrates the legacy {@link java.util.Stack} class (LIFO) with
 * {@code push} and {@code pop} operations.
 *
 * Note: In modern Java, {@link java.util.ArrayDeque} is preferred over
 * {@code Stack} for stack semantics.
 *
 * @author vicegd
 */
public class StackExample {

  public static void main(String[] args) {
    Stack<String> collection = new Stack<String>();
    System.out.println("Initial size of the collection: " + collection.size());
    
    //Adding elements
    collection.add("A");
    collection.add("B");
    collection.add("C");
    collection.add("D");
    collection.add(1, "A2");
    collection.pop();
    collection.pop();
    System.out.println("Size after additions: " + collection.size());
    System.out.println("Contents: " + collection);
    
    //Removing elements
    collection.remove("A2");
    collection.remove(3);

    System.out.println("Size after deletions: " + collection.size());
    System.out.println("Contents: " + collection);
  }

}
