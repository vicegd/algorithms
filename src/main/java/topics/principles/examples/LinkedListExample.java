package topics.principles.examples;

import java.util.LinkedList;

public class LinkedListExample {

	public static void main(String[] args) {
		LinkedList<String> collection = new LinkedList<String>();
		System.out.println("Initial size of the collection: " + collection.size());
		
		//Adding elements
		collection.add("B");
		collection.add("C");
		collection.add("D");
		collection.addLast("E");
		collection.addFirst("A");
		collection.add(1, "A2");
		System.out.println("Size after additions: " + collection.size());
		System.out.println("Contents: " + collection);
		
		//Removing elements
		collection.remove("A2");
		collection.remove(3);
		collection.removeFirst();
		collection.removeLast();
		//String firstElement = collection.peekFirst();
		System.out.println("Size after deletions: " + collection.size());
		System.out.println("Contents: " + collection);
	}

}
