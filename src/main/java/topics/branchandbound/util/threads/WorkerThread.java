package topics.branchandbound.util.threads;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topics.branchandbound.util.Node;

/**
 * Worker thread for the parallel Branch and Bound search.
 *
 * Each {@code WorkerThread} repeatedly pulls the most promising node from the
 * shared data structure, expands it, and updates the global pruning limit when
 * a better solution is found. Threads stop when the data structure is empty or
 * no node can improve the current best solution.
 *
 * @author vicegd
 * @see BranchAndBoundThreads
 */
public class WorkerThread extends Thread {
  private static Logger log = LoggerFactory.getLogger(WorkerThread.class);          
  
  @Override
  public void run() { 
    log.debug(Thread.currentThread().getName() + " - STARTING");
    long l1 = System.currentTimeMillis();
    
    while (!BranchAndBoundThreads.ds.empty() && BranchAndBoundThreads.ds.estimateBest() < BranchAndBoundThreads.pruneLimit) {
      Node node;
      node = BranchAndBoundThreads.ds.extractBestNode();  
      ArrayList<Node> children = node.expand();         

      for (Node child : children) 
        if (child.isSolution()) {
          int cost = child.getHeuristicValue();      
          synchronized(this) {
            if (cost < BranchAndBoundThreads.pruneLimit) {            
              BranchAndBoundThreads.pruneLimit = cost;
              BranchAndBoundThreads.bestNode = child;
            } 
          }
        }
        else           
          if (child.getHeuristicValue() < BranchAndBoundThreads.pruneLimit) {
            BranchAndBoundThreads.ds.insert(child);
          }
    } //while
    long l2 = System.currentTimeMillis();
    log.debug(Thread.currentThread().getName() + " - FINISHING AFTER " + (l2-l1) + " milliseconds");
  }
    
}
