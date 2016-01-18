package topics.sorting.others;

import topics.sorting.core.ISortingAlgorithm;
import topics.sorting.core.Util;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Sorting algorithm: Radix method
 * @author viceg
 */
public class Radix implements ISortingAlgorithm{
	@SuppressWarnings("unchecked")
    //10 queues for base 10 numbers
	private Queue<Integer>[] buckets = new ArrayDeque[10];
	private int[] elements = null;

	public Radix() { 
        for (int i=0; i<10; i++) {
            buckets[i] = new ArrayDeque<Integer>();
        }
	}
	
    private int getMaxNumberOfDigits() {
    	int maxPosition = Util.findPosMax(elements, 0);
    	int maxValue = elements[maxPosition];
    	int maxNumberOfDigits = String.valueOf(maxValue).length();
        return maxNumberOfDigits;
    }
    
    private int getDigit(int integer, int divisor) {
        return (integer / divisor) % 10;
    }
	
	@Override
	public void sort(int[] elements) {
		this.elements = elements;
        int numberOfDigits = getMaxNumberOfDigits(); //k value
        int divisor = 1; //to iterate the positons (1, 10, ...)
        
        for (int n=0; n<numberOfDigits; n++){
            for (int element : elements) {
                int digit = getDigit(element, divisor);
                buckets[digit].add(element);
            }
            int index = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    int integer = bucket.remove();
                    elements[index++] = integer;
                }
            }
            divisor *= 10;
        }
	}
	
	@Override
	public void sort(int[] elements, boolean trace) {
		this.elements = elements;
        int numberOfDigits = getMaxNumberOfDigits(); //k value
        int divisor = 1; //to iterate the positons (1, 10, ...)
        
        for (int n=0; n<numberOfDigits; n++){
            for (int element : elements) {
                int digit = getDigit(element, divisor);
                buckets[digit].add(element);
            }
            int index = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    int integer = bucket.remove();
                    elements[index++] = integer;
                }
            }
            divisor *= 10;
            Util.traceMessage("Digit: " + n, elements);
        }
	}
	



}
