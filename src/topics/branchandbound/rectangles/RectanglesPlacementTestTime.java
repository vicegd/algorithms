package topics.branchandbound.rectangles;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.branchandbound.utils.BranchAndBound;

/**
 * <h1>Sequential Performance Benchmark (Rectangle Placement)</h1>
 * <p>
 * Standalone execution driver designed to benchmark the sequential (single-threaded) 
 * performance of the Branch and Bound rectangle placement algorithm. It measures 
 * the total wall-clock time required to navigate the state space and discover 
 * the optimal combinatorial configuration.
 * </p>
 * <p>
 * This class serves as a computational baseline to evaluate the speedup and 
 * efficiency gains achieved by parallel execution models.
 * </p>
 *
 * @author vicegd
 * @see RectanglesPlacementTestTimeThreads
 */
public class RectanglesPlacementTestTime extends BranchAndBound { 
    private static final Logger log = LoggerFactory.getLogger(RectanglesPlacementTestTime.class);
    
    /**
     * Initializes the benchmark environment, defining the board dimensions 
     * and the specific set of rectangular pieces to be processed.
     */
    public RectanglesPlacementTestTime() {
        var pieces = List.of(
            new Piece(2, 5), 
            new Piece(1, 3), 
            new Piece(1, 5), 
            new Piece(3, 1), 
            new Piece(1, 1), 
            new Piece(2, 1)
        );
        
        // Initialize the execution tree root with a 7x7 board
        this.rootNode = new Game(7, pieces); 
    }
    
    /**
     * Main execution entry point. Triggers the sequential algorithm and 
     * records the total elapsed execution time.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        var problem = new RectanglesPlacementTestTime(); 
        problem.branchAndBound(problem.getRootNode()); 
        
        long endTime = System.currentTimeMillis();
        
        log.debug("The execution WITHOUT threads took {} milliseconds", (endTime - startTime));      
        problem.printSolutionTrace(); 
    }
}