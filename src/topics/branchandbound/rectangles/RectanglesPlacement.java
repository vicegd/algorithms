package topics.branchandbound.rectangles;

import java.util.List;

import topics.branchandbound.utils.BranchAndBound;

/**
 * <h1>Optimal Placement of Rectangles</h1>
 * <p>
 * This class solves a 2D bin packing variant where a given set of rectangular pieces 
 * must be placed on an <i>N &times; N</i> grid. The objective is to minimize the 
 * bounding box area of the placed pieces. It utilizes a <strong>Branch and Bound</strong> 
 * algorithm to systematically evaluate positions and orientations while pruning sub-optimal 
 * configurations.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O((2 &times; N&sup2;)<sup>P</sup>)</code> - Where <i>N</i> is the board dimension and <i>P</i> is the number of pieces. For each piece, the algorithm explores all grid cells in two orientations. Bounding heuristics heavily prune this theoretical worst-case.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N&sup2;)</code> per state node - Required for storing the 2D matrix representing the board configuration.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RectanglesPlacement extends BranchAndBound {

    /**
     * Initializes the problem solver and establishes the execution tree root.
     *
     * @param boardSize The dimension of the square board (N &times; N).
     * @param pieces    The collection of rectangular pieces to be placed.
     */
    public RectanglesPlacement(int boardSize, List<Piece> pieces) {
        rootNode = new BoardState(boardSize, pieces);
    }
}
