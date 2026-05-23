package topics.divideconquer.stringinterleaving;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Validation Suite for Recursive String Interleaving</h1>
 * <p>
 * Proves that pure recursion successfully resolves ambiguous choices by exploring 
 * the entire state space, avoiding the "Greedy Trap".
 * </p>
 *
 * @author vicegd
 */
@DisplayName("String Interleaving (Pure Recursion)")
class StringInterleavingTest {
    private static final Logger log = LoggerFactory.getLogger(StringInterleavingTest.class);
    private static StringInterleaving engine;

    @BeforeAll
    static void setup() {
        engine = new StringInterleaving();
    }

    @Test
    @DisplayName("Should successfully verify a sequential interleaving (Case A)")
    void shouldVerifySequentialInterleaving() {
        assertTrue(engine.isInterleaved("HELLO", "EVERYBODY", "HELLOEVERYBODY"));
    }

    @Test
    @DisplayName("Should successfully verify a mixed interleaving (Case C)")
    void shouldVerifyMixedInterleaving() {
        assertTrue(engine.isInterleaved("HELLO", "EVERYBODY", "HEVEERYLBLOODY"));
    }

    @Test
    @DisplayName("Should correctly reject an invalid character arrangement (Case D)")
    void shouldRejectInvalidInterleaving() {
        assertFalse(engine.isInterleaved("HELLO", "EVERYBODY", "EVERYHELBODYLO"));
    }

    @Test
    @DisplayName("Should successfully solve the ambiguous case (Escaping the Greedy Trap)")
    void shouldSolveTheAmbiguousCase() {
        String a = "XXY";
        String b = "XZ";
        String c = "XXZXY"; 
        
        log.trace("Demonstrating Recursion superiority. Testing ambiguous string: {}", c);
        
        // The recursive algorithm will hit the ambiguous 'X', try branch A, 
        // realize it leads to a dead end, BACKTRACK, and successfully try branch B.
        assertTrue(engine.isInterleaved(a, b, c), "Recursive Algorithm failed to resolve the ambiguous path.");
    }
}