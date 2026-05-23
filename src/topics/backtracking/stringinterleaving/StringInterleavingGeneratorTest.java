package topics.backtracking.stringinterleaving;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Interleaving Generator</h1>
 * <p>
 * Validates that the backtracking algorithm explores all mathematical permutations.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("String Interleaving Generator (Backtracking)")
class StringInterleavingGeneratorTest {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingGeneratorTest.class);
    private static StringInterleavingGenerator generator;

    @BeforeAll
    static void setup() {
        generator = new StringInterleavingGenerator();
    }

    @Test
    @DisplayName("Should generate exact permutations for a small input")
    void shouldGenerateSmallPermutations() {
        String a = "AB";
        String b = "CD";
        
        // Math: (2 + 2)! / (2! * 2!) = 24 / 4 = 6 possible interleavings.
        List<String> results = generator.generateAllInterleavings(a, b);
        
        log.trace("Small strings (AB + CD) interleavings:");
        results.forEach(sol -> log.trace(" - {}", sol));

        assertEquals(6, results.size(), "Failed to generate the exact number of mathematical combinations.");
        assertTrue(results.contains("ABCD"));
        assertTrue(results.contains("ACBD"));
        assertTrue(results.contains("CABD"));
    }

    @Test
    @DisplayName("Should generate massive permutations for the main example")
    void shouldGenerateMainExamplePermutations() {
        String a = "HELLO";       // Length = 5
        String b = "EVERYBODY";   // Length = 9
        
        // Math: (5 + 9)! / (5! * 9!) = 14! / (120 * 362880) = 87,178,291,200 / 43,545,600 = 2002 possibilities.
        List<String> results = generator.generateAllInterleavings(a, b);
        
        log.trace("Main example (HELLO + EVERYBODY) generated {} unique paths.", results.size());
        
        assertEquals(2002, results.size(), "Failed to traverse the full combinatorial tree.");
        
        // Verify that the scenarios we tested in Greedy and DP are present in this master list!
        assertTrue(results.contains("HELLOEVERYBODY"), "Sequential path missing.");
        assertTrue(results.contains("HEVEERYLBLOODY"), "Mixed path missing.");
    }
}