package topics.greedy.stringinterleaving;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>String Interleaving</h1>
 * <p>
 * Checks if string C is formed by an interleaving of strings A and B. 
 * An interleaving maintains the relative order of characters from both original strings.
 * </p>
 *
 * <h2>The Greedy Trap</h2>
 * <p>
 * This Greedy algorithm uses two pointers. It works perfectly when the characters of A and B 
 * are distinct or when there is no ambiguity. However, <strong>it fails if both A and B share 
 * the same current character</strong>, because the greedy choice (always picking A first) 
 * might lead to a dead end, missing a valid interleaving path. 
 * <br>
 * <em>(A guaranteed solution for all cases requires Dynamic Programming O(N*M))</em>.
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O(N) where N is the length of string C.</li>
 * <li><strong>Space Complexity:</strong> O(1) using pointers (no extra memory).</li>
 * </ul>
 *
 * @author vicegd
 */
public class StringInterleaving {
    private static final Logger log = LoggerFactory.getLogger(StringInterleaving.class);

    /**
     * Attempts to verify interleaving using a purely greedy two-pointer strategy.
     *
     * @param a The first source string.
     * @param b The second source string.
     * @param c The target string to check.
     * @return true if the greedy strategy successfully verifies the interleaving.
     */
    public boolean isInterleaved(String a, String b, String c) {
        if (a == null || b == null || c == null) {
            return false;
        }

        // Fast-fail: If the total lengths don't match, it's mathematically impossible
        if (a.length() + b.length() != c.length()) {
            return false;
        }

        int posA = 0; // Pointer for string a
        int posB = 0; // Pointer for string b

        for (int posC = 0; posC < c.length(); posC++) {
            char currentChar = c.charAt(posC);

            // Greedily check string 'a' first (bounds checked natively without try-catch)
            if (posA < a.length() && a.charAt(posA) == currentChar) {
                posA++;
            } 
            // If 'a' doesn't match, check string 'b'
            else if (posB < b.length() && b.charAt(posB) == currentChar) {
                posB++;
            } 
            // If neither matches, the greedy path is blocked
            else {
                if (log.isTraceEnabled()) {
                    log.trace("Greedy failure at index {}. Character '{}' does not match A['{}'] or B['{}']", 
                              posC, currentChar, 
                              (posA < a.length() ? a.charAt(posA) : "EOF"), 
                              (posB < b.length() ? b.charAt(posB) : "EOF"));
                }
                return false;
            }
        }

        return true;
    }
}