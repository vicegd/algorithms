package topics.backtracking.paths;

/**
 * <h1>Shortest Simple Path (Un-pruned)</h1>
 * <p>
 * Extends the basic pathfinder to track and store the absolute lowest cost path.
 * </p>
 * <h2>Pedagogical Note</h2>
 * <p>
 * This problem (Shortest Path) is NOT NP-Hard. It can be solved in polynomial time 
 * using algorithms like Dijkstra (Greedy) or Floyd-Warshall (Dynamic Programming). 
 * We solve it here using Backtracking purely for educational comparative purposes.
 * </p>
 */
public class PathBest extends PathSimple {  
    /** Sequence of vertex indices corresponding to the minimum-cost simple path found so far. */
    protected final int[] bestPath;
    /** Minimum total edge weight among all complete simple paths discovered so far. */
    protected int bestCost;
    /** Number of edges in {@link #bestPath}. */
    protected int bestLength;
        
    /**
     * Initializes the shortest simple path solver for {@code n} vertices.
     *
     * @param n Number of vertices in the graph.
     */
    public PathBest(int n) {
        super(n);
        this.bestPath = new int[n];
        this.bestCost = Integer.MAX_VALUE;
    }
    
    @Override
    protected void backtrack(int current) {
        if (current == target) {
            nsol++;
            if (cost < bestCost) { // Overwrite global best if current is better
                System.arraycopy(path, 0, bestPath, 0, length + 1);
                bestCost = cost;
                bestLength = length;
            }
            return; 
        } 
        
        for (int j = 0; j < n; j++) {
            if (!mark[j] && weights[current][j] != -1) {
                mark[j] = true;
                path[++length] = j;
                cost += weights[current][j];
       
                backtrack(j);
       
                cost -= weights[current][j];
                length--;
                mark[j] = false;
            }
        }
    }
    
    public int getBestCost() { return bestCost; }
    
    public String getBestPath() {
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l <= bestLength; l++) {
            sb.append(nodes[bestPath[l]]).append("**");
        }
        return sb.toString();
    }
}