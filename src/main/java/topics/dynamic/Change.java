package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DYNAMIC PROGRAMMING PROBLEM: PROBLEM OF THE CHANGE OF MONEY
 * We will now discuss three cases. The three fail if
 * we apply the greedy algorithm already seen.
 * Here we only calculate the minimum number of coins.
 * In the base book, you can learn how to know what
 * coins are used
 * @author viceg
 */
@SuppressWarnings("unused")
public class Change {
	private static Logger log = LoggerFactory.getLogger(Change.class);
	
	/**
	 * Dynamic programming version. The time 
	 * complexity is quadratic 0(amount*coins) 
	 * @param amount Amount of money that should be given
	 * @param coins Different available types of coins
	 * @return The number of coins that should be returned
	 */
	public int change(int amount, int[]coins) {
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
		
}
