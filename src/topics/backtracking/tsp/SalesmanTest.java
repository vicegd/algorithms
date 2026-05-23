package topics.backtracking.tsp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Traveling Salesman (Optimization WITHOUT Pruning)")
class SalesmanTest {
    private static final Logger log = LoggerFactory.getLogger(SalesmanTest.class);

    @Test
    @DisplayName("Should find the exact global optimal cost by exploring the entire state space")
    void testSalesmanOptimization() {
        int n = 7;
        int source = 0;
        int[][] w = CyclesAllTest.createExampleGraph(); // Reuse the graph builder

        Salesman salesman = new Salesman(n, source, w);
        log.debug("Starting Un-pruned TSP Optimization...");
        
        salesman.backtracking();

        log.debug("Number of best solutions overridden during search: {}", salesman.getNumberSolutions());
        log.debug("Best global cost: {}", salesman.getBestCost());
        log.debug("Best path sequence: {}", salesman.getBestPath());

        // Assertions based on the known optimum for this topology
        assertEquals(8, salesman.getNumberSolutions(), "Unexpected number of cost overrides.");
        assertEquals(108, salesman.getBestCost(), "The algorithm failed to find the absolute minimum cost.");
        assertEquals("NODE0**NODE2**NODE1**NODE6**NODE3**NODE4**NODE5**NODE0**", salesman.getBestPath());
    }
}