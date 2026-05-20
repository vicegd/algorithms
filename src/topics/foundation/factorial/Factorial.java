package topics.foundation.factorial;

/**
 * <h1>Factorial Computation</h1>
 * <p>
 * Evaluates the factorial of a given integer using a recursive mathematical 
 * strategy. This class provides both a robust implementation with strict 
 * boundary validation and an unsafe variant to demonstrate recursive vulnerability.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Requires <i>N</i> consecutive recursive calls to reach the foundational base case.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Utilizes <i>N</i> discrete stack frames during the recursive descent, scaling linearly with the input.</li>
 * </ul>
 *
 * @author vicegd
 */
public class Factorial {

    /**
     * Computes the factorial recursively with strict negative boundary validation.
     *
     * @param target The target integer for the factorial calculation.
     * @return The computed mathematical factorial, or <code>-1</code> if the input violates geometric constraints (i.e., is negative).
     */
    public long computeSafe(int target) {
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return 1;
        }
        return target * computeSafe(target - 1);
    }

    /**
     * Computes the factorial recursively without validating boundaries.
     * <p>
     * <strong>Structural Warning:</strong> Supplying a negative integer to this 
     * method will entirely bypass the base case, triggering an infinite recursive 
     * loop and culminating in a {@link StackOverflowError}.
     * </p>
     *
     * @param target The target integer for the factorial calculation.
     * @return The computed mathematical factorial.
     */
    public long computeUnsafe(int target) {
        if (target == 0) {
            return 1;
        }
        return target * computeUnsafe(target - 1);
    }
}