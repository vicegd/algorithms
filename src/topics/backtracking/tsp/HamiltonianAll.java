package topics.backtracking.tsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

/**
 * <h1>Exhaustive Search for Hamiltonian Cycles</h1>
 * <p>
 * This class implements a brute-force approach to find all simple cycles that visit every node 
 * exactly once (Hamiltonian Cycles).
 * </p>
 * * <h2>Complexity Analysis</h2>
 * <ul>
 * <li><b>Time Complexity:</b> O((N-1)!), where N is the number of nodes. This is due to the 
 * permutations of nodes to visit.</li>
 * <li><b>Space Complexity:</b> O(N) for the recursion stack and the path array.</li>
 * </ul>
 * * @author vicegd
 */
public class HamiltonianAll {
    protected static final Logger log = LoggerFactory.getLogger(HamiltonianAll.class);
    
    protected final int n;
    protected final int[][] weights;
    protected final int source;
    protected final boolean[] mark; // Visited nodes tracker
    protected final int[] path;     // Current path sequence
    
    protected int length = 0; // Number of steps taken
    protected int cost = 0;   // Current accumulated cost
    protected int nsol = 0;   // Total cycles found

    public HamiltonianAll(int n, int source, int[][] weights) {
        this.n = n;
        this.source = source;
        this.weights = (weights != null) ? weights : generateRandomWeights(n);
        this.mark = new boolean[n];
        this.path = new int[n + 1];
        
        // Initial state
        this.path[0] = source;
        this.mark[source] = true;
    }

    public void backtracking() {
        backtrack(source);
    }

    /**
     * Core backtracking logic following the Choose-Explore-Unchoose paradigm.
     * @param current The current node being visited.
     */
    protected void backtrack(int current) {
        // Base case: If path length is N-1, we have visited all nodes.
        // We only need to check if we can return to the source.
        if (length == n - 1) {
            if (weights[current][source] != -1) {
                nsol++;
                log.trace("Hamiltonian cycle found. Cost: {}", cost + weights[current][source]);
            }
            return;
        }

        // Recursive step: Explore every unvisited neighbor
        for (int nextNode = 0; nextNode < n; nextNode++) {
            if (!mark[nextNode] && weights[current][nextNode] != -1) {
                // 1. CHOOSE
                mark[nextNode] = true;
                path[++length] = nextNode;
                cost += weights[current][nextNode];

                // 2. EXPLORE
                backtrack(nextNode);

                // 3. UN-CHOOSE (Backtrack)
                cost -= weights[current][nextNode];
                length--;
                mark[nextNode] = false;
            }
        }
    }

    private int[][] generateRandomWeights(int n) {
        int[][] w = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                w[i][j] = w[j][i] = r.nextInt(99) + 1;
            }
            w[i][i] = -1; // No self-loops
        }
        return w;
    }

    public int getNumberSolutions() { return nsol; }
}