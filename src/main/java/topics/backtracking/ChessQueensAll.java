package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE PROBLEM OF THE N QUEENS
 * This program calculates one way of placing 
 * n queens on a chessboard of side n
 * @author viceg
 */
@SuppressWarnings("unused")
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
		for (int i=0;i<n;i++) 
			a[i] = false;
	  
		b = new boolean[2*n-1];
		for (int i=0;i<2*n-2;i++) //e.g., if n = 5, we would have from 0 to 8
			b[i] = false;
	
		c = new boolean[2*n-1];
		for (int i=0;i<2*n-2;i++) //e.g., if n = 5, we would have from 0 to 8
			c[i] = false;
	
		count = 0;
	}
		
	/**
	 * Performs the backtracking process
	 * @param level Level in the tree of states starting at 0
	 */
	public void backtracking(int j) {
		 throw new UnsupportedOperationException("This operation needs to be implemented");  
	} //method
		
	/**
	 * Gets the number of solutions after the backtracking process
	 * @return Number of solutions
	 */
	public int getNumberOfSolutions() {
		return count;
	}
} 
