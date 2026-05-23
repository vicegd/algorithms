package topics.backtracking.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Hamiltonian Cycles</h1>
 * <p>
 * Verifies that the exhaustive search finds exactly (N-1)! solutions.
 * For N=7, expected solutions = (7-1)! = 6! = 720.
 * </p>
 */
@DisplayName("Hamiltonian Cycles (Exhaustive Backtracking)")
class HamiltonianAllTest {

    @Test
    @DisplayName("Should find (N-1)! unique Hamiltonian cycles for a complete graph")
    void shouldFindAllPermutations() {
        int n = 7;
        int[][] w = generateCompleteGraph(n);
        
        HamiltonianAll engine = new HamiltonianAll(n, 0, w);
        engine.solve();
        
        // Mathematical expectation for a complete graph: (N-1)!
        // (7-1)! = 6! = 720
        assertEquals(720, engine.getSolutionCount(), "The number of cycles found is incorrect.");
    }

    private int[][] generateCompleteGraph(int n) {
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = (i == j) ? -1 : 10; // Simple weight of 10 for completeness
            }
        }
        return w;
    }
}