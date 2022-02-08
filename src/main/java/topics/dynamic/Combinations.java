package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DYNAMIC PROGRAMMING PROBLEM: MATHEMATICAL COMBINATIONS n x k
 * The recursive algorithm that calculates Combinations has 
 * exponential complexity and repeats calculations 
 * already performed. However, it is possible to design an 
 * algorithm with an quadratic execution time of O(n*k) based 
 * on the idea of the Pascal Triangle. This requires the creation 
 * of a two-dimensional table wherein storing the intermediate 
 * values we are obtaining 
 * @author viceg
 */
public class Combinations {
	private static Logger log = LoggerFactory.getLogger(Combinations.class);
	
	/**
	 * Calculates the combinations of n elements taken k by k
	 * @param table Table to calculate the combinations
	 * @param n n elements
	 * @param k Taken k by k
	 * @return Number of combinations
	 */
	public int combinations(int[][] table, int n, int k) {
		for (int i = 0; i <= n; i++) table[i][0] = 1;
		for (int i = 1; i <= n; i++) table[i][1] = i;
		for (int i = 2; i <= k; i++) table[i][i] = 1;
		for (int i = 3; i <= n; i++) 
			for (int j = 2; j <= k; j++) 
				table[i][j] = table[i-1][j-1] + table[i-1][j];
		return table[n][k];
	}  
	
	/**
	 * Shows the result combinations of n elements taken k by k
	 * @param table Table to calculate the combinations
	 * @param n n elements
	 * @param k taken k by k
	 */
	public void writeSolution(int[][] table, int n, int k){
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= k; j++) { 
				sb.append(table[i][j] + "\t");
			}
			sb.append("\n");
		}
		log.trace(sb.toString());
	}
	
	/**
	 * Divide and conquer recursive version. It solves 
	 * repeatedly the same subproblems. It has no polynomial 
	 * complexity (so it is untreatable), and the worst case 
	 * occurs with calls of the type combiDyV(n, n/2)
	 * @param n n elements
	 * @param k Taken k by k
	 * @return Number of combinations
	 */
	public long combinationsDivideAndConquer(int n, int k) {
		if (n==k) 
			return 1;
		else 
			if (k==0)
				return 1;
		else return combinationsDivideAndConquer(n-1, k-1) + 
				combinationsDivideAndConquer(n-1, k);
	}
	
}
