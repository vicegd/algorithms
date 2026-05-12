package topics.divideconquer;

/**
 * Mergesort algorithm using divide-and-conquer.
 *
 * Orders n elements with O(n log n) time complexity in all cases.
 * Uses auxiliary arrays during merge, so extra space is O(n).
 * @author vicegd
 */
public class Mergesort {
        private int[]elements;

        /**
         * Sorts the input array using mergesort.
         *
         * @param v array to be sorted
         * @throws NullPointerException if v is null
         */
        public void mergesort(int[]v) {
                if (v == null) {
                        throw new NullPointerException("array cannot be null");
                }
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
         * Combines two sorted subarrays into one sorted segment.
         *
         * Left subarray is [x1..x2], right subarray is [y1..y2].
         * This merge step is O(n), where n is sizeX + sizeY.
         *
         * @param x1 left index of first subarray
         * @param x2 right index of first subarray
         * @param y1 left index of second subarray
         * @param y2 right index of second subarray
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

