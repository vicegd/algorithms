package topics.branchandbound;

import java.util.List;
import topics.branchandbound.util.threads.BranchAndBoundThreads;

/**
 * <h1>Optimal Placement of Rectangles (Concurrent Execution)</h1>
 * <p>
 * This class solves the 2D bin packing variant where a given set of rectangular pieces 
 * must be placed on an <i>N &times; N</i> grid to minimize the bounding box area.
 * It extends the multithreaded <strong>Branch and Bound</strong> framework to evaluate 
 * multiple branches of the state space tree simultaneously, leveraging parallel 
 * execution for performance gains on multi-core architectures.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O((2 &times; N&sup2;)<sup>P</sup>)</code> - The theoretical worst-case remains exponential relative to the grid size <i>N</i> and pieces <i>P</i>. However, concurrent evaluation accelerates the discovery of tight upper bounds, triggering earlier and more aggressive global pruning across parallel threads.</li>
 * <li><strong>Space Complexity:</strong> <code>O(T &times; N&sup2;)</code> - Where <i>T</i> is the number of concurrent worker threads. Each thread requires isolated memory allocation to track its specific subset of board configurations during traversal.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RectanglesPlacementThreads extends BranchAndBoundThreads {
    
    /**
     * Initializes the concurrent problem solver and establishes the execution tree root.
     *
     * @param boardSize The dimension of the square board (N &times; N).
     * @param pieces    The collection of rectangular pieces to be optimally placed.
     */
    public RectanglesPlacementThreads(int boardSize, List<Piece> pieces) {
        // BoardState serves as the mathematical root node representing the empty grid
        rootNode = new BoardState(boardSize, pieces); 
    }
}