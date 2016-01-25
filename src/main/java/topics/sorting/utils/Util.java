package topics.sorting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class to trace and use common operations among sorting algorithms
 * @author viceg
 */
public class Util {
	static Logger log = LoggerFactory.getLogger(Util.class);
	
	/**
	 * Logs messages for the sorting algorithm
	 * @param iteration Number of iteration in the algorithm (from 0)
	 * @param elements Array with numbers
	 */
	public static void trace(int iteration, int[] elements){
		StringBuilder sb = new StringBuilder();
		sb.append("Iteration: " + iteration + " - ");
		for (int k = 0; k < elements.length; k++){
			sb.append(elements[k] + " ");
		}
		log.trace(sb.toString());
	}
	
	/**
	 * Logs messages for the Shell sorting algorithm
	 * @param k Value of the k parameter in the Shell algorithm
	 * @param pos Value for the pos parameter in the Shell algorithm
	 * @param elements Array with numbers
	 */
	public static void traceShellSort(int k, int pos, int[] elements){
		StringBuilder sb = new StringBuilder();
		sb.append("K: " + k + " - POS: " + pos + " - ");
		for (int i = 0; i< elements.length; i++){
			sb.append(elements[i] + " ");
		}
		log.trace(sb.toString());
	}
	
	/**
	 * Logs string messages for some sorting algorithms
	 * @param message Message to be logged
	 * @param elements Array with numbers
	 */
	public static void traceMessage(String message, int[] elements){
		StringBuilder sb = new StringBuilder();
		sb.append(message + " - ");
		for (int k = 0; k < elements.length; k++){
			sb.append(elements[k] + " ");
		}
		log.trace(sb.toString());
	}
	
	/**
	 * Interchanges element i and element j
	 * @param elements Array with numbers
	 * @param i Position of one element to be interchanged
	 * @param j Position of the other element
	 */
	public static void interchange(int[] elements, int i, int j) {
		int temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
	
	/**
	 * Finds the position of the smallest element in the array
	 * @param elements Array with numbers
	 * @param firstElement First element from which it is going to search
	 * @return Position of the smallest element
	 */
	public static int findPosMin(int[] elements, int firstElement) {
		int value = Integer.MAX_VALUE;
		int pos = Integer.MAX_VALUE;
		for (int i = firstElement; i < elements.length; i++){
			if (elements[i] < value){
				value = elements[i];	
				pos = i;
			}
		}
		return pos;
	}
	
	/**
	 * Finds the position of the biggest element in the array
	 * @param elements Array with numbers
	 * @param firstElement First element from which it is going to search
	 * @return Position of the biggest element
	 */
	public static int findPosMax(int[] elements, int firstElement) {
		int value = Integer.MIN_VALUE;
		int pos = Integer.MIN_VALUE;
		for (int i = firstElement; i < elements.length; i++){
			if (elements[i] > value){
				value = elements[i];
				pos = i;
			}
		}
		return pos;
	}
}
