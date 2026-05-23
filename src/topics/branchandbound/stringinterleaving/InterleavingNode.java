package topics.branchandbound.stringinterleaving;

import java.util.ArrayList;
import java.util.List;
import topics.branchandbound.utils.Node;

/**
 * <h1>State Node for String Interleaving</h1>
 * <p>
 * Represents a single point in the state space tree. Since we are using a Priority Queue 
 * (Heap) in Branch and Bound, each node must be an immutable snapshot of the current 
 * interleaving process.
 * </p>
 *
 * @author vicegd
 */
public class InterleavingNode extends Node {
    private final String a;
    private final String b;
    private final String currentInterleaving;
    private final int posA;
    private final int posB;

    /**
     * Root Node Constructor.
     */
    public InterleavingNode(String a, String b) {
        this.a = a;
        this.b = b;
        this.currentInterleaving = "";
        this.posA = 0;
        this.posB = 0;
        calculateHeuristicValue();
    }

    /**
     * Child Node Constructor.
     */
    public InterleavingNode(InterleavingNode parent, String newInterleaving, int posA, int posB) {
        super();
        this.a = parent.a;
        this.b = parent.b;
        this.currentInterleaving = newInterleaving;
        this.posA = posA;
        this.posB = posB;
        
        this.depth = parent.depth + 1;
        this.parentId = parent.getId();
        calculateHeuristicValue();
    }

    /**
     * Heuristic: Distance to completion.
     * Since Heap extracts the minimum value first, negative values representing 
     * shallow nodes will be extracted first, forcing a Breadth-First Search (BFS) behavior.
     */
    @Override
    public void calculateHeuristicValue() {
        int targetLength = a.length() + b.length();
        // Starts at a negative value and approaches 0 as the string grows.
        this.heuristicValue = currentInterleaving.length() - targetLength;
    }

    @Override
    public ArrayList<Node> expand() {
        ArrayList<Node> children = new ArrayList<>();

        if (posA < a.length()) {
            String nextStr = currentInterleaving + a.charAt(posA);
            children.add(new InterleavingNode(this, nextStr, posA + 1, posB));
        }
        
        if (posB < b.length()) {
            String nextStr = currentInterleaving + b.charAt(posB);
            children.add(new InterleavingNode(this, nextStr, posA, posB + 1));
        }
        
        return children;
    }

    @Override
    public boolean isSolution() {
        // Cleaned up the redundant ternary operator
        return getHeuristicValue() == 0;
    }

    @Override
    public String toString() {
        return currentInterleaving;
    }
}