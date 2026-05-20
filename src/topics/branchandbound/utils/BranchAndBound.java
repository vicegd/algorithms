package topics.branchandbound.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Branch and Bound Execution Engine</h1>
 * <p>
 * An abstract base class that provides the core algorithmic framework for solving 
 * combinatorial optimization problems using the <strong>Branch and Bound</strong> paradigm.
 * </p>
 * <p>
 * This engine manages the state space tree explicitly using a custom priority queue 
 * (Heap) to implement a Best-First Search strategy. It actively tracks the global 
 * upper bound to prune sub-optimal branches systematically.
 * </p>
 *
 * @author vicegd
 */
public abstract class BranchAndBound {
    private static final Logger log = LoggerFactory.getLogger(BranchAndBound.class);
    
    /**
     * The priority queue managing the active, unexplored nodes in the state space tree.
     */
    protected Heap nodeHeap; 
    
    /**
     * The node representing the optimal valid configuration discovered during execution.
     */
    protected Node bestNode; 
    
    /**
     * The origin state of the problem.
     */
    protected Node rootNode; 
    
    /**
     * The global upper bound metric used to prune paths mathematically incapable 
     * of yielding a better outcome than the currently discovered optimal solution.
     */
    protected int globalUpperBound; 
          
    /**
     * Initializes the fundamental memory structures required for the execution engine.
     */
    protected BranchAndBound() {
        this.nodeHeap = new Heap(); 
    }
        
    /**
     * Executes the primary Branch and Bound systemic loop.
     * <p>
     * It continuously extracts the most promising state, expands its children, 
     * updates the optimal benchmark if a leaf solution is reached, and prunes 
     * branches that violate the bounding constraints.
     * </p>
     *
     * @param initialNode The starting mathematical state of the problem environment.
     */
    public void branchAndBound(Node initialNode) { 
        nodeHeap.insert(initialNode);
        globalUpperBound = initialNode.initialValuePruneLimit();

        while (!nodeHeap.empty() && nodeHeap.estimateBest() < globalUpperBound) {
            Node currentNode = nodeHeap.extractBestNode();  
            List<Node> children = currentNode.expand(); 
            
            for (Node child : children) {
                if (child.isSolution()) {
                    int solutionCost = child.getHeuristicValue();
                    
                    // Update the global benchmark if a superior complete solution is found
                    if (solutionCost < globalUpperBound) {
                        globalUpperBound = solutionCost;
                        bestNode = child;
                    } 
                } else if (child.getHeuristicValue() < globalUpperBound) {
                    // Retain the partial state if its theoretical lower bound is viable
                    nodeHeap.insert(child);
                }
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