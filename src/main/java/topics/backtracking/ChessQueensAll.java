package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE PROBLEM OF THE N QUEENS
 * This program calculates one way of placing 
 * n queens on a chessboard of side n
 * @author viceg
 */
public class ChessQueensAll {
	private static Logger log = LoggerFactory.getLogger(ChessQueensAll.class);
	private int n; //Size of the side of the board
	private int[]sol;  //The solution. If sol[j] = i => there is a queen in column j and row i
	private boolean[]a; //Indicates whether a queen can be placed in row i
	private boolean[]b; //Indicates whether a queen can be placed in diagonal i+j
	private boolean[]c; //Indicates whether a queen can be placed in diagonal i-j
	private int count; //Number of solutions found
	
	/**
	 * Constructor for ChessQueensAll objects
	 * @param n Size of the board
	 */
	public ChessQueensAll(int n) {
		this.n = n;	 
		sol = new int[n];	
		a = new boolean[n];
		b = new boolean[2*n-1];
		c = new boolean[2*n-1];
		count = 0;
	}
		
	/**
	 * Performs the backtracking process
	 * @param j Level in the tree of states starting at 0
	 */
	public void backtracking(int j) {
		if (j==n) { //We have already placed the n queens
			count++;
			log.debug("SOLUTION FOUND NUMBER " + count);
			StringBuilder sb = new StringBuilder();
			for (int k=0;k<n;k++) 
				sb.append("COLUMN "+ k +"  ***  ROW " + sol[k] + "\n");
			log.debug(sb.toString());
		}
		else
			for (int i=0;i<n;i++)
				if (!a[i] && !b[i+j] && !c[i-j+n-1]) {
					sol[j] = i; //There is a queen in column j and row i
					a[i] = true; //Row used
					b[i+j] = true; //Diagonal i+j used
					c[i-j+n-1] = true; //Diagonal i-j used. The +n-1 is to have the range always between 0..n-1
		         
					backtracking(j+1);
		         
					//We leave it as it was (available to be used)
					sol[j] = -1;
					a[i] = false;
					b[i+j] = false;
					c[i-j+n-1] = false;
		       } //if     
	} //method
		
	/**
	 * Gets the number of solutions after the backtracking process
	 * @return Number of solutions
	 */
	public int getNumberOfSolutions() {
		return count;
	}
} 
