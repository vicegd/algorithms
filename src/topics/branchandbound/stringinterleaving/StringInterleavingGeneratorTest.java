package topics.branchandbound.stringinterleaving;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Mock import assuming your util structure
// import topics.branchandbound.utils.HeapRepeatedNodes; 

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for State Space Search (B&B Architecture)</h1>
 * <p>
 * Proves that bypassing the pruning limit correctly allows the algorithm to 
 * exhaustively find all mathematical permutations.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("String Interleaving Generator (Branch & Bound Arch)")
class StringInterleavingBBTest {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingBBTest.class);

    @Test
    @DisplayName("Should explore all branches and find exactly 2002 permutations without pruning")
    void shouldFindAllPermutationsWithoutPruning() {
        String a = "HELLO";
        String b = "EVERYBODY";
        
        // Note: You would instantiate your HeapRepeatedNodes here
        // Heap customHeap = new HeapRepeatedNodes();
        
        // Mocking the heap for the test context
        StringInterleavingBB engine = new StringInterleavingGenerator(a, b, null /* replace with customHeap */);
        
        log.trace("Starting State Space Search. Pruning is DISABLED.");
        engine.branchAndBound(engine.getRootNode());
        
        int resultCount = engine.getSolutionCount();
        log.trace("Search exhausted. Total permutations found: {}", resultCount);
        
        // Formula: 14! / (5! * 9!) = 2002
        assertEquals(2002, resultCount, "Algorithm pruned paths prematurely. Generation must be exhaustive.");
    }
}