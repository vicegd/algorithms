package topics.branchandbound.utils.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

import topics.branchandbound.utils.Node;

/**
 * <h1>Concurrent State Space Queue (Heap)</h1>
 * <p>
 * Manages the frontier of unexplored nodes during a multithreaded 
 * <strong>Branch and Bound</strong> execution. This data structure ensures thread-safe 
 * prioritization, extraction, and duplicate-state detection across parallel workers.
 * </p>
 * * <h2>Architecture & Complexity</h2>
 * <ul>
 * <li><strong>Priority Queue:</strong> Utilizes a <code>PriorityBlockingQueue</code> to maintain the Best-First Search ordering concurrently. Extraction is <code>O(log N)</code>.</li>
 * <li><strong>Lineage Tracking:</strong> Employs a <code>ConcurrentMap</code> to link extracted states back to their parents for final path reconstruction. Lookup is <code>O(1)</code>.</li>
 * <li><strong>Duplicate Pruning:</strong> Integrates a concurrent <code>Set</code> to track topologically equivalent states, preventing redundant exploration loops in <code>O(1)</code> time.</li>
 * </ul>
 *
 * @author vicegd
 */
public class HeapThreads {
    
    /**
     * The thread-safe priority queue holding nodes awaiting exploration.
     */
    private final PriorityBlockingQueue<Node> activeNodes;
    
    /**
     * A concurrent registry linking a node's unique ID to the node instance, 
     * used exclusively to reconstruct the solution lineage.
     */
    private final ConcurrentMap<UUID, Node> lineageRegistry;
    
    /**
     * A thread-safe set tracking the topological signatures of explored nodes 
     * to perform O(1) duplicate pruning and prevent infinite cycles.
     */
    private final Set<Node> exploredStates;

    /**
     * Initializes the concurrent collections required to manage the state space.
     */
    public HeapThreads() {
        this.activeNodes = new PriorityBlockingQueue<>();
        this.lineageRegistry = new ConcurrentHashMap<>();
        this.exploredStates = ConcurrentHashMap.newKeySet();
    }

    /**
     * Flushes all active nodes from the priority queue. 
     * <p>
     * Note: This does not clear the lineage or explored states registries, 
     * preserving the historical context of the execution.
     * </p>
     */
    public void clear() {
        activeNodes.clear();
    }

    /**
     * Safely inserts a new node into the priority queue if it has not been 
     * explored previously.
     *
     * @param node The mathematical state node to be evaluated for insertion.
     */
    public void insert(Node node) {
        // O(1) topological duplicate check to prevent redundant cycles
        if (!exploredStates.contains(node)) { 
            activeNodes.add(node);
        }
    }

    /**
     * Evaluates whether the active frontier contains any pending nodes.
     *
     * @return <code>true</code> if the priority queue is empty; <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return activeNodes.isEmpty();
    }

    /**
     * Peeks at the most promising active node to estimate the current best 
     * theoretical outcome without removing it from the queue.
     *
     * @return The heuristic value of the highest-priority node, or 
     * <code>Integer.MAX_VALUE</code> if the queue is temporarily empty.
     */
    public int estimateBest() {
        Node bestCandidate = activeNodes.peek();
        return bestCandidate != null ? bestCandidate.getHeuristicValue() : Integer.MAX_VALUE;
    }

    /**
     * Thread-safe retrieval and removal of the highest-priority node from the frontier.
     * Registers the extracted node in the lineage and explored-state trackers.
     *
     * @return The optimal pending node, or <code>null</code> if the queue is empty.
     */
    public Node extractBestNode() {
        Node optimalNode = activeNodes.poll();
        
        if (optimalNode != null) {
            lineageRegistry.put(optimalNode.getId(), optimalNode);
            exploredStates.add(optimalNode);
        }
        
        return optimalNode;
    }

    /**
     * Traces the ancestral lineage of a specific node back to the root of the 
     * execution tree.
     *
     * @param targetNode The terminal node (typically the optimal solution leaf).
     * @return A chronologically ordered list representing the path from the 
     * target node up to the root node.
     */
    public List<Node> extractUsedNodesFrom(Node targetNode) {
        if (targetNode == null) {
            return Collections.emptyList();
        }

        var pathLineage = new ArrayList<Node>();
        pathLineage.add(targetNode);
        
        UUID currentParentId = targetNode.getParentId();

        while (currentParentId != null) {
            Node parentNode = lineageRegistry.get(currentParentId);
            if (parentNode != null) {
                pathLineage.add(parentNode);
                currentParentId = parentNode.getParentId();
            } else {
                // Failsafe break if lineage is broken or node was purged
                break; 
            }
        }
              
        return pathLineage;
    }
}