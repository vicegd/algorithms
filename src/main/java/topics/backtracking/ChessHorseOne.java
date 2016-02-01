package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE HORSE JUMPING PROBLEM
 * This program calculates all the ways of moving a 
 * horse through an entire chessboard of side n 
 * @author viceg
 */
@SuppressWarnings("unused")
public class ChessHorseOne {	
	private static Logger log = LoggerFactory.getLogger(ChessHorseOne.class);
	private int n; //Size of the side of the board
	private int[][]board; //Chessboard
	private int[]a; //Displacements of horse (axis x)
	private int[]b; //Displacements of horse (axis y)
	private boolean found; //Solutions found
	
	/**
	 * Constructor for ChessHorseOne objects
	 * @param n Size of the board
	 * @param startingX starting position in the board (horizontally)
	 * @param startingY starting position in the board (vertically)
	 */
	public ChessHorseOne(int n, int startingX, int startingY) {
		this.n = n;
		
		board = new int[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++)
				board[i][j]= 0; //The cell (i,j) has not been visited

		board[startingX][startingY]=1;  //Initial position of the horse
	   
		a = new int[8]; //Valid movements in x
		b = new int[8]; //Valid movements in y
		a[0]=1; b[0]=2; 
		a[1]=2; b[1]=1;
		a[2]=2; b[2]=-1;
		a[3]=1; b[3]=-2;
		a[4]=-1; b[4]=-2;
		a[5]=-2; b[5]=-1;
		a[6]=-2; b[6]=1;
		a[7]=-1; b[7]=2;
	 
		found=false;
	}
	
	/**
	 * Performs the backtracking process
	 * @param jumpNumber Number of jumps performed so far starting at 1
	 * @param x Current x position
	 * @param y Current y position
	 */
	public void backtracking(int jumpNumber, int x, int y) {
		throw new UnsupportedOperationException("This operation needs to be implemented");        
	} //method

	/**
	 * Returns whether there is a solution
	 * @return True is there is a solution. False otherwise
	 */
	public boolean isSolution() {
		return found;
	}
} 
