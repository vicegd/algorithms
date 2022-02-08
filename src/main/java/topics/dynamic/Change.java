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
		int types = coins.length; //The different types of money we have for a specific problem
		int[][]sol = new int[types][amount+1]; //Creates the table [different types of coins][value we need to deal with + 1 because we start in zero]
		
		int notPickingNewCoin = 0;
		int pickingNewCoin = 0;
		for (int i=0; i<= amount; i++) 
			sol[0][i] = i; //It saves the value of the money (from 0 to the given value)
		
		for (int i=1; i<types; i++)
			for (int j=0; j<=amount; j++) {
				notPickingNewCoin = sol[i-1][j]; //The value from the previous row
				if (j >= coins[i]) //If we can get a coin from coin[i] and we still have money to refund
					pickingNewCoin = 1+sol[i][j-coins[i]]; 
				else pickingNewCoin = Integer.MAX_VALUE; //It is not reachable
				sol[i][j] = Math.min(notPickingNewCoin, pickingNewCoin); //We always choose the smallest coin => we want few coins
			}
		
		//Prints the calculated matrix
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i=0;i<types;i++) { 
			for (int j=0; j<=amount; j++)
				sb.append(String.format("%5d", sol[i][j]));
			sb.append("\n");
		}
		log.trace(sb.toString());
		return sol[types-1][amount]; //It returns the last value of all
	}
		
}
