package topics.backtracking.paths;

/**
 * <h1>Longest Simple Path</h1>
 * <p>
 * Calculates the highest cost simple path (the worst path) from source to target.
 * </p>
 * * <h2>Pedagogical Note</h2>
 * <p>
 * Finding the longest simple path in a graph with positive weights is an NP-Hard problem. 
 * Unlike the shortest path, we cannot easily prune branches here (Branch & Bound) because 
 * we never know if a currently "cheap" path might suddenly encounter a massive weight edge 
 * further down the tree. Therefore, we are forced to exhaustively evaluate O(N!) combinations.
 * </p>
 */
public class PathWorst extends PathSimple { 
    protected final int[] worstPath;
    protected int worstCost;
    protected int worstLength;
        
    public PathWorst(int n) {
        super(n);
        this.worstPath = new int[n];
        this.worstCost = Integer.MIN_VALUE;
    }
    
    @Override
    protected void backtrack(int current) {
        if (current == target) {
            nsol++;
            if (cost > worstCost) { // Overwrite if we found a MORE EXPENSIVE path
                System.arraycopy(path, 0, worstPath, 0, length + 1);
                worstCost = cost;
                worstLength = length;
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
    
    public int getWorstCost() { return worstCost; }
    
    public String getWorstPath() {
        StringBuilder sb = new StringBuilder();
        for (int l = 0; l <= worstLength; l++) {
            sb.append(nodes[worstPath[l]]).append("**");
        }
        return sb.toString();
    }
}