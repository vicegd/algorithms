package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Knapsack01 JUnit tests
 * @author viceg
 */
public class Knapsack01Test {
	private static Logger log = LoggerFactory.getLogger(Knapsack01Test.class);
	private Knapsack01 knapsack;
	private int[] weights; //weight of each of the objects
	private int[] values; //value of each of the objects
	private float[] solution; //the solution, that is, the amount of each of the objects we take from the backpack
	private float[] expectedSolution; //To keep the expected solution
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Knapsack01 Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Knapsack01 Tests - Teardown");
	}
	
	/**
	 * It gives the taken objects. Not optimal solution expected
	 */
	@Test
	public void testNotOptimal() {
		weights = new int[] {5, 4, 3}; //Best relation: 60/5 = 12
		values = new int[] {60, 40, 30};
		solution = new float[3];
		knapsack = new Knapsack01(weights, values, solution);
	
		int maxWeight = 7; //The limit of weight you can put in the backpack
		expectedSolution = new float[] {1, 0, 0}; 
		
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
		log.trace("\tThe correct result would be 70 with a weight of 7 (complete)");
	}
	
	/**
	 * It gives the taken objects. Not optimal solution expected
	 */
	@Test
	public void testNotOptimal2() {
		weights = new int[] {30, 25, 60, 50, 45, 40, 80, 80, 600}; //Best relation: 4050/45 = 90
		values = new int[] {2550, 2050, 4800, 3500, 4050, 2720, 5200, 36000};
		solution = new float[8];
		knapsack = new Knapsack01(weights, values, solution);
	
		int maxWeight = 55; //The limit of weight you can put in the backpack
		expectedSolution = new float[] {0, 0, 0, 0, 1, 0, 0, 0}; 
		
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
		log.trace("\tThe correct result would be 4600 with a weight of 55 (complete)");
	}

}
