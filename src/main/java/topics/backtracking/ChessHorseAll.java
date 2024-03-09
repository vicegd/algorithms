package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE HORSE JUMPING PROBLEM
 * The program calculates all the ways of moving a 
 * horse through an entire chessboard of side n 
 * @author viceg
 */
public class ChessHorseAll {
	private static Logger log = LoggerFactory.getLogger(ChessHorseAll.class);
	private int n; //Size of the side of the board
	private int[][]board; //Chessboard
	private int[]a; //Displacements of horse (axis x)
	private int[]b; //Displacements of horse (axis y)
	private int count; //Number of solutions found
	private int startingX, startingY; //initial position of the horse
	
	/**
	 * Constructor for ChessHorseOne objects
	 * @param n Size of the board
	 * @param startingX starting position in the board (horizontally)
	 * @param startingY starting position in the board (vertically)
	 */
	public ChessHorseAll(int n, int startingX, int startingY) {
		this.n = n;
		this.startingX = startingX;
		this.startingY = startingY;
		
		board = new int[n][n]; //The cell (i,j) has not been visited

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
	 
		count = 0;
	}
	
	public void backtracking() {
		backtracking(2, startingX, startingY);
	}
	
	/**
	 * Performs the backtracking process
	 * @param jumpNumber Number of jumps performed so far starting at 1
	 * @param x Current x position
	 * @param y Current y position
	 */
	private void backtracking(int jumpNumber, int x, int y) {
		if (jumpNumber==n*n+1) {  //at this moment the horse has finished (it has been all over the board)
			count++;
			log.debug("SOLUTION FOUND NUMBER " + count);
			StringBuilder sb = new StringBuilder();
			for (int s=0;s<n;s++) {
				for (int t=0;t<n;t++)
					sb.append(String.format("%5d", board[s][t]));    
				sb.append("\n");
			}
			log.debug(sb.toString());
		}
		else
			for (int k=0;k<=7;k++) { //We have 8 possibilities (the different types of jumps of the chess horse)
				int u = x+a[k]; //Target coordinate x of the horse
				int v = y+b[k]; //Target coordinate y of the horse
	     
				if  (u>=0 && u<=n-1 && v>=0 && v<=n-1 && board[u][v]==0) {
					board[u][v] = jumpNumber; //We mark the position with the number of the jump
	             
					backtracking(jumpNumber+1,u,v);
	      
					board[u][v] = 0; //We leave it as it was (available to be used)
				} //if
			} //for       
	} //method

	/**
	 * Gets the number of solutions after the backtracking process
	 * @return Number of solutions
	 */
	public int getNumberOfSolutions() {
		return count;
	}
	
} 
