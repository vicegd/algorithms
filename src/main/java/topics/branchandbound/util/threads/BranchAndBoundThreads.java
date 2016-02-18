package topics.branchandbound.util.threads;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import topics.branchandbound.util.Node;

/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 * @author viceg
 */
public class BranchAndBoundThreads {
	private static Logger log = LoggerFactory.getLogger(BranchAndBoundThreads.class);
	protected static HeapThreads ds; //Nodes to be explored (not used nodes)
	protected static Node bestNode; //To save the final node of the best solution
	protected static Node rootNode; //Initial node
	protected static int pruneLimit; //To prune nodes above this value
	       
	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBoundThreads() {
		ds = new HeapThreads(); //We create an instance of the Heap class to save the nodes
	}
	      
	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 * @param numberOfThreads Number of threads used 
	 */
	public void branchAndBound(Node rootNode, int numberOfThreads) { 
		ds.insert(rootNode); //First node to be explored
		
		pruneLimit = rootNode.initialValuePruneLimit();
		
		List<WorkerThread> threads = new ArrayList<WorkerThread>();

		for (int i = 0; i < numberOfThreads; i++) {
			WorkerThread w = new WorkerThread();
			threads.add(w);
		}
		
		for (WorkerThread w : threads) {
			w.start();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (WorkerThread w : threads) {
			try {
				w.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    /**
     * Prints the solution from the root node to the best node
     */
    public void printSolutionTrace() {
    	if (bestNode == null) {
			log.debug("Original:");
			log.debug(rootNode.toString());
    		log.debug("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);

            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				log.debug("Original:");
    			else 
    				log.debug("Step " + i + ":");
				log.debug(result.get(result.size()-i-1).toString());
    	    }
            log.debug("\nSolution with " + bestNode.getDepth() + " step(s).");	
    	}
    }
}
