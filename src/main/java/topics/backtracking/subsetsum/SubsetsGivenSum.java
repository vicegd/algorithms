package topics.backtracking.subsetsum;

import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Subset Sum</h1>
 * <p>
 * This class identifies all possible subsets of a given array of strictly positive 
 * integers whose elements sum up to a specific target value. It utilizes a 
 * <strong>Backtracking</strong> algorithm with an inclusion/exclusion branching model.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(2<sup>N</sup>)</code> - The state space tree models a binary choice (include or exclude) for each of the <i>N</i> elements. Pruning drastically reduces the physical paths explored.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Dictated by the memory required to maintain the boolean tracking array and the maximum depth of the JVM call stack.</li>
 * </ul>
 *
 * @author vicegd
 */
public class SubsetsGivenSum {
    private static final Logger log = LoggerFactory.getLogger(SubsetsGivenSum.class);

    private final int[] elements;
    private final int targetSum;
    private final boolean[] isElementIncluded;
    private int solutionCount;

    /**
     * Initializes the Subset Sum solver.
     *
     * @param elements  An array of positive, distinct integers representing the mathematical set.
     * @param targetSum The exact cumulative sum required for a subset to be considered a valid solution.
     */
    public SubsetsGivenSum(int[] elements, int targetSum) {
        this.elements = elements;
        this.targetSum = targetSum;
        this.isElementIncluded = new boolean[elements.length];
        this.solutionCount = 0;
    }

    /**
     * Triggers the backtracking execution to find all valid subsets.
     */
    public void solve() {
        backtrack(0, 0);
    }

    /**
     * The core recursive engine that evaluates inclusion and exclusion of set elements.
     *
     * @param currentIndex The current depth in the state space tree, pointing to the element under evaluation.
     * @param currentSum   The cumulative sum of all elements currently included in the active path.
     */
    private void backtrack(int currentIndex, int currentSum) {
        // Pruning: Since the domain consists strictly of positive integers, 
        // any path exceeding the target sum is mathematically a dead end.
        if (currentSum > targetSum) {
            return;
        }

        // Base Case: All elements have been evaluated.
        if (currentIndex == elements.length) {
            if (currentSum == targetSum) {
                solutionCount++;
                logSolution();
            }
            return;
        }

        // Branch 1: Include the current element in the subset
        isElementIncluded[currentIndex] = true;
        backtrack(currentIndex + 1, currentSum + elements[currentIndex]);

        // Branch 2: Exclude the current element from the subset (Rollback state)
        isElementIncluded[currentIndex] = false;
        backtrack(currentIndex + 1, currentSum);
    }

    /**
     * Formats and logs the elements forming the valid subset.
     * <p>
     * String construction is bypassed if debug logging is disabled to preserve CPU cycles.
     * </p>
     */
    private void logSolution() {
        if (!log.isDebugEnabled()) {
            return;
        }

        var joiner = new StringJoiner(" + ");
        for (int i = 0; i < elements.length; i++) {
            if (isElementIncluded[i]) {
                joiner.add(String.valueOf(elements[i]));
            }
        }
        
        log.debug("SUBSET SUMS {} = {}", targetSum, joiner);
    }

    /**
     * Retrieves the total count of valid subsets discovered by the algorithm.
     *
     * @return The integer count of valid solutions.
     */
    public int getSolutionCount() {
        return solutionCount;
    }
}