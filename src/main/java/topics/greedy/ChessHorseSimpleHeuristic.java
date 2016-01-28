package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREEDY ALGORITHM PROBLEM: THE HORSE JUMPING PROBLEM (Knight's tour)
 * It has not an optimal solution in some cases
 * @author viceg
 */
public class ChessHorseSimpleHeuristic {
	private static Logger log = LoggerFactory.getLogger(ChessHorseSimpleHeuristic.class);
	private int n; //Size of the board
	private int[][]board; //Board in which we move the horse
	
	/**
	 * Constructor for ChessHorse objects
	 * @param n Size of the board
	 */
	public ChessHorseSimpleHeuristic(int n) {
		this.n = n;
		board = new int[n][n];
		for (int i=0;i<n;i++)
			for (int j=0;j<n;j++)
				board[i][j]= -1; //The number indicates the order in which the horse goes through that cell (-1 if nothing)
	}
	
	/**
	 * Checks whether the horse can complete the board
	 * @param pos Initial position of the horse
	 * @return Whether the horse complete the board
	 */
	public boolean horse(int[] pos) {
		int[] newPos = new int[2]; //To save the possible new position (x and y)
		for (int i = 0; i<n*n; i++) { //i indicates the number of movement. We need to cover the entire board
			board[pos[0]][pos[1]] = i; //We put the horse in a position (x and y)
			if (!newMovement(pos, newPos)) //If we don't have a movement => we fail
				if (i != n*n-1) return false; //Unless that is the last element of all
			pos[0] = newPos[0]; //newPos[0] is the variable in which we save the new movement (x)
			pos[1] = newPos[1]; //newPos[1] is the variable in which we save the new movement (y)
		}
		writeSolution();
		return true; //We have completed the board with horse jumps
	}
	
	private boolean newMovement(int[] pos, int[] newPos) { //Heuristic
		boolean hasMovement = false;
		int i = 1;
		while ((i <= 8)&&(!hasMovement)){ //Different types of jumps
			if (jump(pos, i, newPos)) //If we can do that movement, we keep the new possible position in the newPos variable
				hasMovement = true;
			i++;
		}
		return hasMovement;
	}

	/* Returns if the horse can do a particular type of jump */
	private boolean jump(int[] pos, int jumpType, int[] newPos) {
		//newPos saves the new position to which the horse will jump
		switch (jumpType) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		}
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
	
	private void writeSolution(){
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i = 0; i<board.length; i++){
			for (int j = 0; j<board.length; j++){
				sb.append(board[i][j] + "\t");
			}
			sb.append("\n");
		}
		log.trace(sb.toString());
	}

} 
