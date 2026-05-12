package topics.sorting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.sorting.utils.ISortingAlgorithm;
import topics.sorting.utils.Util;

/**
 * Quicksort sorting algorithm.
 *
 * Uses divide-and-conquer with median-of-three pivot selection.
 * Average and best-case complexity is O(n log n), worst case is O(n^2).
 * @author vicegd
 */
public class Quicksort implements ISortingAlgorithm{
        static Logger log = LoggerFactory.getLogger(Quicksort.class);

        @Override
        public void sort(int[] elements) {
                quickSort(elements, 0, elements.length-1, 1);
        }

        @Override
        public void sort(int[] elements, boolean trace) {
                if (trace) log.debug("Quicksort method");
                quickSort(elements, 0, elements.length-1, 1);
        }

        /**
         * Returns the median-of-three pivot index and partially orders left/center/right.
         *
         * @param elements array to sort
         * @param left left bound index
         * @param right right bound index
         * @return index of the median element among left, center, and right
         */
        private int medianOfThree(int[] elements, int left, int right){ 
                int center = (left + right) / 2;
                if (elements[left] > elements[center])
                        Util.interchange(elements, left, center);
                if (elements[left] > elements[right])
                        Util.interchange(elements, left, right);
                if (elements[center] > elements[right])
                        Util.interchange(elements, center, right);
                return center;
        }

        private void quickSort(int[] elements, int left, int right, int level){
                int i = left;
                int j = right - 1;
                int pivot;

                if (left < right){ //if there is one element it is not necessary
                        int center = medianOfThree(elements, left, right);
                        //if there are less than or equal to 3 elements, there are just ordered
                        if ((right - left) >= 3){ 
                                pivot = elements[center]; //choose the pivot
                                Util.interchange(elements, center, right); //hide the pivot

                                do {         
                                while (elements[i] <= pivot && i < right) i++; //first element > pivot
                                while (elements[j] >= pivot && j > left) j--; //first element < pivot
                                if (i < j) Util.interchange(elements, i, j);
                            } while (i < j);   //end while

                                //we set the position of the pivot
                                Util.interchange(elements, i, right);
                                Util.traceMessage("Level: " + level + " Pivot: " + pivot, elements);
                                quickSort(elements, left, i-1, level+1);
                                quickSort(elements, i+1, right, level+1);
                        }
                        else Util.traceMessage("Level: " + level, elements);
                } else Util.traceMessage("Level: " + level, elements);
        }

}

