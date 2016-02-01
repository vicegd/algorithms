package topics.backtracking.times;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: PERMUTATIONS OF N ELEMENTS
 * This program calculates times to generate the permutations 
 * of n elements. The time is factorial, that is untreatable
 * @author viceg
 */
public class PermutationsTimes {
	private static Logger log = LoggerFactory.getLogger(PermutationsTimes.class);
	static int n;
	static int[] v;
	static int[] sol;
	static boolean[] mark; 
	static int counter = 0;
	
	public static void main(String arg[]) {
		long t1,t2;
		for (int dim=1; dim<=30; dim++) {
			counter = 0;
			n = dim;
			v = new int [n];
			for (int i=0; i<n; i++) 
				v[i] = i;
	   
			mark = new boolean[n];
			for (int i=0; i<n; i++) 
				mark[i] = false;
	  
			sol = new int[n];
	
			t1 = System.currentTimeMillis();
			backtracking(0);
			t2 = System.currentTimeMillis();
			log.debug("NUMBER="+n+"***SOLUTIONS="+counter+"***TIME="+(t2-t1));
	  }
	}
	
	static void backtracking(int level) {
		if (level == n) //There is already a solution or complete permutation
			counter++;
		else {
			for (int j=0; j<n; j++)
				if (!mark[j]) { 
					sol[level] = v[j];
					mark[j] = true;
					backtracking(level+1);
					mark[j] = false;
	        }
	    } //else  
	}

} 
