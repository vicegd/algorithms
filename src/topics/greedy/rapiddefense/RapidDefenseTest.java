package topics.greedy.rapiddefense;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Rapid Defense - Greedy Allocation")
class RapidDefenseTest {
    private RapidDefense engine;
    private List<City> cities;
    private List<Defender> defenders;

    @BeforeEach
    void setup() {
        engine = new RapidDefense();
        cities = new ArrayList<>();
        defenders = new ArrayList<>();
        
        // Scenario: 4 Cities, 4 Defender Teams
        cities.add(new City(0, 100)); // City 0 needs 100
        cities.add(new City(1, 200)); // City 1 needs 200
        cities.add(new City(2, 500)); // City 2 needs 500
        cities.add(new City(3, 800)); // City 3 needs 800

        defenders.add(new Defender(0, 150)); // Team 0 has 150
        defenders.add(new Defender(1, 550)); // Team 1 has 550
        defenders.add(new Defender(2, 50));  // Team 2 has 50
        defenders.add(new Defender(3, 210)); // Team 3 has 210
        
        // Mathematical Maximum Victories: 3
        // Team 0 (150) beats City 0 (100)
        // Team 3 (210) beats City 1 (200)
        // Team 1 (550) beats City 2 (500)
        // Team 2 (50) is sacrificed to City 3 (800)
    }

    @Test
    @DisplayName("Naive O(N²) approach should maximize victories")
    void shouldMaximizeVictoriesBasic() {
        engine.assignBasic(cities, defenders);
        int victories = engine.countVictories(cities, defenders);
        assertEquals(3, victories, "Naive algorithm failed to find the optimal victory count.");
    }

    @Test
    @DisplayName("Optimized O(N log N) approach should maximize victories identically")
    void shouldMaximizeVictoriesQuick() {
        engine.assignQuick(cities, defenders);
        int victories = engine.countVictories(cities, defenders);
        assertEquals(3, victories, "Optimized algorithm failed to find the optimal victory count.");
    }
}