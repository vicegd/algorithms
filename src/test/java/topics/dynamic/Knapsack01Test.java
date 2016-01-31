package topics.dynamic;

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
	 * It gives the maximum value in a knapsack
	 * Objects cannot be broken
	 */
	@Test
	public void testKnapsack() {
		//CASE 1: Very simple. The greedy algorithm already seen fails because it returns 60 (Knapsack01.java)
		int maxWeight = 7; //the limit of weight you can put in the backpack
		int n = 3; //number of elements
		
		float[]benefits = new float[n]; //values/weights (Kg)
		benefits[0]=12;benefits[1]=10;benefits[2]=10;
		
		int[]weights = new int[n]; //weights of the elements
		weights[0]=5;weights[1]=4;weights[2]=3;
		
		log.trace("Case with a maximum weight of " + maxWeight + " Kg");
		
		knapsack = new Knapsack01();
		float result = knapsack.knapsack01(maxWeight, benefits, weights);
		
		assertEquals(70, result, 0.01);
	}
	
	/**
	 * It gives the maximum value in a knapsack
	 * Objects cannot be broken
	 */
	@Test
	public void testKnapsack2() {
		//CASE 2
		int maxWeight = 55; //the limit of weight you can put in the backpack
		int n = 8; //number of elements
		
		float[]benefits = new float[n]; //values/weights (Kg)
		benefits[0]=65;benefits[1]=85;benefits[2]=82;benefits[3]=80;
		benefits[4]=68;benefits[5]=70;benefits[6]=90;benefits[7]=60;
		
		int[]weights = new int[n]; //weights of the elements
		weights[0]=80;weights[1]=30;weights[2]=25;weights[3]=60;
		weights[4]=40;weights[5]=50;weights[6]=45;weights[7]=600;
		
		log.trace("Case with a maximum weight of " + maxWeight + " Kg");
		
		knapsack = new Knapsack01();
		float result = knapsack.knapsack01(maxWeight, benefits, weights);
		
		assertEquals(4600, result, 0.01);
	}
	
	/**
	 * It gives the maximum value in a knapsack
	 * Objects cannot be broken
	 */
	@Test
	public void testKnapsack3() {
		//CASE 3
		int maxWeight = 6; //the limit of weight you can put in the backpack
		int n = 4; //number of elements
		
		float[]benefits = new float[n]; //values/weights (Kg)
		benefits[0]=2;benefits[1]=2;benefits[2]=5;benefits[3]=2.5f;
		
		int[]weights = new int[n]; //weights of the elements
		weights[0]=3;weights[1]=2;weights[2]=1;weights[3]=4;
		
		log.trace("Case with a maximum weight of " + maxWeight + " Kg");
		
		knapsack = new Knapsack01();
		float result = knapsack.knapsack01(maxWeight, benefits, weights);
		
		assertEquals(15, result, 0.01);
	}
	
	/**
	 * It gives the maximum value in a knapsack
	 * Objects cannot be broken
	 */
	@Test
	public void testKnapsack4() {
		//CASE 4
		int maxWeight = 10; //the limit of weight you can put in the backpack
		int n = 3; //number of elements
		
		float[]benefits = new float[n]; //values/weights (Kg)
		benefits[0]=8/(float)6;benefits[1]=1;benefits[2]=1;
		
		int[]weights = new int[n]; //weights of the elements
		weights[0]=6;weights[1]=5;weights[2]=5;
		
		log.trace("Case with a maximum weight of " + maxWeight + " Kg");
		
		knapsack = new Knapsack01();
		float result = knapsack.knapsack01(maxWeight, benefits, weights);
		
		assertEquals(10, result, 0.01);
	}
	
	
}
