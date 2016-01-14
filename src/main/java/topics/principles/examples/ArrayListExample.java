package topics.principles.examples;

import java.util.ArrayList;

public class ArrayListExample {

	public static void main(String[] args) {
		ArrayList<String> collection = new ArrayList<String>();
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
