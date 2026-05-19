package topics.introduction;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Search Algorithms</h1>
 * <p>
 * Verifies the logical accuracy of the Sequential, Sentinel, and Binary search 
 * algorithms against both successful retrievals and deliberate misses.
 * </p>
 *
 * @author vicegd
 */
class SearchTest {
    private static final Logger log = LoggerFactory.getLogger(SearchTest.class);
    private static Search searchEngine;
  
    /**
     * Initializes the context and instantiates the computational engine 
     * prior to executing the validations.
     */
    @BeforeAll
    static void setup() {
        log.trace("Initializing Search Algorithm Validation Context");
        searchEngine = new Search();
    }
  
    /**
     * <p><strong>Scenario:</strong> Standard sequential search for an existing element.</p>
     */
    @Test
    void shouldFindElementUsingSequentialSearch() {
        int[] dataset = {3, 1, 10, 5, -1};
        boolean isFound = searchEngine.searchSequential(dataset, 10);
        
        assertTrue(isFound, "The linear search must successfully locate the existing number 10.");
    }
  
    /**
     * <p><strong>Scenario:</strong> Standard sequential search for a non-existent element.</p>
     */
    @Test
    void shouldNotFindMissingElementUsingSequentialSearch() {
        int[] dataset = {3, 1, 10, 5, -1};
        boolean isFound = searchEngine.searchSequential(dataset, 100);
        
        assertFalse(isFound, "The linear search must accurately report the absence of the number 100.");
    }
  
    /**
     * <p><strong>Scenario:</strong> Sentinel-backed sequential search for an existing element.</p>
     */
    @Test
    void shouldFindElementUsingSentinelSearch() {
        List<Integer> dataset = new ArrayList<>(Arrays.asList(3, 1, 10, 5, -1));
        boolean isFound = searchEngine.searchSequentialSentinel(dataset, 10);
        
        assertTrue(isFound, "The sentinel search must successfully locate the existing number 10.");
    }
  
    /**
     * <p><strong>Scenario:</strong> Sentinel-backed sequential search for a non-existent element.</p>
     */
    @Test
    void shouldNotFindMissingElementUsingSentinelSearch() {
        List<Integer> dataset = new ArrayList<>(Arrays.asList(3, 1, 10, 5, -1));
        boolean isFound = searchEngine.searchSequentialSentinel(dataset, 100);
        
        assertFalse(isFound, "The sentinel search must accurately distinguish the injected sentinel from original data.");
    }
  
    /**
     * <p><strong>Scenario:</strong> Binary search across a sorted dataset for an existing element.</p>
     */
    @Test
    void shouldFindElementUsingBinarySearch() {
        int[] sortedDataset = {-1, 1, 3, 4, 15, 100};
        boolean isFound = searchEngine.searchBinary(sortedDataset, 15);
        
        assertTrue(isFound, "The binary search must successfully locate the number 15 within the logarithmic divisions.");
    }
  
    /**
     * <p><strong>Scenario:</strong> Binary search across a sorted dataset for a non-existent element.</p>
     */
    @Test
    void shouldNotFindMissingElementUsingBinarySearch() {
        int[] sortedDataset = {-1, 1, 3, 4, 15, 100};
        boolean isFound = searchEngine.searchBinary(sortedDataset, 92);
        
        assertFalse(isFound, "The binary search must accurately exhaust the search space and report the absence of 92.");
    }
}