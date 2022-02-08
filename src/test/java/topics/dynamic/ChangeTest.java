package topics.dynamic;

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
	 * It gives the fewest possible number of coins to pay a certain amount of money
	 */
	@Test
	public void testChange() {
		//CASE 1
		int amount = 15;
		int[]coins = new int[3];
		coins[0]=1;coins[1]=6;coins[2]=4;
		
		log.trace("Case with an amount of " + amount);
		
		change = new Change();
		int result = change.change(amount, coins);
		
		assertEquals(4, result);
	}
	
	/**
	 * It gives the fewest possible number of coins to pay a certain amount of money
	 */
	@Test
	public void testChange2() {
		//CASE 2: Seen in case 1 of greedy algorithms (ChangeNotOptimal.java)
		int amount = 60;
		int[]coins = new int[6];
		coins[0]=1;coins[1]=2;coins[2]=20;
		coins[3]=50;coins[4]=100;coins[5]=200;
		
		log.trace("Case with an amount of " + amount);
		
		change = new Change();
		int result = change.change(amount, coins);
		
		assertEquals(3, result);
	}
	
	/**
	 * It gives the fewest possible number of coins to pay a certain amount of money
	 */
	@Test
	public void testChange3() {
		//CASE 3: Seen in case 2 of greedy algorithms (ChangeNotOptimal.java
		int amount = 15;
		int[]coins = new int[8];
		coins[0]=1;coins[1]=4;coins[2]=5;coins[3]=12;
		coins[4]=20;coins[5]=50;coins[6]=100;coins[7]=200;
		
		log.trace("Case with an amount of " + amount);
		
		change = new Change();
		int result = change.change(amount, coins);
		
		assertEquals(3, result);
	}
	
}
