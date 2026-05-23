package topics.foundation.helloworld;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for {@link HelloWorld}.
 *
 * @author vicegd
 */
class HelloWorldTest {
    private static HelloWorld helloWorld;
    
    @BeforeAll
    static void setup() {
        helloWorld = new HelloWorld();
    }
    
    /**
     * 10 + 40 should equal 50.
     */
    @Test
    void shouldReturnSumOfTwoIntegers() {
        assertEquals(50, helloWorld.sum(10, 40));
    }
    
    /**
     * 10 + 40 should not equal adjacent values.
     */
    @Test
    void shouldNotReturnAdjacentValues() {
        int result = helloWorld.sum(10, 40);
        assertNotEquals(51, result);
        assertNotEquals(49, result);
    }
}