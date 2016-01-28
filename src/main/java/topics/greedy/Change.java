package topics.greedy;

/**
 * GREEDY ALGORITHM PROBLEM: THE PROBLEM OF THE CHANGE OF MONEY
 * It has an optimal solution in some cases.
 * The idea (heuristic) is always give the higher currency 
 * value that is less than or equal to the amount left to 
 * return 
 * @author viceg
 */
public class Change {
	private float[] coins; //Available coins to be used 
	private int[] solution; //The solution, that is, the number of coins of each type we should get
	
	/**
	 * Constructor for Change objects
	 * @param coins Available coins to be used
	 * @param solution Array to save the number of coins of each type used as a solution
	 */
	public Change(float[] coins, int[] solution) {
		this.coins = coins;
		this.solution = solution;
	}
	
	/**
	 * Calculates the coins to be used to give 
	 * a specific amount of money
	 * This method has a good complexity O(n)
	 * @param amount Amount of money that the needs to be given
	 */
	public void calculateCoins(double amount){
		int numCoins = 0; //index of the coins (the lowest index corresponds to the higher value of the coin)	
		
		while (amount > 0 && numCoins < coins.length){
			//state is valid
			if (amount - coins[numCoins] >= 0){
				//new coin in the solution set
				solution[numCoins]++; //we increment one coin of a specific value
				amount -= coins[numCoins];
			}
			else
				//check the coin of lower value
				numCoins ++;
		}
	}

}
