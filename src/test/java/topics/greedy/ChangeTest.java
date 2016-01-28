package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Change JUnit tests
 * @author viceg
 */
public class ChangeTest {
	private static Logger log = LoggerFactory.getLogger(ChangeTest.class);
	private Change change;
	private float[] coins; //Available coins to be used 
	private int[] solution; //The solution, that is, the number of coins of each type we should get
	private int[] expectedSolution; //To keep the expected solution
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Change Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Change Tests - Teardown");
	}
	
	/**
	 * It gives the change. Optimal solution expected
	 */
	@Test
	public void testOptimal() {
		coins = new float[] {8, 4, 2, 1}; //The lowest index corresponds to the higher value of the coin
		solution = new int[4];
		change = new Change(coins, solution);
	
		int amount = 15;
		expectedSolution = new int[]{1, 1, 1, 1}; 
		
		log.trace("We need to reach " + amount + " units of money with the lowest possible number of coins");
		change.calculateCoins(amount);
		for (int i = 0; i < solution.length; i++){
			assertEquals(expectedSolution[i], solution[i]);
			log.trace("\tThe number of coins of value " + coins[i] + " is " + solution[i]);
		}
	}
	
	/**
	 * It gives the change. Not optimal solution expected
	 */
	@Test
	public void testNotOptimal() {
		coins = new float[] {200, 100, 50, 20, 2, 1};
		solution = new int[6];
		change = new Change(coins, solution);
	
		int amount = 60;
		expectedSolution = new int[]{0, 0, 1, 0, 5, 0}; 
		
		log.trace("We need to reach " + amount + " units of money with the lowest possible number of coins");
		change.calculateCoins(amount);
		for (int i = 0; i < solution.length; i++){
			assertEquals(expectedSolution[i], solution[i]);
			log.trace("\tThe number of coins of value " + coins[i] + " is " + solution[i]);
		}
		log.trace("\tThe optimal result would be 3 coins of 20");
	}
	
	/**
	 * It gives the change. Not optimal solution expected
	 */
	@Test
	public void testNotOptimal2() {
		coins = new float[] {200, 100, 50, 20, 12, 5, 4, 1};
		solution = new int[8];
		change = new Change(coins, solution);
	
		int amount = 15;
		expectedSolution = new int[]{0, 0, 0, 0, 1, 0, 0, 3}; 
		
		log.trace("We need to reach " + amount + " units of money with the lowest possible number of coins");
		change.calculateCoins(amount);
		for (int i = 0; i < solution.length; i++){
			assertEquals(expectedSolution[i], solution[i]);
			log.trace("\tThe number of coins of value " + coins[i] + " is " + solution[i]);
		}
		log.trace("\tThe optimal result would be 3 coins of 5");
	}

}
