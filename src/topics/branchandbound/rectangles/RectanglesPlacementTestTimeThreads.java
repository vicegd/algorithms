package topics.branchandbound.rectangles;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.branchandbound.utils.threads.BranchAndBoundThreads;

/**
 * <h1>Concurrent Performance Benchmark (Rectangle Placement)</h1>
 * <p>
 * Standalone execution driver designed to benchmark the concurrent (multithreaded) 
 * performance of the Branch and Bound rectangle placement algorithm. It dispatches 
 * a specified pool of worker threads to navigate the state space in parallel and 
 * records the total elapsed wall-clock time.
 * </p>
 * <p>
 * This class serves as a direct comparative metric against the sequential 
 * execution baseline to evaluate hardware utilization and algorithmic speedup.
 * </p>
 *
 * @author vicegd
 * @see RectanglesPlacementTestTime
 */
public class RectanglesPlacementTestTimeThreads extends BranchAndBoundThreads {  
    private static final Logger log = LoggerFactory.getLogger(RectanglesPlacementTestTimeThreads.class);
  
    /**
     * Initializes the concurrent benchmark environment, defining the board dimensions 
     * and the specific set of rectangular pieces to be processed.
     */
    public RectanglesPlacementTestTimeThreads() {
        var pieces = List.of(
            new Piece(2, 5), 
            new Piece(1, 3), 
            new Piece(1, 5), 
            new Piece(3, 1), 
            new Piece(1, 1), 
            new Piece(2, 1)
        );
        
        // Initialize the execution tree root with a 7x7 board
        BranchAndBoundThreads.rootNode = new Game(7, pieces); 
    }
    
    /**
     * Main execution entry point. Triggers the multithreaded algorithm using 
     * a defined pool of worker threads and records the total elapsed execution time.
     *
     * @param args Command-line arguments (not utilized).
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        var problem = new RectanglesPlacementTestTimeThreads(); 
        // Dispatch the search across 5 concurrent worker threads
        problem.branchAndBound(problem.getRootNode(), 5); 
        
        long endTime = System.currentTimeMillis();
        
        log.debug("The execution WITH threads took {} milliseconds", (endTime - startTime));      
        problem.printSolutionTrace(); 
    }  
}