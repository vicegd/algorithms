package topics.greedy.rapiddefense;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Empirical Runtime Analysis</h1>
 * <p>Demonstrates how the Naive O(N²) approach scales terribly compared to the O(N log N) approach.</p>
 */
public class RapidDefenseBenchmark {
    private static final Logger log = LoggerFactory.getLogger(RapidDefenseBenchmark.class);

    public static void main(String[] args) {
        RapidDefense engine = new RapidDefense();

        System.out.println("=========================================================");
        System.out.println("  RAPID DEFENSE BENCHMARK: O(N²) vs O(N log N)");
        System.out.println("=========================================================");

        // Cap at 65536 to prevent memory heap space exhaustion and overly long waits
        for (int n = 512; n <= 65536; n *= 2) {
            List<City> citiesBasic = generateCities(n);
            List<Defender> defendersBasic = generateDefenders(n);
            
            // Clone data for the quick algorithm to ensure fairness
            List<City> citiesQuick = new ArrayList<>(citiesBasic);
            List<Defender> defendersQuick = new ArrayList<>(defendersBasic);

            // Measure Basic Algorithm O(N²)
            long startBasic = System.currentTimeMillis();
            engine.assignBasic(citiesBasic, defendersBasic);
            long endBasic = System.currentTimeMillis();

            // Measure Quick Algorithm O(N log N)
            long startQuick = System.currentTimeMillis();
            engine.assignQuick(citiesQuick, defendersQuick);
            long endQuick = System.currentTimeMillis();

            System.out.printf("Scale N = %-6d | Naive O(N²): %-5d ms | Optimized O(N log N): %-4d ms%n", 
                              n, (endBasic - startBasic), (endQuick - startQuick));
        }
    }

    private static List<City> generateCities(int n) {
        List<City> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new City(i, ThreadLocalRandom.current().nextInt(1, 1000)));
        }
        return list;
    }

    private static List<Defender> generateDefenders(int n) {
        List<Defender> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new Defender(i, ThreadLocalRandom.current().nextInt(1, 1000)));
        }
        return list;
    }
}