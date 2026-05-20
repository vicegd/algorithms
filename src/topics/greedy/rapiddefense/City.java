package topics.greedy.rapiddefense;

/**
 * Represents a city invaded by enemies.
 */
public class City implements Comparable<City> {
    private final int id;
    private final int numberEnemies;
    private int defenderTeamId; 

    public City(int id, int numberEnemies) {
        this.id = id;
        this.numberEnemies = numberEnemies;
        this.defenderTeamId = -1; // -1 indicates no team assigned yet
    }

    public int getId() {
        return id;
    }

    public int getNumberEnemies() {
        return numberEnemies;
    }

    public int getDefenderTeamId() {
        return defenderTeamId;
    }

    public void setDefenderTeamId(int defenderTeamId) {
        this.defenderTeamId = defenderTeamId;
    }

    @Override
    public int compareTo(City other) {
        // Java modern standard for comparing primitive fields
        return Integer.compare(this.numberEnemies, other.numberEnemies);
    }
}