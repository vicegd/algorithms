package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: SUBSETS OF A GIVEN SUM
 * This program, given a set consisting of n different positive 
 * integers, computes all subsets which sum a given value c
 * @author viceg
 */
public class SubsetsGivenSum {
	private static Logger log = LoggerFactory.getLogger(SubsetsGivenSum.class);
	private int n; //The amount of numbers we have for working with
	private int c; //The sum we are looking for
	private int[]v; //n positive different numbers     
	private boolean[] mark; //If a number is added or not
	private int sum; //Cumulative sum until a state 
	private int counter; //Number of solutions
	
	/**
	 * Constructor for SubsetsGivenSum objects
	 * @param n Number of elements for each case
	 * @param c Sum that we are looking for
	 */	
	public SubsetsGivenSum(int n, int c) {
		this.n = n;
		this.c = c;
		
		v = new int[n];
		mark = new boolean[n];
		
		for (int i=0; i<n; i++) 
			mark[i]=false;
		counter = 0;
		sum = 0;
	}
	
	/**
	 * An example: the first n natural numbers
	 */
	public void assumption1() {
		for (int i=0; i<n; i++) 
			v[i] = i+1;
	}
	
	/**
	 * Another example: the first square numbers
	 */
	public void assumption2() {
		for (int i=0; i<n; i++) 
			v[i] = (i+1)*(i+1);
	}
	
	/**
	 * Performs the backtracking process
	 * @param level Level in the tree of states starting at 0
	 */
	public void backtracking(int level) {
		if (level == n) { //There is already a subset to be analyzed
			if (sum == c) { //If meets the requirement (the final value c)
				counter++; //Then we have a new solution
				log.debug("SUBSET SUMS "+c+"=");
				StringBuilder sb = new StringBuilder();
				for (int k=0; k<n ;k++)
					if (mark[k]) //All marked numbers are part of the subset 
						sb.append(v[k]+"+");
				log.debug(sb.toString());
			}          
		}    
		else
			if (sum <= c) //If sum>c it can be pruned, since the values we are working with are positive
				//With j=0 the element does not belong to the solution; with j=1 we consider the element as part of the solution (there are two different possibilities)
				for (int j=0; j<=1; j++) { 
					if (j == 0) { 
						sum = sum + v[level];
						mark[level] = true;
					}    
					backtracking(level+1);
					if (j == 0) { //Unmark the marked after the backtracking process
						sum = sum - v[level];
						mark[level] = false;
					}
				} //for
	} //backtracking
	
	/**
	 * Gets the number of solutions after the backtracking process
	 * @return Number of solutions
	 */
	public int getNumberOfSolutions() {
		return counter;
	}
} 
