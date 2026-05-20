package topics.greedy.rapiddefense;

/**
 * Represents a team of defenders available for deployment.
 */
public class Defender implements Comparable<Defender> {
    private final int id;
    private final int numberDefenders;
    private boolean assigned;

    public Defender(int id, int numberDefenders) {
        this.id = id;
        this.numberDefenders = numberDefenders;
        this.assigned = false;
    }

    public int getId() {
        return id;
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

    @Override
    public int compareTo(Defender other) {
        // Java modern standard for comparing primitive fields
        return Integer.compare(this.numberDefenders, other.numberDefenders);
    }
}