package topics.principles.examples;

import java.util.LinkedHashSet;

public class LinkedHashSetExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedHashSet<String> collection = new LinkedHashSet<String>();
		System.out.println("Initial size of the collection: " + collection.size());
		
		//Adding elements
		collection.add("B");
		collection.add("A");
		collection.add("C");
		collection.add("D");
		System.out.println("Size after additions: " + collection.size());
		System.out.println("Contents: " + collection);

		//Removing elements
		collection.remove("A");
		collection.remove(1);
		System.out.println("Size after deletions: " + collection.size());
		System.out.println("Contents: " + collection);
	}

}
