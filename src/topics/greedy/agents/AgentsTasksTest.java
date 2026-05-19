package topics.greedy.agents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Agent-Task Assignment Problems</h1>
 * <p>
 * Validates the operational divergence between row-minima and column-minima greedy logic, 
 * proving sub-optimality bounds using classical academic vectors.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Agent & Task Assignment Matrix Verification")
class AgentsTasksTest {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasksTest.class);
    private static int[][] standardCosts;

    @BeforeAll
    static void setup() {
        log.trace("Initializing Assignment Problem Asset Benchmarks");
        
        // Classic 4x4 matrix from academic test cases
        standardCosts = new int[][]{
            {11, 12, 18, 40},
            {14, 15, 13, 22},
            {11, 17, 19, 23},
            {17, 14, 20, 28}
        };
    }

    @Test
    @DisplayName("Strategy 1 (Row Minima): Should assign tasks tracking agent vantage points")
    void shouldAssignTasksToAgentsUsingRowMinima() {
        AgentsTasks engine = new AgentsTasks(standardCosts);
        
        // Execute assignment sequence
        int[] assignments = engine.assignTasksToAgents();
        int resolvedCost = engine.calculateRowStrategyCost(assignments);
        
        // Expected outcome: 69
        assertEquals(69, resolvedCost, "Row-Minima approach yielded an incorrect cumulative cost calculation.");
    }

    @Test
    @DisplayName("Strategy 2 (Column Minima): Should assign agents tracking task optimization priorities")
    void shouldAssignAgentsToTasksUsingColumnMinima() {
        AgentsTasks engine = new AgentsTasks(standardCosts);
        
        // Execute assignment sequence
        int[] assignments = engine.assignAgentsToTasks();
        int resolvedCost = engine.calculateColumnStrategyCost(assignments);
        
        // Expected outcome: 61
        assertEquals(61, resolvedCost, "Column-Minima approach yielded an incorrect cumulative cost calculation.");
    }
}