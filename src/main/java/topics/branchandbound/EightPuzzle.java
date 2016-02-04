package topics.branchandbound;

import java.util.ArrayList;
import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Node;

/**
 * BRANCH AND BOUND PROBLEM: THE PUZZLE
 * @author viceg
 */
public class EightPuzzle extends BranchAndBound {	
	/**
	 * Constructor for EightPuzzle objects
	 * @param heuristicType Type of the heuristic used to solve the problem - Manhattan or WrongPlace
	 * @param board Representation of the board for playing the EightPuzzle
	 */
    public EightPuzzle(HeuristicType heuristicType, int[] board) {
    	rootNode = new Puzzle(heuristicType, board); //We create the puzzle to start playing
    }
}
/***************************************************/


/***************************************************/
class Puzzle extends Node {
    private int[] board; //Board
    private HeuristicType heuristicType; //Type of heuristic function

    public Puzzle(HeuristicType heuristicType, int[] board) { //Generates a fresh set of squares (ROOT NODE)
    	this.heuristicType = heuristicType;
    	this.board = board;
    }

    public Puzzle(int[] board, HeuristicType heuristicType, int depth, int parentID) {
        this.board = board;
        this.heuristicType = heuristicType;
        this.depth = depth;
        this.parentID = parentID;
        calculateHeuristicValue();
    }

    /* Returns a copy of the board but in this
     * case, moving the empty cell upwards */
    private int[] up() {
        int[] temp = board.clone();
        int emptyCell = 0;
        for (int i=0; i<temp.length; i++) {
            if (temp[i] == 9)
                emptyCell = i;
        }
        //Invalid option check
        if (emptyCell < 3) //It is not possible to move the piece in the indicated direction (empty cell in 0, 1 or 2)
            return temp;
        temp[emptyCell] = temp[emptyCell-3];
        temp[emptyCell-3] = 9;
        return temp;
    }
  
    /* Returns a copy of the board but in this
     * case, moving the empty cell downwards */
    private int[] down() {
        int[] temp = board.clone();
        int emptyCell= 0;
        for (int i=0; i<temp.length; i++) {
            if (temp[i] == 9)
                emptyCell = i;
        }
        //Invalid option check
        if (emptyCell > 5) //It is not possible to move the piece in the indicated direction (empty cell in 6, 7 or 8)
            return temp;
        temp[emptyCell] = temp[emptyCell+3];
        temp[emptyCell+3] = 9;
        return temp;
    }

    /* Returns a copy of the board but in this
     * case, moving the empty cell to the left */
    private int[] left() {
        int[] temp = board.clone();
        int emptyCell= 0;
        for (int i=0; i<temp.length; i++) {
            if (temp[i] == 9)
                emptyCell = i;
        }
        //Invalid option check
        if (emptyCell%3 == 0) //It is not possible to move the piece in the indicated direction (empty cell in 0,3 or 6)
            return temp;
        temp[emptyCell] = temp[emptyCell-1];
        temp[emptyCell-1] = 9;
        return temp;
    }
 
    /* Returns a copy of the board but in this
     * case, moving the empty cell to the right */
    private int[] right() {
        int[] temp = board.clone();
        int emptyCell= 0;
        for (int i=0; i<temp.length; i++){
            if (temp[i] == 9)
                emptyCell = i;
        }
        //Invalid option check
        if (emptyCell%3 == 2) //It is not possible to move the piece in the indicated direction (empty cell in 2, 5 or 8)
            return temp;
        temp[emptyCell] = temp[emptyCell+1];
        temp[emptyCell+1] = 9;
        return temp;
    }

    //Heuristic: count the number of pieces in the wrong place
    private int getWrongPlaceHeuristicValue() {
        int wrong = 0;
        for (int i=0; i<9; i++)
            if (board[i] != (i+1))
                wrong++;
        return wrong;
    }

    //Heuristic: Manhattan distance
    private int getManhattanHeuristicValue() {
        int manhattan = 0;
        int x1, x2, y1, y2;
        for (int i=0; i<9; i++) {
            //Breaks the game locations into a grid formation
            //Measures the distance using grid length
            x1 = (board[i]-1)%3; //Actual horizontal distance
            x2 = i%3; //Optimal horizontal distance
            y1 = (board[i]-1)/3; //Actual vertical distance
            y2 = i/3; //Optimal vertical distance
            manhattan += Math.abs(x1-x2) + Math.abs(y1-y2);
        }
        return manhattan;
    }
    
    private boolean prune() { //PRUNING METHOD
    	boolean result = false;
    	
    	int x = 0;
    	int sum = 0;
    	for (int i = 1; i <= 9; i++) {
    		sum += smaller(i);
    	}
    	if ((position(9)%2) == 0)
    		x = 1;
    	
    	if ((sum + x)%2 == 1) { //It is an odd number
    		result = true;
    	}
    	
    	return result;
    }
  
    /* It is the number of pieces j such that j<i 
     * and position(j)>position(i) */
    private int smaller(int i) { 
    	int j = 0;
    	for (int k = position(i); k < board.length; k++) {
    		if (board[k] < i) j++;
    	}
    	return j;
    }
    
    /* Position in the initial state of the piece number i */
    private int position(int i) { 
    	int position = -1;
    	for (int k = 0; k < board.length; k++) {
    		if (board[k] == i)
    			position = k+1; //We start in 0
    	}
    	return position;
    }
   
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("===============\n");
        for (int i=1; i<=9; i++) {
            if ((i%3) == 1)
                sb.append(" | "); //First |
            if (board[i-1] == 9)
                sb.append("  | ");
            else
                sb.append(board[i-1]+" | ");
            if ((i%3) == 0) //Each three numbers...
                sb.append("\n===============\n");
        }
        return sb.toString();
    }

    @Override
    public void calculateHeuristicValue() {
		if (prune()) 
			heuristicValue = Integer.MAX_VALUE;
		else {
    		switch (heuristicType){
    		case Manhattan:
    			heuristicValue = getManhattanHeuristicValue();
    			break;
    		case WrongPlace:
    			heuristicValue = getWrongPlaceHeuristicValue();
    			break;
    		}
		} 	
    }
    
    /* To get the children of the current node. They 
     * point to their parent through the parentID */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
		int[] testBoard;
	    Puzzle temp;
	       
	    //Possible movements of the pieces towards the empty cell
	    testBoard = up(); //UP
	    temp = new Puzzle(testBoard, heuristicType, depth+1, hashCode());
	    result.add(temp);
	        
	    testBoard = down(); //DOWN
	    temp = new Puzzle(testBoard, heuristicType, depth+1, hashCode());
	    result.add(temp);
	        
	    testBoard = left(); //LEFT
	    temp = new Puzzle(testBoard, heuristicType, depth+1, hashCode());
	    result.add(temp);
	        
	    testBoard = right(); //RIGHT
	    temp = new Puzzle(testBoard, heuristicType, depth+1, hashCode());
	    result.add(temp);
	    return result;
	}

	@Override
	public boolean isSolution() {
		return (getHeuristicValue() == 0) ? true : false;
	}

} //Puzzle
/***************************************************/


/***************************************************/
enum HeuristicType {
	Manhattan,
	WrongPlace
}
/***************************************************/

