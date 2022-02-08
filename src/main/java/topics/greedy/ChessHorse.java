package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREEDY ALGORITHM PROBLEM: THE HORSE JUMPING PROBLEM (Knight's tour)
 * It has not an optimal solution in some cases
 * @author viceg
 */
public class ChessHorse {
	private static Logger log = LoggerFactory.getLogger(ChessHorseSimpleHeuristic.class);
	private int n; //Size of the board
	private int[][]board; //Board in which we move the horse
	
	/**
	 * Constructor for ChessHorse objects
	 * @param n Size of the board
	 */
	public ChessHorse(int n) {
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
	
	private boolean newMovement(int[] pos, int[] newPos) { //heuristic
		boolean hasMovement = false; //To know if it has a possible movement
		int[] tempPos = new int[2]; //To temporally keep the value to return
		int minAccessible = Integer.MAX_VALUE; //To keep the smaller number of jumps from a position
		for (int i = 1; i <= 8; i++){ //Different types of jumps. We should try all to study the position from which we have less possible jumps
			if (jump(pos, i, newPos)){ //If we can do that movement, we keep the new possible position in the newPos variable
				hasMovement = true; //We have at least one possible movement
				int result = jumpCounter(newPos); //We should choose the one from which we have less accessible jumps (our heuristic)
				if (result < minAccessible) {
					minAccessible = result;
					tempPos[0] = newPos[0]; //We need tempPos to keep the best result because the value of newPos is changes in each iteration
					tempPos[1] = newPos[1];
				}
			}
		}
		newPos[0] = tempPos[0];
		newPos[1] = tempPos[1];
		return hasMovement;
	}
	
	/* Returns the number of cells to which the horse 
	 * can jump from a given position */
	private int jumpCounter(int[] pos) {
		int count = 0;
		for (int i = 1; i <= 8; i++){ //Different types of jumps
			if (jump(pos, i, new int[2])) count++;
		}
		return count;
	}

	/* Returns if the horse can do a particular type of jump */
	private boolean jump(int[] pos, int jumpType, int[] newPos) {
		//newPos saves the new position to which the horse will jump
		switch (jumpType) {
		case 1:
			newPos[0] = pos[0]+2; newPos[1] = pos[1]+1;
			break;
		case 2:
			newPos[0] = pos[0]+1; newPos[1] = pos[1]+2;
			break;
		case 3:
			newPos[0] = pos[0]-1; newPos[1] = pos[1]+2;
			break;
		case 4:
			newPos[0] = pos[0]-2; newPos[1] = pos[1]+1;
			break;
		case 5:
			newPos[0] = pos[0]-2; newPos[1] = pos[1]-1;
			break;
		case 6:
			newPos[0] = pos[0]-1; newPos[1] = pos[1]-2;
			break;
		case 7:
			newPos[0] = pos[0]+1; newPos[1] = pos[1]-2;
			break;
		case 8:
			newPos[0] = pos[0]+2; newPos[1] = pos[1]-1;
			break;
		}
		return ((newPos[0]>=0)&&(newPos[0]<n) //The new position for X is within the board
				&&(newPos[1]>=0)&&(newPos[1]<n) //The new position for Y is within the board
				&&(board[newPos[0]][newPos[1]]==-1)); //The new position is empty
	}
	
	public void writeSolution(){
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
