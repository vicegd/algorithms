package topics.dynamic.stringinterleaving;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for DP String Interleaving</h1>
 * <p>
 * Proves that Dynamic Programming flawlessly handles sequential, mixed, 
 * and highly ambiguous strings that break the Greedy algorithm.
 * </p>
 *
 * @author vicegd
 */
@DisplayName("String Interleaving (Dynamic Programming)")
class StringInterleavingTest {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingTest.class);
    private static StringInterleaving engine;

    @BeforeAll
    static void setup() {
        engine = new StringInterleaving();
    }

    @Test
    @DisplayName("Should successfully verify a sequential interleaving")
    void shouldVerifySequentialInterleaving() {
        assertTrue(engine.isInterleaved("HELLO", "EVERYBODY", "HELLOEVERYBODY"));
    }

    @Test
    @DisplayName("Should successfully verify a mixed interleaving")
    void shouldVerifyMixedInterleaving() {
        assertTrue(engine.isInterleaved("HELLO", "EVERYBODY", "HEVEERYLBLOODY"));
    }

    @Test
    @DisplayName("Should correctly reject an invalid character arrangement")
    void shouldRejectInvalidInterleaving() {
        // Here, the characters match, but the relative order of "EVERYBODY" is broken.
        assertFalse(engine.isInterleaved("HELLO", "EVERYBODY", "EVERYHELBODYLO"));
    }

    @Test
    @DisplayName("Should successfully solve the ambiguous case (The Greedy Trap)")
    void shouldSolveTheGreedyTrap() {
        // This is the exact scenario where the Greedy algorithm failed in the previous topic.
        String a = "XXY";
        String b = "XZ";
        String c = "XXZXY"; 
        
        log.trace("Demonstrating DP superiority. Testing ambiguous string: {}", c);
        
        // DP explores both parallel branches when it encounters 'X', safely finding the valid path.
        assertTrue(engine.isInterleaved(a, b, c), "DP Algorithm failed to resolve the ambiguous path.");
    }
}