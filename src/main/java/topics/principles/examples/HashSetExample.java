package topics.principles.examples;

import java.util.HashSet;

public class HashSetExample {

	public static void main(String[] args) {
		HashSet<String> collection = new HashSet<String>();
		System.out.println("Initial size of the collection: " + collection.size());
		
		//Adding elements
		collection.add("A");
		collection.add("B");
		collection.add("C");
		collection.add("D");
		System.out.println("Size after additions: " + collection.size());
		System.out.println("Contents: " + collection);
		
		//Removing elements
		collection.remove("A");
		collection.remove(3);
		System.out.println("Size after deletions: " + collection.size());
		System.out.println("Contents: " + collection);
	}

}
