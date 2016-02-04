//BRANCH AND BOUND PROBLEM: OPTIMAL PLACEMENT OF RECTANGLES
package topics.branchandbound.times;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topics.branchandbound.util.threads.BranchAndBoundThreads;

class RectanglesPlacementTestTimeThreads extends BranchAndBoundThreads {  
	private static Logger log = LoggerFactory.getLogger(RectanglesPlacementTestTimeThreads.class);
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		
		RectanglesPlacementTestTimeThreads problem = new RectanglesPlacementTestTimeThreads(); 
		problem.branchAndBound(problem.getRootNode(), 5); 
		
		long t2 = System.currentTimeMillis();
		
		log.debug("The execution WITH threads took " + (t2-t1) + " milliseconds");			
		problem.printSolutionTrace(); //There is always a solution for this problem
	}  
	
	public RectanglesPlacementTestTimeThreads() {
        List<Piece> pieces = new ArrayList<Piece>();
        Piece p1 = new Piece(2, 5);
        Piece p2 = new Piece(1, 3);
        Piece p3 = new Piece(1, 5);
        Piece p4 = new Piece(3, 1);
        Piece p5 = new Piece(1, 1);
        Piece p6 = new Piece(2, 1);
        pieces.add(p1); pieces.add(p2); pieces.add(p3); pieces.add(p4); pieces.add(p5); pieces.add(p6);
	    rootNode = new Game(7, pieces); //We create the board to start playing
	}
}
/***************************************************/