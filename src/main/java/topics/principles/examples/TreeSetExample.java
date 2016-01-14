package topics.principles.examples;

import java.util.TreeSet;

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
