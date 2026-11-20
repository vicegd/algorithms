package topics.greedy.rapiddefense;

/**
 * <h1>Invaded City Model</h1>
 * <p>
 * Represents a city under attack in the Rapid Defense greedy assignment problem.
 * Implements {@link Comparable} so cities can be sorted in ascending order of enemy count.
 * </p>
 *
 * @author vicegd
 */
public class City implements Comparable<City> {
    /** Unique identifier of the city. */
    private final int id;
    /** Number of enemy units invading this city (demand threshold). */
    private final int numberEnemies;
    /** Assigned defender team ID, or {@code -1} if undefended. */
    private int defenderTeamId; 

    /**
     * Creates a new city requiring defense.
     *
     * @param id            Unique city identifier.
     * @param numberEnemies Invading force size that a defender team must match or exceed.
     */
    public City(int id, int numberEnemies) {
        this.id = id;
        this.numberEnemies = numberEnemies;
        this.defenderTeamId = -1; // -1 indicates no team assigned yet
    }

    /** @return Unique city identifier. */
    public int getId() {
        return id;
    }

    /** @return Number of enemy units attacking the city. */
    public int getNumberEnemies() {
        return numberEnemies;
    }

    /** @return ID of the assigned defender team, or {@code -1} if unassigned. */
    public int getDefenderTeamId() {
        return defenderTeamId;
    }

    /**
     * Assigns a defender team to protect this city.
     *
     * @param defenderTeamId ID of the deployed defender team.
     */
    public void setDefenderTeamId(int defenderTeamId) {
        this.defenderTeamId = defenderTeamId;
    }

    /**
     * Compares cities by their enemy force size in ascending order.
     *
     * @param other Another city to compare against.
     * @return Negative, zero, or positive depending on whether this city has fewer, equal, or more enemies.
     */
    @Override
    public int compareTo(City other) {
        // Java modern standard for comparing primitive fields
        return Integer.compare(this.numberEnemies, other.numberEnemies);
    }
}