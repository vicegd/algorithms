package topics.greedy.rapiddefense;

import java.util.Collections;
import java.util.List;

/**
 * <h1>Rapid Defense Assignment</h1>
 * <p>
 * Optimizes the deployment of defender teams to invaded cities to maximize the number of victories.
 * Demonstrates the dramatic performance difference between a Naive O(N²) greedy assignment 
 * and an Optimized O(N log N) greedy assignment via sorting.
 * </p>
 *
 * @author vicegd
 */
public class RapidDefense {
    /**
     * <h2>1. Naive Greedy Assignment O(N²)</h2>
     * <p>
     * Iterates through each city and linearly scans all defenders to find the tightest winning match.
     * If a win is impossible, it scans again for the smallest available team to minimize losses.
     * </p>
     */
    public void assignBasic(List<City> cities, List<Defender> defenders) {
        int n = cities.size();
        
        for (int i = 0; i < n; i++) {
            City currentCity = cities.get(i);
            int bestTeamIndex = findWinningTeam(defenders, currentCity.getNumberEnemies());
            
            // If no team can win, sacrifice the smallest available team
            if (bestTeamIndex == -1) {
                bestTeamIndex = findSmallestAvailableTeam(defenders);
            }
            
            currentCity.setDefenderTeamId(defenders.get(bestTeamIndex).getId());
            defenders.get(bestTeamIndex).setAssigned(true);
        }
    }

    /**
     * <h2>2. Optimized Greedy Assignment O(N log N)</h2>
     * <p>
     * Sorts both collections first. Uses a two-pointer approach to match the weakest winning 
     * defenders to the weakest enemies. If a defender cannot beat the weakest enemy, 
     * it is sacrificed to the strongest enemy.
     * </p>
     */
    public void assignQuick(List<City> cities, List<Defender> defenders) {
        // O(N log N) sorting step
        Collections.sort(cities);
        Collections.sort(defenders);

        int n = cities.size();
        int weakestEnemyIndex = 0; 
        int strongestEnemyIndex = n - 1; 

        // O(N) single pass assignment
        for (int i = 0; i < n; i++) {
            Defender currentDefender = defenders.get(i);
            City weakestCity = cities.get(weakestEnemyIndex);

            if (currentDefender.getNumberDefenders() >= weakestCity.getNumberEnemies()) {
                // Guarantee a victory with minimum wasted troops
                weakestCity.setDefenderTeamId(currentDefender.getId());
                currentDefender.setAssigned(true);
                weakestEnemyIndex++;
            } else {
                // Sacrifice this weak team to the strongest enemy
                City strongestCity = cities.get(strongestEnemyIndex);
                strongestCity.setDefenderTeamId(currentDefender.getId());
                currentDefender.setAssigned(true);
                strongestEnemyIndex--;
            }
        }
    }

    /**
     * Calculates the total number of victories based on the current assignment state.
     */
    public int countVictories(List<City> cities, List<Defender> defenders) {
        int victories = 0;
        for (City city : cities) {
            Defender assignedTeam = getDefenderById(defenders, city.getDefenderTeamId());
            if (assignedTeam != null && assignedTeam.getNumberDefenders() >= city.getNumberEnemies()) {
                victories++;
            }
        }
        return victories;
    }

    // --- Private Helper Methods for the Naive O(N²) approach ---

    private int findWinningTeam(List<Defender> defenders, int enemies) {
        int bestTeamIndex = -1;
        int minimumPeopleNeeded = Integer.MAX_VALUE;

        for (int i = 0; i < defenders.size(); i++) {
            Defender d = defenders.get(i);
            if (!d.isAssigned() && d.getNumberDefenders() >= enemies && d.getNumberDefenders() < minimumPeopleNeeded) {
                minimumPeopleNeeded = d.getNumberDefenders();
                bestTeamIndex = i;
            }
        }
        return bestTeamIndex;
    }

    private int findSmallestAvailableTeam(List<Defender> defenders) {
        int bestTeamIndex = -1;
        int minimumPeople = Integer.MAX_VALUE;

        for (int i = 0; i < defenders.size(); i++) {
            Defender d = defenders.get(i);
            if (!d.isAssigned() && d.getNumberDefenders() < minimumPeople) {
                minimumPeople = d.getNumberDefenders();
                bestTeamIndex = i;
            }
        }
        return bestTeamIndex;
    }

    private Defender getDefenderById(List<Defender> defenders, int id) {
        for (Defender d : defenders) {
            if (d.getId() == id) return d;
        }
        return null;
    }
}