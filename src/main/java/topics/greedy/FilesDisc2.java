package topics.greedy;

import topics.sorting.Quicksort;

/**
 * GREEDY ALGORITHM PROBLEM: MINIMIZE THE FREE SPACE ON A DISK WITH FILES
 * It has NOT an optimal solution in some cases. The idea
 * is: First the biggest files while the disc has enough 
 * space to copy them
 * @author viceg
 */
public class FilesDisc2 {
	 private int[] files; //Size of each of the files
	 private int discCapacity; //Maximum capacity of the disk where files will be saved

	 /**
	  * Constructor for FilesDisc1 objects
	  * @param files Size of the files used
	  * @param discCapacity Maximum disc capacity
	  */
	 public FilesDisc2(int[] files, int discCapacity) {
		 this.files = files;
		 this.discCapacity = discCapacity;
	 }
	 
	 /**
	  * Calculates the amount of used space in a disc
	  * @return The amount of data used of a disc
	  */
	 public int calculate(){
		 Quicksort quicksort = new Quicksort();
		 quicksort.sort(files);
		 int i = files.length-1;
		 int counter = 0;
		 while (i >= 0){
			 if ((counter + files[i]) <= discCapacity){
				 counter += files[i];
			 }
			 i--;
		 }
		 return counter;
	 }
	
}
