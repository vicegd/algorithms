package topics.backtracking.paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Simple Paths in a Graph</h1>
 * <p>
 * Finds ALL simple paths from a source node to a target node using Backtracking.
 * A simple path is a path that does not repeat any nodes.
 * </p>
 * * <h2>Complexity</h2>
 * <p>
 * Since finding all simple paths between two nodes in a complete graph involves 
 * exploring all possible permutations of intermediate nodes, the worst-case 
 * time complexity is factorial: <b>O(N!)</b>.
 * </p>
 */
public class PathSimple {
    protected static final Logger log = LoggerFactory.getLogger(PathSimple.class);
    
    protected final int n;
    protected final String[] nodes;
    protected int[][] weights;
    
    protected int source;
    protected int target;
    
    protected boolean[] mark;
    protected int[] path;
    protected int cost = 0;
    protected int length = 0;
    protected int nsol = 0;
        
    public PathSimple(int n) {
        this.n = n;
        this.nodes = new String[n];
        for (int i = 0; i < n; i++) {
            this.nodes[i] = "NODE" + i;
        }
        
        this.mark = new boolean[n];
        this.path = new int[n];
    }
    
    public void setSource(int source) { 
        this.source = source;
        this.mark[source] = true; 
        this.path[0] = source;
    }
    
    public void setTarget(int target) {
        this.target = target;
    }
    
    public void setWeightMatrix(int[][] weights) {
        this.weights = weights;
    }
    
    public void backtracking() {
        backtrack(source);
    }
    
    protected void backtrack(int current) {
        if (current == target) {
            nsol++;
            if (log.isTraceEnabled()) {
                log.trace("Path found: {} | Cost: {}", getPathString(length), cost);
            }
            return; // We stop here because a simple path cannot continue and return to target again
        } 
        
        for (int j = 0; j < n; j++) {
            if (!mark[j] && weights[current][j] != -1) {
                // 1. Choose
                mark[j] = true;
                path[++length] = j;
                cost += weights[current][j];
       
                // 2. Explore
                backtrack(j);
       
                // 3. Un-choose
                cost -= weights[current][j];
                length--;
                mark[j] = false;
            }
        }
    }
    
    protected String getPathString(int upToLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= upToLength; i++) {
            sb.append(nodes[path[i]]).append("**");
        }
        return sb.toString();
    }
    
    public int getNumberSolutions() { return nsol; }
    
    public String writeWeights() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%5d", weights[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}