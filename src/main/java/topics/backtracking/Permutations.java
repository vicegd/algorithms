package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: PERMUTATIONS OF N ELEMENTS
 * This program generates permutations of the 
 * integer elements that are in the vector v
 * @author viceg	
 */
public class Permutations {
	private static Logger log = LoggerFactory.getLogger(Permutations.class);
	private int n; //Size of the problem
	private int[]v; //Vector of elements
	private int[]sol; //To save each of the solutions
	private boolean[]mark; //To mark elements that are already used in any of the solutions (to avoid repeating any number)
	private int counter = 0; //Solution counter
	
	/**
	 * Constructor for Permutations objects
	 * @param n Number of elements for each permutation
	 */
	public Permutations(int n) {
		this.n = n;
		v = new int[n];
		
		for (int i=0; i<n; i++) //Generates a vector of size n
			v[i] = i;
	   
		mark = new boolean[n]; //To mark (label) which items are already used in each of the solutions
		for (int i=0; i<n; i++) 
			mark[i] = false; //At first there is none used in any solution
	  
		sol = new int[n]; //Creates a vector for the solutions
	}
	
	/**
	 * Performs the backtracking process
	 * @param level Level in the tree of states starting at 0
	 */
	public void backtracking(int level) {
		if (level == n) { //There is already a solution or complete permutation
			StringBuilder sb = new StringBuilder();
			for (int k=0; k<n; k++) //Iterates through all the elements of the solution and prints them
				sb.append(sol[k]+"*");
			log.debug(sb.toString());
			counter++;
		}
		else { 
			for (int j=0; j<n; j++) //Tries new permutations (new sequences of elements)
				if (!mark[j]) { //If the element is not marked in this permutation
					sol[level] = v[j]; //...Uses it
					mark[j] = true; //...And marks it
					backtracking(level+1); //When the backtracking ends (after printing its permutation), unmarks the previously marked numbers
					mark[j] = false;
	        }
	   } //else  
	}
	
	/**
	 * Gets the number of permutations after the backtracking process
	 * @return Number of permutations
	 */
	public int getNumberOfPermutations() {
		return counter;
	}
	
} 
