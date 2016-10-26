package topics.introduction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Computes the max pairwise product among different numbers
 * E.g.: 7 3 6 => 42
 * However, in this example we will only work with two long numbers
 * @author viceg
 *
 */
public class MaxPairWiseProduct2 {
	private static Logger log = LoggerFactory.getLogger(MaxPairWiseProduct2.class);
	long number1;
	long number2;
	
	public MaxPairWiseProduct2() {
		number1 = 100_000;
		number2 = 1_000_000;
	}
	
	/**
	 * The problem with the previous version is:
	 * Maximum JAVA int value: 2_147_483_647
	 * Expected result: 	   100_000_000_000
	 * If we work with long, the maximum JAVA long value: 9_223_372_036_854_775_807 
	 * @return
	 */
	public long compute() {
		log.info("The result is = " + number1 * number2);
		return number1 * number2;
	}
}
