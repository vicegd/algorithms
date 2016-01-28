package topics.greedy;

import topics.sorting.Quicksort;

/**
 * GREEDY ALGORITHM PROBLEM: MAXIMIZE THE NUMBER OF FILES ON A DISK
 * It has an optimal solution. The idea is: 
 * First the smallest files while the disc has enough 
 * space to copy them 
 * @author viceg
 *
 */
public class FilesDisc1 {
	 private int[] files; //Size of each of the files
	 private int discCapacity; //Maximum capacity of the disk where files will be saved

	 /**
	  * Constructor for FilesDisc1 objects
	  * @param files Size of the files used
	  * @param discCapacity Maximum disc capacity
	  */
	 public FilesDisc1(int[] files, int discCapacity) {
		 this.files = files;
		 this.discCapacity = discCapacity;
	 }
	 
	 /**
	  * Calculates the amount of files in a disc
	  * @return The number of files inserted in the disc
	  */
	 public int calculate(){
		 Quicksort quicksort = new Quicksort();
		 quicksort.sort(files);
		 int i = 0;
		 int counter = 0;
		 int numberOfFiles = 0;
		 while (i < files.length){
			 if ((counter + files[i]) <= discCapacity){
				 counter += files[i];
				 numberOfFiles++;
			 }
			 i++;
		 }
		 return numberOfFiles;
	 }
	
}
