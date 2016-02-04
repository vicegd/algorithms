package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE PROBLEM OF THE N QUEENS
 * This program calculates one way of placing 
 * n queens on a chessboard of side n
 * @author viceg
 */
public class ChessQueensOne {
	private static Logger log = LoggerFactory.getLogger(ChessQueensOne.class);
	private int n; //Size of the side of the board
	private int[]sol;  //The solution. If sol[j] = i => there is a queen in column j and row i
	private boolean[]a; //Indicates whether a queen can be placed in row i
	private boolean[]b; //Indicates whether a queen can be placed in diagonal i+j
	private boolean[]c; //Indicates whether a queen can be placed in diagonal i-j
	private boolean found; //Solutions found
	
	/**
	 * Constructor for ChessQueensOne objects
	 * @param n Size of the board
	 */
	public ChessQueensOne(int n) {
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
	
		found = false;;
	}
	
	/**
	 * Performs the backtracking process
	 * @param j Level in the tree of states starting at 0
	 */
	public void backtracking(int j) {
		if (j==n) {  //We have already placed the n queens
			found = true;
			log.debug("SOLUTION FOUND");
			StringBuilder sb = new StringBuilder();
			for (int k=0;k<n;k++) 
				sb.append("COLUMN "+ k + "  ***  ROW "+ sol[k] + "\n");
			log.debug(sb.toString());
		}
		else
			for (int i=0;i<n;i++)
				if (!a[i] && !b[i+j] && !c[i-j+n-1] && !found) {  //SHORT CIRCUIT
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
	 * Returns whether there is a solution
	 * @return True is there is a solution. False otherwise
	 */
	public boolean isSolution() {
		return found;
	}
} 
