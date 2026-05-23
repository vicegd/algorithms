package topics.greedy.stringinterleaving;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Greedy String Interleaving</h1>
 * <p>
 * Demonstrates successful cases where Greedy works (disjoint or non-ambiguous strings), 
 * and explicitly demonstrates the Greedy Trap where it incorrectly fails on valid interleavings.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("String Interleaving (Greedy Heuristic)")
class StringInterleavingTest {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingTest.class);
    private static StringInterleaving engine;

    @BeforeAll
    static void setup() {
        engine = new StringInterleaving();
    }

    @Test
    @DisplayName("Should successfully verify a clean, sequential interleaving (Case A)")
    void shouldVerifyCleanInterleaving() {
        String a = "HELLO";
        String b = "EVERYBODY";
        String c = "HELLOEVERYBODY"; 
        
        log.trace("Testing sequential concatenation: {}", c);
        assertTrue(engine.isInterleaved(a, b, c), "Failed to verify sequential interleaving.");
    }

    @Test
    @DisplayName("Should successfully verify a mixed interleaving (Case C)")
    void shouldVerifyMixedInterleaving() {
        String a = "HELLO";
        String b = "EVERYBODY";
        String c = "HEVEERYLBLOODY"; 
        
        log.trace("Testing mixed interleaving: {}", c);
        assertTrue(engine.isInterleaved(a, b, c), "Failed to verify mixed interleaving.");
    }

    @Test
    @DisplayName("Should quickly reject strings with incorrect total lengths")
    void shouldRejectIncorrectLengths() {
        assertFalse(engine.isInterleaved("A", "B", "ABC"), "Failed to reject based on length mismatch.");
    }

    @Test
    @DisplayName("Should fall into the Greedy Trap with ambiguous choices")
    void shouldDemonstrateGreedyTrap() {
        // Here, both strings share common characters.
        String a = "XXY";
        String b = "XZ";
        String c = "XXZXY"; 
        
        // Is "XXZXY" a valid interleaving? YES!
        // Path: b(X) -> a(X) -> b(Z) -> a(X) -> a(Y) = XXZXY.
        
        // But the Greedy algorithm prioritizes 'A'. 
        // 1. Matches 'X' with a(X).
        // 2. Matches 'X' with a(X).
        // 3. Matches 'Z' with b(X) -> Fails. Matches b(Z) -> b is advanced.
        // 4. Looks for 'X', but A only has 'Y' left, and B is empty. DEAD END!
        
        log.trace("Testing Greedy Trap with string: {}. Expecting a false negative.", c);
        
        // The assertion expects FALSE because the greedy algorithm fails, 
        // even though the string is technically a valid interleaving.
        assertFalse(engine.isInterleaved(a, b, c), "Algorithm miraculously solved an ambiguous path.");
    }
}