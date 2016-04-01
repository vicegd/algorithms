package topics.branchandbound;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Node;

/**
 * BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
 * @author viceg
 */
public class RectanglesPlacement extends BranchAndBound {    
	/**
	 * Constructor for RectanglesPlacement objects
	 * @param n Size of the board (n x n)
	 * @param pieces List of pieces to be placed on the board
	 */
	public RectanglesPlacement(int n, List<Piece> pieces) {
	    rootNode = new Game(n, pieces); //We create the board to start playing
	}
}
/***************************************************/


/***************************************************/
class Game extends Node {
    private int[][] board; //Board to place rectangles
    private List<Piece> pieces; //Pieces (rectangles) to be placed on the board

    public Game(int n, List<Piece> pieces) { //Generates a new board and pieces (to place them in the board in the best way possible) (ROOT NODE)
        board = new int[n][n]; //Size of the board n x n
        this.pieces = pieces;
    }

    public Game(int[][] board, List<Piece> pieces, int depth, UUID parentID) {
        this.board = board;
        this.pieces = pieces;
        this.depth = depth;
        this.parentID = parentID;
        calculateHeuristicValue();
    }

    private ArrayList<Object> placeNewPiece() {
        ArrayList<Object> boards = new ArrayList<Object>();
        
        for (int i=0; i<board.length; i++) {
        	for (int j=0; j<board.length; j++) {
                int[][] newBoard = tryPositionNewPiece(i, j, PieceOrientation.Horizontal);
                if (newBoard != null) boards.add(newBoard);

                newBoard = tryPositionNewPiece(i, j, PieceOrientation.Vertical);
                if (newBoard != null) boards.add(newBoard);
        	}
        }        
        return boards;
    }
    
    private int[][] tryPositionNewPiece(int x, int y, PieceOrientation orientation) {
    	int[][] newBoard = new int[board.length][board.length];
    	for (int i = 0; i < board.length; i++)
    		for (int j = 0; j < board.length; j++)
    			newBoard[i][j] = board[i][j]; //Copy the board in a new board for new nodes
    	
    	if (insertNewPiece(x, y, orientation, newBoard, pieces.get(getDepth())))
    			return newBoard;
	    else return null; //If we return null, then it is not a valid node
    }
    
    private boolean insertNewPiece(int x, int y, PieceOrientation orientation, int[][] newBoard, Piece piece) {
    	boolean result = false;
    	
    	int limitX = 0;
        int limitY = 0;
    	if (orientation == PieceOrientation.Horizontal) { //The default orientation
    		limitX = piece.x;
    		limitY = piece.y;
    	}
        else { //If the orientation is vertical we need to interchange the coordinates of the piece
        	limitX = piece.y;
        	limitY = piece.x;
        }
    	
    	//Check the size of the board
    	if ((x+limitX > newBoard.length)||(y+limitY > newBoard.length)) return false;
    	
    	//Check if the piece is on another piece
    	for (int i=x; i<x+limitX; i++) {
        	for (int j=y; j<y+limitY; j++) {
        		if (newBoard[i][j] != 0) return false;
        	}
        }	

    	//We need that the piece is next to another piece (but not on another piece)
    	if (depth == 0) result = true; //The first element does not need a neighbor
    	else { //If it is not the first element
	    	if (x+limitX < newBoard.length) //On the right
	    		for (int k = y; k < y+limitY; k++)
	    			if (newBoard[x+limitX][k] != 0) result = true; //We have a neighbor
	    	if (x != 0) //On the left
	    		for (int k = y; k < y+limitY; k++)
	    			if (newBoard[x-1][k] != 0) result = true; //We have a neighbor
	    	if (y != 0) //On the top
	    		for (int k = x; k < x+limitX; k++)
	    			if (newBoard[k][y-1] != 0) result = true; //We have a neighbor
	    	if (y+limitY < newBoard.length) //On the button
	    		for (int k = x; k < x+limitX; k++)
	    			if (newBoard[k][y+limitY] != 0) result = true; //We have a neighbor
    	}
    	
    	//We insert the piece on the board
    	for (int i=x; i<x+limitX; i++) {
        	for (int j=y; j<y+limitY; j++) {
                newBoard[i][j] = getDepth()+1;
        	}
        }
    	
    	return result;
    }

    @Override
    public String toString() {
    	StringBuffer sb = new StringBuffer("=============\n");
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++)
            	sb.append(board[i][j]);
            sb.append("\n");
        }
        sb.append("=============\n");
        return sb.toString();
    }
  
	@Override
	public void calculateHeuristicValue() {
    	int firstValueX = -1;
    	int firstValueY = -1;
    	int lastValueX = -1;
    	int lastValueY = -1;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
            	if ((firstValueY == -1)&&(board[i][j] != 0)) {
            		firstValueY = i;
            	}
            	if (board[i][j] != 0) {
            		lastValueY = i;
            	}
            }	
        }
        
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
            	if ((firstValueX == -1)&&(board[j][i] != 0)) {
            		firstValueX = i;
            	}
            	if (board[j][i] != 0) {
            		lastValueX = i;
            	}
            }	
        }
        heuristicValue = (Math.abs(lastValueX-firstValueX)+1)*(Math.abs(lastValueY-firstValueY)+1); //Area			
	}

    /* To get the children of the current node. They 
     * point to their parent through the parentID */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> result = new ArrayList<Node>();
        ArrayList<Object> boards = new ArrayList<Object>();
        Game temp;
        int[][] testBoard;
        
        //Possible positions for a new piece placed on the board
        boards = placeNewPiece(); //We could place the new piece in different locations, so for each location we have a new state
        for (Object board : boards) {
        	testBoard = (int[][])board;
            temp = new Game(testBoard, pieces, depth+1, this.getID()); //parentID = UUID of the previous node
            result.add(temp);
        }
        return result;
	}
	
	@Override
    public boolean isSolution() {
    	return (depth == pieces.size()) ? true : false; //We have a solution only when all the pieces are placed
    }

} //Game
/***************************************************/


/***************************************************/
class Piece {
	int x;
	int y;
	
	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
/***************************************************/


/***************************************************/
enum PieceOrientation {
	Horizontal,
	Vertical
}
/***************************************************/