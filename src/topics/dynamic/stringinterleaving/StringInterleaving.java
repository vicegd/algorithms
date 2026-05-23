package topics.dynamic.stringinterleaving;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>String Interleaving</h1>
 * <p>
 * Checks if string C is formed by an interleaving of strings A and B.
 * Unlike the Greedy approach, Dynamic Programming guarantees the correct 
 * global optimum by exhaustively evaluating and memoizing all valid sub-paths.
 * </p>
 *
 * <h2>The DP State Matrix</h2>
 * <p>
 * <code>dp[i][j]</code> represents whether the first <code>i</code> characters of A 
 * and the first <code>j</code> characters of B can form the first <code>i + j</code> characters of C.
 * </p>
 *
 * <ul>
 * <li><strong>Time Complexity:</strong> O(N * M) - Where N and M are the lengths of strings A and B.</li>
 * <li><strong>Space Complexity:</strong> O(N * M) - To store the boolean evaluation matrix.</li>
 * </ul>
 *
 * @author vicegd
 */
public class StringInterleaving {
    private static final Logger log = LoggerFactory.getLogger(StringInterleaving.class);

    /**
     * Solves the interleaving problem using a bottom-up 2D tabulation.
     *
     * @param a The first source string.
     * @param b The second source string.
     * @param c The target string to check.
     * @return true if C is a valid interleaving of A and B.
     */
    public boolean isInterleaved(String a, String b, String c) {
        if (a == null || b == null || c == null) return false;

        int lenA = a.length();
        int lenB = b.length();

        // Fast-fail: Lengths must match perfectly
        if (lenA + lenB != c.length()) {
            return false;
        }

        // Matrix size is +1 to account for the "empty string" prefix state
        boolean[][] dp = new boolean[lenA + 1][lenB + 1];

        // Base case: Two empty strings naturally form an empty string
        dp[0][0] = true;

        // Initialize First Column (Using only string A to form C)
        for (int i = 1; i <= lenA; i++) {
            dp[i][0] = dp[i - 1][0] && (a.charAt(i - 1) == c.charAt(i - 1));
        }

        // Initialize First Row (Using only string B to form C)
        for (int j = 1; j <= lenB; j++) {
            dp[0][j] = dp[0][j - 1] && (b.charAt(j - 1) == c.charAt(j - 1));
        }

        // Fill the rest of the DP table
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                
                // Can we arrive at this state by taking a character from A?
                boolean matchA = dp[i - 1][j] && (a.charAt(i - 1) == c.charAt(i + j - 1));
                
                // Can we arrive at this state by taking a character from B?
                boolean matchB = dp[i][j - 1] && (b.charAt(j - 1) == c.charAt(i + j - 1));
                
                // If either path is valid, this state is valid
                dp[i][j] = matchA || matchB;
            }
        }

        if (log.isTraceEnabled()) {
            printTable(dp, a, b, c);
        }

        return dp[lenA][lenB];
    }

    /**
     * Pedagogical helper to visualize the internal DP evaluation matrix.
     */
    private void printTable(boolean[][] dp, String a, String b, String c) {
        StringBuilder sb = new StringBuilder("\n--- DP Evaluation Table for: ").append(c).append(" ---\n");
        
        sb.append(String.format("%5s", "")); // Corner space
        sb.append(String.format("%5s", "(-)")); // Empty string column
        for (int j = 0; j < b.length(); j++) {
            sb.append(String.format("%5c", b.charAt(j)));
        }
        sb.append("\n");

        for (int i = 0; i <= a.length(); i++) {
            // Row headers
            if (i == 0) sb.append(String.format("%5s", "(-)"));
            else sb.append(String.format("%5c", a.charAt(i - 1)));
            
            // Boolean values (T/F)
            for (int j = 0; j <= b.length(); j++) {
                sb.append(String.format("%5c", dp[i][j] ? 'T' : 'F'));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}