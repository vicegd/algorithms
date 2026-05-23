package topics.branchandbound.stringinterleaving;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topics.branchandbound.utils.BranchAndBound;
import topics.branchandbound.utils.Node;
import topics.branchandbound.utils.Heap;

/**
 * <h1>String Interleaving Generator</h1>
 * <p>
 * Uses the architecture of Branch and Bound to explore the state space tree.
 * However, because the goal is <strong>GENERATION</strong> (finding ALL solutions) 
 * rather than <strong>OPTIMIZATION</strong> (finding the BEST solution), the pruning 
 * mechanism is intentionally bypassed.
 * </p>
 *
 * @author vicegd
 */
public class StringInterleavingGenerator extends BranchAndBound {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingGenerator.class);
    private int solutionCount;

    public StringInterleavingGenerator(String a, String b, Heap customHeap) {
        if (customHeap != null) {
            this.nodeHeap = customHeap;
        }
        this.rootNode = new InterleavingNode(a, b);
        this.solutionCount = 0;
    }

    @Override
    public void branchAndBound(Node rootNode) {
        nodeHeap.insert(rootNode); 
        globalUpperBound = rootNode.initialValuePruneLimit();

        while (!nodeHeap.empty() && nodeHeap.estimateBest() < globalUpperBound) {
            Node node = nodeHeap.extractBestNode();
            List<Node> children = node.expand();

            for (Node child : children) {
                if (child.isSolution()) {
                    int cost = child.getHeuristicValue();
                    
                    if (cost < globalUpperBound) {
                        // EDUCATIONAL HACK: 
                        // We DO NOT update globalUpperBound = cost. 
                        // Updating it would cut off other valid combinations!
                        solutionCount++; 
                        bestNode = child;
                        
                        if (log.isTraceEnabled()) {
                            log.trace("Found combination: {}", child.toString());
                        }
                    }
                } else if (child.getHeuristicValue() < globalUpperBound) {
                    nodeHeap.insert(child);
                }
            }
        }
    }

    public int getSolutionCount() {
        return solutionCount;
    }
}