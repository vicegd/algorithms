package topics.backtracking.paths;

/**
 * <h1>Paths with an Exact Threshold Cost</h1>
 * <p>
 * This class calculates ALL paths (including non-simple paths/cycles) from a source 
 * to a target node that match an exact cost threshold. 
 * </p>
 * * <h2>Pedagogical Note</h2>
 * <p>
 * Notice that we intentionally ignore the <code>mark[]</code> array in the recursive step.
 * This allows the algorithm to revisit nodes (looping in cycles) as long as the 
 * accumulated cost is strictly less than the threshold and the length boundary is respected.
 * </p>
 */
public class PathAll extends PathSimple {
    protected final int threshold;
        
    public PathAll(int n, int threshold) {
        super(n);
        this.threshold = threshold;     
        this.path = new int[10 * n]; // Expanded array to allow long, non-simple looping paths
    }
    
    @Override
    public void setSource(int source) { 
        super.setSource(source);    
        this.path = new int[10 * n]; // Re-initialize to ensure capacity
        this.path[0] = source;
    }
        
    @Override
    protected void backtrack(int current) {
        // Solution state: Target reached AND exact cost matched
        if (current == target && cost == threshold) {
            nsol++;
            if (log.isTraceEnabled()) {
                log.trace("Threshold path found: {} | Exact Cost: {}", getPathString(length), cost);
            }
            // We do NOT return here because we might loop out and back for the same cost 
            // if zero-cost cycles existed (though weights are usually positive).
        } 
        
        for (int j = 0; j < n; j++) {
            // Notice the absence of "!mark[j]". We allow revisiting nodes!
            // Pruning heuristic: Only proceed if we haven't exceeded max length OR the target cost.
            if (weights[current][j] != -1 && length < (10 * n) - 1 && cost < threshold) {
                // Choose
                path[++length] = j;
                cost += weights[current][j];
       
                // Explore
                backtrack(j);
       
                // Un-choose
                cost -= weights[current][j];
                length--;
            }
        }
    }
}