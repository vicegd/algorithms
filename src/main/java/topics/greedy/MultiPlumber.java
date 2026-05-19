package topics.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Multi-Plumber Scheduling</h1>
 * <p>
 * Models the assignment of N tasks to K independent workers (plumbers) to minimize 
 * the total accumulated customer waiting time across all workers.
 * </p>
 *
 * <h2>The Greedy Strategy</h2>
 * <p>
 * To achieve the mathematical optimum, we apply two principles:
 * <ol>
 * <li><strong>SPT (Shortest Processing Time):</strong> Sort tasks ascending.</li>
 * <li><strong>Round-Robin Distribution:</strong> Distribute the sorted tasks cyclically 
 * across all available workers to perfectly balance the cascading wait times.</li>
 * </ol>
 * </p>
 *
 * @author vicegd
 */
public class MultiPlumber {
    private static final Logger log = LoggerFactory.getLogger(MultiPlumber.class);

    /**
     * Calculates the optimal total waiting time using Greedy principles.
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N log N) - Bound by sorting.</li>
     * <li><strong>Space Complexity:</strong> O(N + K) - For task assignment lists.</li>
     * </ul>
     *
     * @param tasks Array of incoming task durations.
     * @param numPlumbers Number of available plumbers to distribute the workload.
     * @return The minimum possible global waiting time.
     */
    public int calculateOptimalWaitTime(int[] tasks, int numPlumbers) {
        if (tasks == null || tasks.length == 0 || numPlumbers <= 0) return 0;

        int[] sortedTasks = tasks.clone();
        Arrays.sort(sortedTasks); // 1. SPT Rule

        List<List<Integer>> assignments = initializePlumbers(numPlumbers);

        // 2. Round-Robin Distribution
        for (int i = 0; i < sortedTasks.length; i++) {
            int targetPlumber = i % numPlumbers;
            assignments.get(targetPlumber).add(sortedTasks[i]);
        }

        if (log.isTraceEnabled()) {
            log.trace("--- OPTIMAL GREEDY ASSIGNMENT ---");
        }
        return calculateTotalWaitTime(assignments);
    }

    /**
     * Simulates a chaotic, unoptimized assignment by distributing tasks randomly.
     * Used pedagogically to contrast against the optimal greedy strategy.
     *
     * @param tasks Array of incoming task durations.
     * @param numPlumbers Number of available plumbers to distribute the workload.
     * @return The total global waiting time (usually heavily sub-optimal).
     */
    public int calculateRandomWaitTime(int[] tasks, int numPlumbers) {
        if (tasks == null || tasks.length == 0 || numPlumbers <= 0) return 0;

        List<List<Integer>> assignments = initializePlumbers(numPlumbers);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int task : tasks) {
            int targetPlumber = random.nextInt(numPlumbers);
            assignments.get(targetPlumber).add(task);
        }

        if (log.isTraceEnabled()) {
            log.trace("--- RANDOM (SUB-OPTIMAL) ASSIGNMENT ---");
        }
        return calculateTotalWaitTime(assignments);
    }

    /**
     * Helper method to calculate the sum of completion times for all customers.
     */
    private int calculateTotalWaitTime(List<List<Integer>> assignments) {
        int globalTotalWait = 0;

        for (int i = 0; i < assignments.size(); i++) {
            int time = 0;
            int cumulativeWait = 0;
            
            for (int taskDuration : assignments.get(i)) {
                cumulativeWait += time;
                time += taskDuration;
            }
            
            // The wait time for a single plumber is the sum of completion times
            int plumberTotal = time + cumulativeWait;
            globalTotalWait += plumberTotal;

            if (log.isTraceEnabled()) {
                log.trace("Plumber {} handles tasks: {}. Cumulative wait sum: {}", 
                          i, assignments.get(i), plumberTotal);
            }
        }
        return globalTotalWait;
    }

    private List<List<Integer>> initializePlumbers(int numPlumbers) {
        List<List<Integer>> list = new ArrayList<>(numPlumbers);
        for (int i = 0; i < numPlumbers; i++) {
            list.add(new ArrayList<>());
        }
        return list;
    }
}