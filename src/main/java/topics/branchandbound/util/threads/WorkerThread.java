package topics.branchandbound.util.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topics.branchandbound.util.Node;

/**
 * <h1>Concurrent Worker Thread</h1>
 * <p>
 * Represents an independent execution context within the multithreaded 
 * <strong>Branch and Bound</strong> algorithmic framework. Each worker actively 
 * competes to extract the most promising mathematical states from a shared 
 * priority queue, expands them, and evaluates the resulting topological branches.
 * </p>
 * <p>
 * The thread coordinates with its peers by referencing and updating a global 
 * upper bound constraint. If a worker discovers a superior optimal solution, 
 * it claims a global lock to update the benchmark, immediately accelerating 
 * the pruning operations of all other active threads.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(1)</code> per state extraction and evaluation block. The aggregate lifecycle time depends entirely on the size of the unpruned state space distributed across the thread pool.</li>
 * <li><strong>Space Complexity:</strong> <code>O(1)</code> auxiliary space per thread. The worker operates strictly on references to the globally managed heap and does not construct isolated data structures beyond transient local variables.</li>
 * </ul>
 *
 * @author vicegd
 * @see BranchAndBoundThreads
 */
public class WorkerThread extends Thread {
    private static final Logger log = LoggerFactory.getLogger(WorkerThread.class);          
  
    /**
     * Executes the continuous extraction and evaluation loop until the shared 
     * state space tree is exhausted or completely pruned.
     */
    @Override
    public void run() { 
        log.debug("{} - STARTING", Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();
    
        // Continuously poll the shared frontier as long as viable nodes exist
        while (!BranchAndBoundThreads.nodeHeap.isEmpty() && 
               BranchAndBoundThreads.nodeHeap.estimateBest() < BranchAndBoundThreads.globalUpperBound) {
            
            Node currentNode = BranchAndBoundThreads.nodeHeap.extractBestNode();  
            
            // Failsafe: Handles the race condition where the queue empties between the while-condition and extraction
            if (currentNode == null) {
                continue;
            }
            
            var children = currentNode.expand();        

            for (Node child : children) {
                if (child.isSolution()) {
                    int cost = child.getHeuristicValue();      
                    
                    // Global synchronization block: Ensures atomic updates to the shared optimal benchmark
                    synchronized (BranchAndBoundThreads.class) {
                        if (cost < BranchAndBoundThreads.globalUpperBound) {            
                            BranchAndBoundThreads.globalUpperBound = cost;
                            BranchAndBoundThreads.bestNode = child;
                        } 
                    }
                } else if (child.getHeuristicValue() < BranchAndBoundThreads.globalUpperBound) {
                    BranchAndBoundThreads.nodeHeap.insert(child);
                }
            }
        }
        
        long endTime = System.currentTimeMillis();
        log.debug("{} - FINISHING AFTER {} milliseconds", Thread.currentThread().getName(), (endTime - startTime));
    }
}