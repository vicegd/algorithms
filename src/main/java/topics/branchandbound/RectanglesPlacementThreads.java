package topics.branchandbound;

import java.util.List;

import topics.branchandbound.util.threads.BranchAndBoundThreads;

/**
 * BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
 * @author viceg
 */
public class RectanglesPlacementThreads extends BranchAndBoundThreads {    
	/**
	 * Constructor for RectanglesPlacement objects
	 * @param n Size of the board (n x n)
	 * @param pieces List of pieces to be placed on the board
	 */
	public RectanglesPlacementThreads(int n, List<Piece> pieces) {
	    rootNode = new Game(n, pieces); //We create the board to start playing
	}
}
/***************************************************/