package topics.introduction.helloworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Hello World</h1>
 * <p>
 * The classic first program. Demonstrates the minimal structure of a Java class:
 * a method that takes parameters, performs a simple operation, and returns a result.
 * </p>
 *
 * @author vicegd
 */
public class HelloWorld {
    private static final Logger log = LoggerFactory.getLogger(HelloWorld.class);
    
    /**
     * Returns the sum of two integers.
     *
     * @param a First integer.
     * @param b Second integer.
     * @return a + b
     */
    public int sum(int a, int b) {
        log.info("sum({}, {})", a, b);
        return a + b;
    }
}