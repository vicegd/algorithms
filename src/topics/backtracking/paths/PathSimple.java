package topics.backtracking.paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Simple Paths in a Graph</h1>
 * <p>
 * Finds ALL simple paths from a source node to a target node using Backtracking.
 * A simple path is a path that does not repeat any nodes.
 * </p>
 * <h2>Complexity</h2>
 * <p>
 * Since finding all simple paths between two nodes in a complete graph involves 
 * exploring all possible permutations of intermediate nodes, the worst-case 
 * time complexity is factorial: <b>O(N!)</b>.
 * </p>
 */
public class PathSimple {
    protected static final Logger log = LoggerFactory.getLogger(PathSimple.class);
    
    /** Total number of vertices in the directed graph. */
    protected final int n;
    /** Human-readable vertex labels (e.g., {@code "NODE0"}, {@code "NODE1"}, ...). */
    protected final String[] nodes;
    /** Adjacency matrix of edge weights ({@code -1} denotes no direct edge). */
    protected int[][] weights;
    
    /** Index of the starting vertex. */
    protected int source;
    /** Index of the destination vertex. */
    protected int target;
    
    /** Visited flag per vertex to prevent cycles along the current simple path. */
    protected boolean[] mark;
    /** Sequence of vertex indices forming the current partial path. */
    protected int[] path;
    /** Accumulated edge weight of the current partial path. */
    protected int cost = 0;
    /** Number of edges currently in {@link #path} (depth in the search tree). */
    protected int length = 0;
    /** Total number of valid simple paths discovered from {@link #source} to {@link #target}. */
    protected int nsol = 0;
        
    /**
     * Initializes the graph pathfinder for {@code n} vertices.
     *
     * @param n Number of vertices in the graph.
     */
    public PathSimple(int n) {
        this.n = n;
        this.nodes = new String[n];
        for (int i = 0; i < n; i++) {
            this.nodes[i] = "NODE" + i;
        }
        
        this.mark = new boolean[n];
        this.path = new int[n];
    }
    
    /**
     * Sets and marks the starting vertex as the first element of the path.
     *
     * @param source Index of the origin vertex ({@code 0 <= source < n}).
     */
    public void setSource(int source) { 
        this.source = source;
        this.mark[source] = true; 
        this.path[0] = source;
    }
    
    /**
     * Sets the destination vertex where valid simple paths terminate.
     *
     * @param target Index of the target vertex ({@code 0 <= target < n}).
     */
    public void setTarget(int target) {
        this.target = target;
    }
    
    /**
     * Configures the graph's adjacency weight matrix.
     *
     * @param weights 2D matrix where {@code weights[i][j]} is the cost from {@code i} to {@code j}, or {@code -1} if disconnected.
     */
    public void setWeightMatrix(int[][] weights) {
        this.weights = weights;
    }
    
    /**
     * Entry point that launches the recursive backtracking search from {@link #source}.
     */
    public void backtracking() {
        backtrack(source);
    }
    
    /**
     * Explores all unvisited neighbors of {@code current} using the classic
     * Choose &rarr; Explore &rarr; Un-choose backtracking pattern.
     *
     * @param current Vertex index currently at the tip of the partial path.
     */
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