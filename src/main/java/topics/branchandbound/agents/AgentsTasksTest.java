package topics.branchandbound.agents;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Test Suite for Task Assignment Algorithm</h1>
 * <p>
 * Validates the optimal mathematical resolution of the Branch and Bound strategy 
 * across specific cost matrices to ensure heuristic accuracy and bounding efficiency.
 * </p>
 *
 * @author vicegd
 */
class AgentsTasksTest {
    private static final Logger log = LoggerFactory.getLogger(AgentsTasksTest.class);
    
    /**
     * Initializes context and resources prior to executing the test suite.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Agents Tasks Test Suite");
    }
    
    /**
     * Cleans up resources after the entire test suite has finished execution.
     */
    @AfterAll
    static void teardown() {
        log.trace("Tearing down Agents Tasks Test Suite");
    }
    
    /**
     * <p><strong>Scenario:</strong> A hardcoded 4x4 non-uniform cost matrix.</p>
     * <p><strong>Expected Outcome:</strong> The Branch and Bound algorithm must resolve 
     * the optimal cost vector and correctly aggregate to exactly 61.</p>
     */
    @Test
    void shouldFindOptimalAssignmentCostForFourAgents() {
        int problemSize = 4;
        
        int[][] costMatrix = {
            {11, 12, 18, 40},
            {14, 15, 13, 22},
            {11, 17, 19, 23},
            {17, 14, 20, 28}
        };
        
        var assignmentEngine = new AgentsTasks(problemSize, costMatrix);
        
        assignmentEngine.branchAndBound(assignmentEngine.getRootNode());
        assignmentEngine.printSolutionTrace();
        
        int optimalCost = assignmentEngine.getBestNode().getHeuristicValue();
        
        assertEquals(61, optimalCost, 
            "The minimal operational cost for the provided 4x4 matrix must be exactly 61.");
    }
}