package seminars.en._20.seminar3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defense {
	protected static Logger log = LoggerFactory.getLogger(Defense.class);
	private int n;						// Number of invaded cities
	private List<City> cities;			// Number of of cities (and enemies inside)
	private List<Defender> defenders;	// Number of defenders in each team
	
	public Defense(int n, List<City> cities, List<Defender> defenders) {
		this.n = n;	//number of invaded cities
		this.cities = cities; //cities with enemies
		this.defenders = defenders;	//groups of defenders
	}
	
	/**
	 * Assign each defense team to a city
	 * In a way that we optimize victories
	 */
	public void assign() {
		int team;

		//we iterate through all the invaded cities
		for (int i= 0; i<n; i++) {
			team = findWinningTeam(cities.get(i).getNumberEnemies()); 
			if (team == -1) //if there is no winning team, we look for the team of the smallest number of people	
				team = findSmallestTeam(); //trying not to waste people when we know we are going to lose
			cities.get(i).setAssignment(team); //we keep the matching found. The defense team number "team" is assigned to the city i
			defenders.get(team).setAssigned(true); //mark the team as already assigned
		}
	}
	
	/**
	 * Assign each defense team to a city
	 * In a way that we optimize victories (better complexity)
	 */
	public void assignQuick() {
		Collections.sort(cities);
		Collections.sort(defenders);
		
		int j = 0; //index to mark the first group of enemies that we need to attack
		int k = n-1; //index to mark the last group of enemies that we need to attack
		for (int i=0; i<n; i++) { //to iterate through all the defenders
			if (defenders.get(i).getNumberDefenders() >= cities.get(j).getNumberEnemies()) { //we have a team that guarantees victory
				cities.get(j).setAssignment(i);
				defenders.get(i).setAssigned(true);
				j++;
			}
			else {	//since we cannot win, we send the team to the last possible city (with the largest number of enemies)
				cities.get(k).setAssignment(i);
				defenders.get(i).setAssigned(true);
				k--;
			}
		} //for
	}
	
	/**
	 * Look for the defense team with the smallest number of people 
	 * This method is used when we know that victory is not possible
	 * @return index of the assigned team
	 */
	private int findSmallestTeam() {
		int team = -1;
		int people = Integer.MAX_VALUE;
		
		//look for the usassinged team with the smallest number of members
		for (int i = 0; i<n; i++)
			if (!defenders.get(i).isAssigned()) { //if it is not assigned
				if (defenders.get(i).getNumberDefenders() < people) {
					people = defenders.get(i).getNumberDefenders();
					team = i;
				}
			}
		return team;
	}

	/**
	 * Look for the smallest defense team that is bigger than the number of the enemies
	 * @param enemies number of enemies in a city
	 * @return index of the assigned team
	 */
	private int findWinningTeam(int enemies) {
		int team = -1;
		int people = Integer.MAX_VALUE;
		
		for (int i = 0; i<n; i++)
			if (!defenders.get(i).isAssigned()) { //if it is not assigned
				if ((defenders.get(i).getNumberDefenders() >= enemies)&&(defenders.get(i).getNumberDefenders() < people)) {
					people = defenders.get(i).getNumberDefenders();
					team = i;
				}
			}
		return team;
	}
	
	/**
	 * Displays on the console, indexes of invaded cities, enemy troops 
	 * and assigned defenders. In addition, the final result according 
	 * to this assignment: Victory / Defeat
	 */
	public void showDefensePlan() {
		int counter = 0;
		System.out.println("Invaded cities");
		System.out.println("\tNumber of enemies:");
		System.out.println("\t\tNumber of defenders");
		System.out.println("\t\t\tBattle result:");
		for (int i= 0; i<n; i++) {
			System.out.print(i+"\t");
			System.out.print(cities.get(i).getNumberEnemies() +"\t");
			
			int defenderTeamAssigned = cities.get(i).getAssignment();
			System.out.print(defenders.get(defenderTeamAssigned).getNumberDefenders()+"\t");
			
			if (defenders.get(defenderTeamAssigned).getNumberDefenders() 
			>= cities.get(i).getNumberEnemies()) {
				counter++;
				System.out.println("Victory\t");
			}
			else System.out.println("*Defeat\t");
		}
		System.out.println("Number of victories: " + counter);
	}

	/* Try the assignment algorithm */
	public static void main(String[] args) {
		long t1, t2;
		
		for (int n=5; n<Integer.MAX_VALUE; n*=2) {
			List<City> cities = generateEnemiesInCities(n); //random array with number of enemies in each city
			List<Defender> defenders = generateDefenders(n); //random array with number of defenders in each group
			
			Defense defense = new Defense(n, cities, defenders);
			
			t1 = System.currentTimeMillis();
			defense.assign(); //to assign defenders to cities
			t2 = System.currentTimeMillis();
			
			//defense.showDefensePlan(); //to show the result
			log.debug(n + ": - Basic algorith time: "+(t2-t1)+" milliseconds");
		
			restart(cities, defenders);
			
			t1 = System.currentTimeMillis();
			defense.assignQuick(); //to assign defenders to cities
			t2 = System.currentTimeMillis();
			
			//defense.showDefensePlan(); //to show the result
			System.out.println(n + ": - Quick algorithm time: "+(t2-t1)+" milliseconds\n");
		}
	}
	
	/**
	 * Generates random arrays with groups of enemies
	 * @param n
	 * @return
	 */
	public static ArrayList<City> generateEnemiesInCities(int n) {
		Random r = new Random();
		ArrayList<City> cities = new ArrayList<>();
		
		for (int i = 0; i<n; i++) {
			City city = new City(i, r.nextInt(1000));
			cities.add(city);
		}

		return cities;
	}
	
	/**
	 * Generates random arrays with groups of defenders
	 * @param n
	 * @return
	 */
	public static ArrayList<Defender> generateDefenders(int n) {
		Random r = new Random();
		ArrayList<Defender> defenders = new ArrayList<>();
		
		for (int i = 0; i<n; i++) {
			Defender defender = new Defender(i, r.nextInt(1000));
			defenders.add(defender);
		}

		return defenders;
	}
	
	private static void restart(List<City> cities, List<Defender> defenders){
		for (City c : cities) {
			c.setAssignment(-1); //we don't have any defender team assigned to this city
		}
		
		for (Defender d : defenders) {
			d.setAssigned(false); //the defender team is not assigned yet
		}
	}

}
