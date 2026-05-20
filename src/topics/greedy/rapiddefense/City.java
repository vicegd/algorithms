package seminars.en._20.seminar3;

public class City implements Comparable<City> {
	private int id;			//id of the city
	private int numberEnemies;	//number of enemies
	private int assignment; //the index of the defender team that is assigned to this city
	
	public City(int id, int enemies) {
		this.id = id;
		this.numberEnemies = enemies;
		this.assignment = -1;
	}
	
	public int getId() {
		return id;
	}
	
	public int getNumberEnemies() {		
		return numberEnemies;
	}
	
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}

	public int getAssignment() {
		return assignment;
	}
	
	/**
	 * Needed to sort cities
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(City city2) {
		if (this.numberEnemies == city2.numberEnemies)
			return 0;
		else if (this.numberEnemies < city2.numberEnemies)
				return -1;
			else
				return 1;
	}
	
}
