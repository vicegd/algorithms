package topics.backtracking.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Pruned TSP</h1>
 * <p>
 * Proves that bounding (pruning branches that exceed the best cost) is a safe operation 
 * that dramatically improves speed without compromising the global optimum.
 * </p>
 */
@DisplayName("Traveling Salesman (Optimization WITH Pruning)")
class SalesmanPruningTest {
    private static final Logger log = LoggerFactory.getLogger(SalesmanPruningTest.class);

    @Test
    @DisplayName("Should find the exact global optimal cost using Branch & Bound logic")
    void testSalesmanPruning() {
        int n = 7;
        int source = 0;
        int[][] w = CyclesAllTest.createExampleGraph();

        SalesmanPruning salesman = new SalesmanPruning(n, source, w);
        log.debug("Starting Pruned TSP Optimization...");
        
        salesman.backtracking();

        log.debug("Number of best solutions overridden during search: {}", salesman.getNumberSolutions());
        log.debug("Best global cost: {}", salesman.getBestCost());
        log.debug("Best path sequence: {}", salesman.getBestPath());

        // The exact same optimal path and cost MUST be found, demonstrating that 
        // the pruning logic never discards the valid optimal branch.
        assertEquals(8, salesman.getNumberSolutions());
        assertEquals(108, salesman.getBestCost());
        assertEquals("NODE0**NODE2**NODE1**NODE6**NODE3**NODE4**NODE5**NODE0**", salesman.getBestPath());
    }
}