package topics.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Computes the max pairwise product among different numbers
 * E.g.: 7 3 6 => 42
 * However, in this example we will only work with two integer numbers
 * @author viceg
 *
 */
public class MaxPairWiseProduct {
	private static Logger log = LoggerFactory.getLogger(MaxPairWiseProduct.class);
	int number1;
	int number2;
	
	public MaxPairWiseProduct() {
		number1 = 100_000;
		number2 = 1_000_000;
	}
	
	public int compute() {
		log.info("The result is = " + number1 * number2);
		return number1 * number2;
	}
}
