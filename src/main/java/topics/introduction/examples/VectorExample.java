package topics.introduction.examples;

import java.util.Vector;

/**
 * Demonstrates the legacy thread-safe {@link java.util.Vector} collection.
 *
 * Vector is a synchronized dynamic array. In modern Java, {@link java.util.ArrayList}
 * (unsynchronized) or {@link java.util.concurrent.CopyOnWriteArrayList}
 * (concurrent) are generally preferred.
 *
 * @author vicegd
 */
public class VectorExample {

  public static void main(String[] args) {
    Vector<String> collection = new Vector<String>();
    System.out.println("Initial size of the collection: " + collection.size());
    
    //Adding elements
    collection.add("A");
    collection.add("B");
    collection.add("C");
    collection.add("D");
    collection.add(1, "A2");
    System.out.println("Size after additions: " + collection.size());
    System.out.println("Contents: " + collection);
    
    //Removing elements
    collection.remove("A2");
    collection.remove(3);
    
    System.out.println("Size after deletions: " + collection.size());
    System.out.println("Contents: " + collection);
  }

}
