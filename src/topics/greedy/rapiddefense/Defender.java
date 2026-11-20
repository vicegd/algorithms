package topics.greedy.rapiddefense;

/**
 * <h1>Defender Team Model</h1>
 * <p>
 * Represents a defense unit available for deployment in the Rapid Defense greedy problem.
 * Implements {@link Comparable} so teams can be sorted in ascending order of troop capacity,
 * allowing the greedy algorithm to assign the smallest sufficient team to each city.
 * </p>
 *
 * @author vicegd
 */
public class Defender implements Comparable<Defender> {
    /** Unique identifier of the defender team. */
    private final int id;
    /** Number of troops in this team (defense capacity). */
    private final int numberDefenders;
    /** Whether this team has already been deployed to a city. */
    private boolean assigned;

    /**
     * Creates a new unassigned defender team.
     *
     * @param id              Unique team identifier.
     * @param numberDefenders Troop strength available in this team.
     */
    public Defender(int id, int numberDefenders) {
        this.id = id;
        this.numberDefenders = numberDefenders;
        this.assigned = false;
    }

    /** @return Unique team identifier. */
    public int getId() {
        return id;
    }

    /** @return Number of troops in the team. */
    public int getNumberDefenders() {
        return numberDefenders;
    }

    /** @return {@code true} if this team is already deployed to a city. */
    public boolean isAssigned() {
        return assigned;
    }

    /**
     * Updates the deployment state of this defender team.
     *
     * @param assigned {@code true} when deployed to a city.
     */
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * Compares defender teams by troop capacity in ascending order.
     *
     * @param other Another defender team to compare against.
     * @return Negative, zero, or positive depending on whether this team has fewer, equal, or more troops.
     */
    @Override
    public int compareTo(Defender other) {
        // Java modern standard for comparing primitive fields
        return Integer.compare(this.numberDefenders, other.numberDefenders);
    }
}