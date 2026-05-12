package topics.principles.examples;

import java.util.ArrayDeque;

/**
 * Demonstrates {@link java.util.ArrayDeque} as both a queue (FIFO via
 * {@code add}/{@code poll}) and a stack (LIFO via {@code push}/{@code pop}).
 *
 * @author vicegd
 */
public class ArrayDequeExample {

	public static void main(String[] args) {
		ArrayDeque<String> collection = new ArrayDeque<String>();
		collection.add("B");
		collection.add("A");
		collection.add("C");
		collection.add("D");
		collection.add("A");
		while (collection.peek() != null) {
			System.out.print(collection.poll());
		}
		System.out.println();
		
		collection.push("B");
		collection.push("A");
		collection.push("C");
		collection.push("D");
		collection.push("A");
		collection.add("Z");
		collection.push("J");
		while (collection.peek() != null) {
			System.out.print(collection.pop());
		}
		System.out.println();
	}

}
