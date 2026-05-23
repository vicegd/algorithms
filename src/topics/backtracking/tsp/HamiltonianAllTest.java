package topics.backtracking.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Exhaustive Hamiltonian Cycles</h1>
 * <p>
 * Verifies that the brute-force search mathematically explores the entire 
 * state space, resulting in exactly (N-1)! cycles for a fully connected graph.
 * </p>
 */
@DisplayName("Hamiltonian Cycles (Exhaustive Search)")
class HamiltonianAllTest {

    @Test
    @DisplayName("Should find exactly (N-1)! unique Hamiltonian cycles in a complete graph")
    void testCompleteGraphPermutations() {
        int n = 7;
        int source = 0;
        int[][] w = generateCompleteGraph(n);

        HamiltonianAll engine = new HamiltonianAll(n, source, w);
        engine.backtracking();

        // Mathematical validation for N=7:
        // Expected = (7-1)! = 6! = 720
        assertEquals(720, engine.getNumberSolutions(), "The exhaustive search failed to traverse all permutations.");
    }

    /**
     * Generates a fully connected graph where every node connects to every other node.
     */
    private int[][] generateCompleteGraph(int n) {
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = (i == j) ? -1 : 10; // -1 for self-loops, 10 for any edge
            }
        }
        return w;
    }
}