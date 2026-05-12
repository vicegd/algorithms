package topics.principles.examples;

import java.util.TreeSet;

/**
 * Demonstrates {@link java.util.TreeSet} with natural sorted ordering.
 *
 * Shows how TreeSet stores elements in ascending order and supports
 * range-view operations such as {@code subSet}.
 *
 * @author vicegd
 */
public class TreeSetExample {

	public static void main(String[] args) {
		TreeSet<String> collection = new TreeSet<String>();
		System.out.println("Initial size of the collection: " + collection.size());
		
		//Adding elements
		collection.add("D");
		collection.add("A");
		collection.add("B");
		collection.add("C");
		collection.add("E");
		System.out.println("Size after additions: " + collection.size());
		System.out.println("Contents: " + collection);
		
		//Removing elements
		collection.remove("A");
		System.out.println("Size after deletions: " + collection.size());
		System.out.println("Contents: " + collection);
		
		//Obtaining subset
		System.out.println(collection.subSet("B", "E"));
	}

}
