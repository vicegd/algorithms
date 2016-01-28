package topics.introduction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just to show a small example of how the project is structured
 * @author viceg
 */
public class HelloWorld {
	private static Logger log = LoggerFactory.getLogger(HelloWorld.class);
	
	/**
	 * Adds two integer numbers and return the result
	 * @param a The first integer number to be added
	 * @param b The second integer number to be added
	 * @return The calculation of a+b
	 */
	public int sum(int a, int b) {
		log.info("Adding two numbers");
		return a+b;
	}
}
