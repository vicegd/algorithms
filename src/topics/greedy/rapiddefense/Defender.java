package seminars.en._20.seminar3;

public class Defender implements Comparable<Defender> {
	private int id; //id of the team
	private int numberDefenders; //number of people in the team
	private boolean assigned; //is it the team assigned to any city?
	
	public Defender(int id, int number) {
		this.id = id;
		this.numberDefenders = number;
		this.assigned = false;
	}

	public void setNumberDefenders(int number) {
		this.numberDefenders = number;
	}

	public int getNumberDefenders() {
		return numberDefenders;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public int getId() {
		return id;
	}

	/**
	 * Needed to sort defenders
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Defender defenders2) {
		if (this.numberDefenders == defenders2.numberDefenders)
			return 0;
		else
			if (this.numberDefenders < defenders2.numberDefenders)
				return -1;
			else
				return 1;
	}

}
