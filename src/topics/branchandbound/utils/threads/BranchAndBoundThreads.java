package topics.branchandbound.utils.threads;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.branchandbound.utils.Node;

/**
 * <h1>Concurrent Branch and Bound Execution Engine</h1>
 * <p>
 * An abstract base class providing the core algorithmic framework for solving 
 * combinatorial optimization problems using a multithreaded <strong>Branch and Bound</strong> paradigm.
 * </p>
 * <p>
 * This engine manages the state space tree explicitly using a concurrent priority queue.
 * It dispatches a specified number of worker threads that collaboratively explore the 
 * state space, sharing a globally visible upper bound to systematically prune 
 * sub-optimal branches across all execution contexts.
 * </p>
 *
 * @author vicegd
 */
public abstract class BranchAndBoundThreads {
    private static final Logger log = LoggerFactory.getLogger(BranchAndBoundThreads.class);
    
    /**
     * The concurrent priority queue managing the active, unexplored nodes in the state space tree.
     */
    protected static HeapThreads nodeHeap; 
    
    /**
     * The node representing the optimal valid configuration discovered globally across all threads.
     * Marked as volatile to ensure immediate visibility across CPU caches.
     */
    protected static volatile Node bestNode; 
    
    /**
     * The origin state of the problem environment.
     */
    protected static Node rootNode; 
    
    /**
     * The global upper bound metric used to prune paths mathematically incapable 
     * of yielding a better outcome. Marked as volatile to guarantee thread-safe visibility.
     */
    protected static volatile int globalUpperBound; 
          
    /**
     * Initializes the concurrent memory structures required for the execution engine.
     */
    protected BranchAndBoundThreads() {
        nodeHeap = new HeapThreads(); 
    }
        
    /**
     * Executes the multithreaded Branch and Bound systemic loop.
     *
     * @param initialNode     The starting mathematical state of the problem environment.
     * @param numberOfThreads The total number of concurrent worker threads to dispatch.
     */
    public void branchAndBound(Node initialNode, int numberOfThreads) { 
        nodeHeap.insert(initialNode);
        
        rootNode = initialNode;
        globalUpperBound = initialNode.initialValuePruneLimit();
        
        var workers = new ArrayList<WorkerThread>();

        for (int i = 0; i < numberOfThreads; i++) {
            workers.add(new WorkerThread());
        }
        
        // Dispatch workers with a slight execution stagger to prevent simultaneous 
        // starvation on the initially shallow state space tree.
        for (var worker : workers) {
            worker.start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread dispatch interrupted", e);
            }
        }
        
        // Synchronize the main execution thread until all workers have exhausted the state space
        for (var worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread join interrupted", e);
            }
        }
    }
    
    /**
     * Retrieves the foundational starting state of the problem.
     *
     * @return The root node of the execution tree.
     */
    public Node getRootNode() {
        return rootNode;
    }
    
    /**
     * Retrieves the node encapsulating the mathematically optimal path or configuration.
     *
     * @return The best discovered node, or <code>null</code> if no valid solution exists.
     */
    public Node getBestNode() {
        return bestNode;
    }

    /**
     * Extracts and logs the complete topological lineage of the optimal path, 
     * detailing every state transition from the root node to the final solution leaf.
     */
    public void printSolutionTrace() {
        if (bestNode == null) {
            log.debug("Original State:");
            log.debug(rootNode != null ? rootNode.toString() : "Undefined Root");
            log.debug("THERE IS NO VALID SOLUTION");
            return;
        } 
        
        List<Node> pathLineage = nodeHeap.extractUsedNodesFrom(bestNode);

        // Iterate backwards through the lineage list to print chronologically from root to leaf
        for (int i = 0; i < pathLineage.size(); i++) {
            Node chronologicalStep = pathLineage.get(pathLineage.size() - 1 - i);
            
            if (i == 0) {
                log.debug("Original State:");
            } else {
                log.debug("Step {}:", i);
            }
            log.debug("\n{}", chronologicalStep);
        }
        
        log.debug("\nSolution reached in {} step(s).", bestNode.getDepth());  
    }
}