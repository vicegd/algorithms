package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREEDY ALGORITHM PROBLEM: THE TRUCK DRIVER IN A HURRY
 * It has an optimal solution
 * @author viceg
 */
public class TruckDriver {
	private static Logger log = LoggerFactory.getLogger(TruckDriver.class);
	private String[] petrolStations; //Name of each of the petrol stations
	private int[] distancesBetweenPetrolStations; //Distances between petrol stations in kilometers
	private int range; //Number of kilometers the truck can go without stopping
	
	/**
	 * Constructor for TruckDriver objects
	 * @param petrolStations Cities in which there are petrol stations
	 * @param distancesBetweenPetrolStations Distances among cities (petrol stations)
	 * @param range Maximum kilometers the truck can be moved before stopping
	 * @param solution Array indicating if the truck has stopped in a specific city
	 */
	public TruckDriver(String[] petrolStations, int[] distancesBetweenPetrolStations, int range) {
		this.petrolStations = petrolStations;
		this.distancesBetweenPetrolStations = distancesBetweenPetrolStations;
		this.range = range;
	}

	/**
	 * Shows where the truck has stopped
	 * This algorithm has a linear complexity O(n)
	 * @param solution Petrol stations the truck should use to minimize the number of stops (true if the driver stops)
	 */
	public void findPath(boolean[] solution){
		int nKilometers = 0; //We accumulate the way without stopping
		for (int i = 0; i < petrolStations.length; i++) { //We iterate through all the petrol stations
			if ((nKilometers + distancesBetweenPetrolStations[i]) > range){
				solution[i-1] = true; //The driver should have stopped before moving 
				log.trace("\tAfter " + nKilometers + " kilometers the driver stops in " + petrolStations[i-1]);
				nKilometers = 0; //Restart the kilometer counter after refueling
				
			}
			nKilometers += distancesBetweenPetrolStations[i]; //The driver moves the truck
		}
		solution[solution.length-1] = true; //It is the last one (the destination) so he must stop there
		log.trace("\tAfter " + nKilometers + " kilometers the driver stops in " + 
				petrolStations[petrolStations.length-1] + " because it reached its destination");
	}

}
