package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Knapsack JUnit tests
 * @author viceg
 */
public class KnapsackTest {
	private static Logger log = LoggerFactory.getLogger(KnapsackTest.class);
	private Knapsack knapsack;
	private int[] weights; //weight of each of the objects
	private int[] values; //value of each of the objects
	private float[] solution; //the solution, that is, the amount of each of the objects we take from the backpack
	private float[] expectedSolution; //To keep the expected solution
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Knapsack Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Knapsack Tests - Teardown");
	}
	
	/**
	 * It gives the taken objects. Optimal solution expected
	 */
	@Test
	public void testOptimal() {
		weights = new int[] {10, 20, 30, 40, 50};
		values = new int[] {20, 30, 66, 40, 60};
		solution = new float[5];
		knapsack = new Knapsack(weights, values, solution);
	
		int maxWeight = 100; //The limit of weight you can put in the backpack
		expectedSolution = new float[] {1, 1, 1, 0, 0.8f}; 
		
		log.trace("We have a limit of: " + maxWeight + " in the backpack");
		knapsack.findObjects(maxWeight);
		
		int totalWeight = 0;
		int totalValue = 0;
		for (int i = 0; i < solution.length; i++){
			assertEquals(expectedSolution[i], solution[i], 0.1);
			log.trace("\tWe took " + solution[i] + " of the element number " + i + 
					" (Value = " + values[i]*solution[i] + " And weight = " + weights[i]*solution[i] + ")");
			totalWeight += weights[i]*solution[i];
			totalValue += values[i]*solution[i];
		}
		log.trace("\tThe total value is " + totalValue + " with a weight of " + totalWeight);
	}
	
}
