package topics.greedy.plumber;

import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Single-Plumber Scheduling</h1>
 * <p>
 * This class models the classic scheduling problem where a single worker handles
 * a sequence of tasks with known durations. The order of execution strictly determines 
 * the total accumulated customer waiting time.
 * </p>
 *
 * <h2>The Greedy Strategy</h2>
 * <p>
 * To minimize the total waiting time for all customers, the optimal greedy choice 
 * is the <strong>Shortest Processing Time (SPT) first</strong> rule. By sorting the tasks 
 * in ascending order of their durations, we delay the fewest number of subsequent tasks.
 * </p>
 *
 * @author vicegd
 */
public class Plumber {
    private static final Logger log = LoggerFactory.getLogger(Plumber.class);
    private final int[] taskDurations;

    /**
     * Builds a plumber instance with task durations.
     *
     * @param tasks Duration of each task in the provided execution order.
     * @throws NullPointerException if {@code tasks} is null.
     * @throws IllegalArgumentException if any task duration is negative.
     */
    public Plumber(int[] tasks) {
        Objects.requireNonNull(tasks, "Tasks array cannot be null.");
        for (int task : tasks) {
            if (task < 0) {
                throw new IllegalArgumentException("Task durations must be non-negative.");
            }
        }
        // Defensive copy to guarantee immutability
        this.taskDurations = Arrays.copyOf(tasks, tasks.length);
    }

    /**
     * Calculates total waiting time for the <strong>current</strong> task order.
     * <br>
     * <em>Example for durations [2, 5, 4]:</em>
     * Waiting times are 2, (2+5=7), (7+4=11). Total = 2 + 7 + 11 = 20.
     *
     * @return Total waiting time for the current order.
     * @throws ArithmeticException if the accumulated value exceeds integer bounds.
     */
    public int getTotalTimeOfWait() {
        long cumulative = 0;
        long time = 0;
        
        for (int i = 0; i < taskDurations.length; i++) {
            cumulative += time;
            time += taskDurations[i];
            
            if (log.isTraceEnabled()) {
                log.trace("Task {}: Duration = {}, Wait Time for this task = {}, Running Sum = {}", 
                          i, taskDurations[i], time, cumulative + time);
            }
        }
        
        long result = time + cumulative;
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("Total waiting time exceeds integer capacity.");
        }
        return (int) result;
    }

    /**
     * Calculates minimum possible total waiting time (The Greedy Optimum).
     * <br>
     * Sorts task durations ascending, then computes the accumulated waiting time.
     *
     * @return Minimal total waiting time among all possible task permutations.
     */
    public int getOptimalTotalTimeOfWait() {
        int[] sortedTasks = Arrays.copyOf(taskDurations, taskDurations.length);
        Arrays.sort(sortedTasks); // The Greedy Step: O(N log N)
        
        if (log.isTraceEnabled()) {
            log.trace("Optimized Greedy Order: {}", Arrays.toString(sortedTasks));
        }

        long cumulative = 0;
        long time = 0;
        
        for (int task : sortedTasks) {
            cumulative += time;
            time += task;
        }
        
        long result = time + cumulative;
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("Total waiting time exceeds integer capacity.");
        }
        return (int) result;
    }
}