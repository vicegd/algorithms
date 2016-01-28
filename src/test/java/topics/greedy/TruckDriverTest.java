package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TruckDriver JUnit tests
 * @author viceg
 */
public class TruckDriverTest {
	private static Logger log = LoggerFactory.getLogger(TruckDriverTest.class);
	private TruckDriver truckDriver;
	private String[] petrolStations; //Name of each of the petrol stations
	private int[] distancesBetweenPetrolStations; //Distances between petrol stations in kilometers
	private int range; //Number of kilometers the truck can go without stopping
	private boolean[] solution; //Petrol stations the truck should use to minimize the number of stops (true if the driver stops)
	private boolean[] expectedSolution; //Expected solution to be found
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Truck Driver - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Truck Driver - Teardown");
	}
	
	/**
	 * Shows where the truck should stop. It gives the optimal solution
	 */
	@Test
	public void testPetrolStations() {
		petrolStations = new String[]{"Bilbao", "Burgos", "Madrid", "Cordoba", "Malaga"};
		distancesBetweenPetrolStations = new int[] {0, 159, 240, 395, 160};
		range = 400; //the truck has a range of X kilometers per charge
	 	solution = new boolean[petrolStations.length];
	 	expectedSolution = new boolean[] {false, false, true, true, true};
	 	
	 	truckDriver = new TruckDriver(petrolStations, distancesBetweenPetrolStations, range);
	 	truckDriver.findPath(solution);
	 	
	 	for (int i = 0; i < petrolStations.length; i++) {
	 		assertEquals(expectedSolution[i], solution[i]);
	 	} 	
	}
	
	/**
	 * Shows where the truck should stop. It gives the optimal solution
	 */
	@Test
	public void testPetrolStations2() {
		petrolStations = new String[]{"Bilbao", "Burgos", "Madrid", "Cordoba", "Malaga"};
		distancesBetweenPetrolStations = new int[] {0, 59, 43, 96, 160};
		range = 400; //the truck has a range of X kilometers per charge
	 	solution = new boolean[petrolStations.length];
	 	expectedSolution = new boolean[] {false, false, false, false, true};
	 	
	 	truckDriver = new TruckDriver(petrolStations, distancesBetweenPetrolStations, range);
	 	truckDriver.findPath(solution);
	 	
	 	for (int i = 0; i < petrolStations.length; i++) {
	 		assertEquals(expectedSolution[i], solution[i]);
	 	} 	
	}
	
}
