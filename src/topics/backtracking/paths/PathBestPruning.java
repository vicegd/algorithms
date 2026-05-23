package topics.backtracking.paths;

/**
 * <h1>Shortest Simple Path (Branch & Bound)</h1>
 * <p>
 * Optimizes the <code>PathBest</code> search space by immediately discarding (pruning) 
 * branches that have already exceeded the current <code>bestCost</code>.
 * </p>
 */
public class PathBestPruning extends PathBest {     
    
    public PathBestPruning(int n) {
        super(n);
    }
    
    @Override
    protected void backtrack(int current) {
        if (current == target) {
            nsol++;
            if (cost < bestCost) {
                System.arraycopy(path, 0, bestPath, 0, length + 1);
                bestCost = cost;
                bestLength = length;
            }
            return;
        } 
        
        for (int j = 0; j < n; j++) {
            // PRUNING: Only explore if current cost is strictly less than bestCost
            if (!mark[j] && weights[current][j] != -1 && cost + weights[current][j] < bestCost) { 
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
}