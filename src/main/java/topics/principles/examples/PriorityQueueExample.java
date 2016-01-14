package topics.principles.examples;

import java.util.PriorityQueue;

public class PriorityQueueExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriorityQueue<String> collection = new PriorityQueue<String>();
		System.out.println("Initial size of the collection: " + collection.size());
		
		//Adding elements
		collection.add("B");
		collection.add("A");
		collection.add("C");
		collection.add("D");
		collection.add("A");
		//Removing elements
		collection.remove("A");
		
		System.out.println("Size after operations: " + collection.size());
		while (collection.peek() != null) {
			System.out.print(collection.poll());
		}
		System.out.println();
	}

}
