package topics.divideconquer;

/**
 * DIVIDE AND CONQUER PROBLEM: ORDER n ELEMENTS WITH AN EXTERNAL ALGORITHM. 
 * THE MERGESORT ALGORITHM
 * @author viceg
 */
public class Mergesort {
	private int[]elements;
	
	/**
	 * This algorithm uses the D&C technique. Complexity:
	 * All cases: 
	 * - By division a=2;b=2;k=1 => O(nlogn)
	 * @param v Array to be sorted
	 */
	public void mergesort(int[]v) {
		elements = v;
		mergesort(0, v.length-1);
	}
	private void mergesort(int left, int right) {
		if (right > left) {
			int center = (left+right)/2;
			mergesort(left, center);
			mergesort(center+1, right);
			combine(left, center, center+1, right);
		}
	}
	
	/**
	 * It is a linear process O(n) which combines a sorted 
	 * sequence x1..x2 with another one y1..y2, 
	 * bringing the result on the sorted vector v(elements).
	 * It has to use for it two auxiliary vectors, 
	 * since it is impossible to do everything on v
	 * @param x1 Left index of the first part to be combined
	 * @param x2 Right index of the first part to be combined
	 * @param y1 Left index of the second part to be combined
	 * @param y2 Right index of the second part to be combined
	 */
	private void combine(int x1, int x2, int y1, int y2) {
		int sizeX = x2-x1+1; //size from x1 to x2
		int sizeY = y2-y1+1; //size from y1 to y2
		int[]x = new int[sizeX]; //auxiliary vector for saving from x1 to x2
		int[]y = new int[sizeY]; //auxiliary vector for saving from y1 to y2
		for (int i=0; i<sizeX; i++) 
			x[i] = elements[x1+i]; //copies the values from v to x
		for (int i=0; i<sizeY; i++) 
			y[i] = elements[y1+i]; //copies the values from v to y
		int indexX = 0; //index pointing to the current element we are working with in the x1..x2 subset
		int indexY = 0; //index pointing to the current element we are working with in the y1..y2 subset
		int valueX = 0; //value we are working with in the x1..x2 subset
		int valueY = 0; //value we are working with in the y1..y2 subset
		for (int i=0; i<sizeX+sizeY; i++) { //iterates though all the values
			//if we have more values in the x1..x2 subset we get the one pointed by the index
			if (indexX < sizeX) 
				valueX = x[indexX];
			else valueX = Integer.MAX_VALUE; 
			//if we have more values in the y1..y2 subset we get the one pointed by the index
			if (indexY < sizeY) 
				valueY = y[indexY];
			else valueY = Integer.MAX_VALUE;
			//we copy the smallest element (Xi or Yi) in v
			if (valueX <= valueY) {
				elements[x1+i] = valueX; //copies the value of Xi
				indexX = indexX+1; //moves the index to the next element in the x1..x2 subset since we have placed one element
			}
			else {
				elements[x1+i] = valueY; //copies the value of Yi
				indexY = indexY+1; //moves the index to the next element in the y1..y2 subset since we have placed one element
			}
	  }//for
	}
	  
} 

