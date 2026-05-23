package topics.divideconquer.stringinterleaving;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>String Interleaving</h1>
 * <p>
 * Checks if string C is formed by an interleaving of strings A and B by recursively 
 * breaking the problem down into smaller sub-problems (advancing character pointers).
 * </p>
 *
 * <h2>Pedagogical Value</h2>
 * <p>
 * This pure recursive approach acts as the mathematical bridge between Greedy and Dynamic Programming:
 * <ul>
 * <li>Unlike <strong>Greedy</strong>, it does not get trapped by ambiguity. If characters from A and B 
 * both match C, it branches and explores both possibilities.</li>
 * <li>Unlike <strong>Dynamic Programming</strong>, it does not memorize past results. This leads to 
 * overlapping sub-problems being recalculated multiple times.</li>
 * </ul>
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O(2^(N+M)) in the worst case (Exponential due to branching).</li>
 * <li><strong>Space Complexity:</strong> O(N+M) - Bounded by the maximum depth of the call stack.</li>
 * </ul>
 *
 * @author vicegd
 */
public class StringInterleaving {
    private static final Logger log = LoggerFactory.getLogger(StringInterleaving.class);

    /**
     * Public wrapper method to initialize the recursion safely.
     *
     * @param a The first source string.
     * @param b The second source string.
     * @param c The target string to check.
     * @return true if C is a valid interleaving of A and B.
     */
    public boolean isInterleaved(String a, String b, String c) {
        if (a == null || b == null || c == null) return false;

        // Fast-fail: If the total lengths don't match, an interleaving is mathematically impossible.
        if (a.length() + b.length() != c.length()) {
            return false;
        }

        return solveRecursively(a, b, c, 0, 0, 0);
    }

    /**
     * Internal recursive core that explores all valid interleaving paths.
     */
    private boolean solveRecursively(String a, String b, String c, int posA, int posB, int posC) {
        // Base case: If we successfully matched every character up to the end of string C
        if (posC == c.length()) {
            return true;
        }

        if (log.isTraceEnabled()) {
            log.trace("Evaluating recursion state -> posA: {}, posB: {}, posC: {}", posA, posB, posC);
        }

        // Branch 1: Try matching the current character of C with the current character of A
        boolean matchA = (posA < a.length()) 
                         && (a.charAt(posA) == c.charAt(posC)) 
                         && solveRecursively(a, b, c, posA + 1, posB, posC + 1);

        // Short-circuit: If branching down 'A' resulted in success, there is no need to explore 'B'
        if (matchA) {
            return true;
        }

        // Branch 2: Try matching the current character of C with the current character of B
        boolean matchB = (posB < b.length()) 
                         && (b.charAt(posB) == c.charAt(posC)) 
                         && solveRecursively(a, b, c, posA, posB + 1, posC + 1);

        return matchB;
    }
}