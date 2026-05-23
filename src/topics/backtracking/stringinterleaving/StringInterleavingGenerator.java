package topics.backtracking.stringinterleaving;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>String Interleaving Generator</h1>
 * <p>
 * Instead of verifying a single target string, this algorithm exhaustively generates 
 * EVERY possible valid interleaving of two strings, maintaining the relative order 
 * of their characters.
 * </p>
 *
 * <h2>The Backtracking Paradigm</h2>
 * <p>
 * Notice the core pattern inside the recursive method:
 * <ol>
 * <li><strong>CHOOSE:</strong> We append a character to our current path.</li>
 * <li><strong>EXPLORE:</strong> We recurse deeper into the tree.</li>
 * <li><strong>UN-CHOOSE:</strong> We delete the character to backtrack and try a parallel branch.</li>
 * </ol>
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O( (N+M)! / (N! * M!) ) - Combinatorial explosion.</li>
 * <li><strong>Space Complexity:</strong> O(N+M) for the recursion stack and string builder.</li>
 * </ul>
 *
 * @author vicegd
 */
public class StringInterleavingGenerator {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingGenerator.class);

    /**
     * Generates all possible interleavings of two strings.
     *
     * @param a The first source string.
     * @param b The second source string.
     * @return A list containing all valid interleavings.
     */
    public List<String> generateAllInterleavings(String a, String b) {
        List<String> solutions = new ArrayList<>();
        
        if (a == null || b == null) {
            return solutions;
        }

        backtrack(a, b, 0, 0, new StringBuilder(), solutions);
        
        if (log.isTraceEnabled()) {
            log.trace("Generation complete. Total unique interleavings generated: {}", solutions.size());
        }
        
        return solutions;
    }

    /**
     * Internal recursive backtracking engine.
     */
    private void backtrack(String a, String b, int posA, int posB, StringBuilder currentPath, List<String> solutions) {
        // Base Case (Leaf Node): We have exhausted both strings.
        if (posA == a.length() && posB == b.length()) {
            solutions.add(currentPath.toString());
            return;
        }

        // Branch 1: Take a character from string 'A'
        if (posA < a.length()) {
            // 1. CHOOSE
            currentPath.append(a.charAt(posA));
            // 2. EXPLORE
            backtrack(a, b, posA + 1, posB, currentPath, solutions);
            // 3. UN-CHOOSE (Backtrack)
            currentPath.deleteCharAt(currentPath.length() - 1);
        }

        // Branch 2: Take a character from string 'B'
        if (posB < b.length()) {
            // 1. CHOOSE
            currentPath.append(b.charAt(posB));
            // 2. EXPLORE
            backtrack(a, b, posA, posB + 1, currentPath, solutions);
            // 3. UN-CHOOSE (Backtrack)
            currentPath.deleteCharAt(currentPath.length() - 1);
        }
    }
}