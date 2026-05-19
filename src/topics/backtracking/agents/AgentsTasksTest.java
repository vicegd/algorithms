package topics.backtracking.agents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Backtracking Agent Assignment</h1>
 * <p>
 * Proves that Backtracking exhaustively finds the true mathematical optimum 
 * that Greedy heuristics missed.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Agent-Task Assignment (Backtracking)")
class AgentsTasksTest {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasksTest.class);

    @BeforeAll
    static void setup() {
        log.trace("Initializing Backtracking Assignment Benchmarks");
    }

    @Test
    @DisplayName("Should find the absolute minimum cost guaranteed via exhaustive search")
    void shouldFindGlobalOptimum() {
        // A controlled matrix where the absolute optimum is clear
        int[][] costs = {
            {10, 9, 5},
            {2, 3, 4},
            {8, 7, 6}
        };
        
        // The absolute optimum is:
        // Agent 0 -> Task 2 (Cost 5)
        // Agent 1 -> Task 0 (Cost 2)
        // Agent 2 -> Task 1 (Cost 7)
        // Total = 14
        
        AgentsTasks engine = new AgentsTasks(costs);
        engine.solve();
        
        assertEquals(14, engine.getOptimalCost(), "Backtracking failed to evaluate all branches to find the optimum.");
    }
}