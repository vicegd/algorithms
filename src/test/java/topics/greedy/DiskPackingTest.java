package topics.greedy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Validation Suite for Disk Packing Greedy Strategies</h1>
 * <p>
 * Verifies that the ascending strategy correctly finds the optimal count, 
 * and highlights the algorithmic trap where the descending strategy fails 
 * to find the optimal space configuration.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("Disk Packing Strategies")
class DiskPackingTest {
    private static final Logger log = LoggerFactory.getLogger(DiskPackingTest.class);
    private static DiskPacking diskPacking;

    @BeforeAll
    static void setup() {
        diskPacking = new DiskPacking();
    }

    @Test
    @DisplayName("Maximize Files: Should find the optimal number of files (Smallest First)")
    void shouldMaximizeFileCount() {
        int[] files = {100, 350, 450, 370, 5000, 500, 700, 800, 50};
        int discCapacity = 880;
        
        // Sorted ascending: 50, 100, 350, 370, 450, 500, 700, 800, 5000
        // Fits: 50 + 100 + 350 + 370 = 870. Total count: 4.
        int result = diskPacking.maximizeFileCount(files, discCapacity);
        assertEquals(4, result, "Failed to calculate the optimal maximum file count.");
    }

    @Test
    @DisplayName("Maximize Space: Demonstrates the Greedy Trap (Sub-optimal space usage)")
    void shouldDemonstrateSpaceUsageGreedyTrap() {
        int[] files = {100, 350, 450, 370, 5000, 500, 700, 800, 50};
        int discCapacity = 1200;
        
        int result = diskPacking.maximizeSpaceUsage(files, discCapacity);
        
        // Greedy Descending Logic:
        // Skips 5000. 
        // Takes 800 (400 remaining).
        // Skips 700, 500, 450.
        // Takes 370 (30 remaining).
        // Skips 350, 100, 50.
        // Total Greedy Output: 800 + 370 = 1170.
        assertEquals(1170, result, "Algorithm behavior deviated from standard Greedy path.");
        
        log.trace("Greedy trapped at 1170. The mathematical optimum is exactly 1200 (using files 700 and 500).");
    }
}