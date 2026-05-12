package topics.greedy;

import java.util.Arrays;
import java.util.Objects;

/**
 * Single-plumber scheduling utility.
 *
 * This class models the classic scheduling problem where one plumber handles
 * a sequence of tasks with known durations. The order of tasks determines the
 * total customer waiting time.
 *
 * For a fixed order, the waiting-time sum is computed in O(n). For an
 * optimal order (Shortest Processing Time first), use
 * {@link #getOptimalTotalTimeOfWait()}.
 *
 * @author vicegd
 */
public class Plumber {
        private final int[] taskDurations;

        /**
         * Builds a plumber instance with task durations.
         *
         * @param tasks duration of each task in the execution order
         * @throws NullPointerException if {@code tasks} is null
         * @throws IllegalArgumentException if any task duration is negative
         */
        public Plumber(int[] tasks) {
                Objects.requireNonNull(tasks, "tasks cannot be null");
                for (int task : tasks) {
                        if (task < 0) {
                                throw new IllegalArgumentException("task durations must be non-negative");
                        }
                }
                this.taskDurations = Arrays.copyOf(tasks, tasks.length);
        }

        /**
         * Calculates total waiting time for the current task order.
         *
         * Example for durations [2, 5, 4]: waiting times are 2, 7, 11,
         * total = 20.
         *
         * @return total waiting time for the current order
         * @throws ArithmeticException if the accumulated value exceeds {@code int}
         */
        public int getTotalTimeOfWait(){
                long cumulative = 0;
                long time = 0;
                for (int i = 0; i < taskDurations.length; i++){
                        cumulative += time;
                        time += taskDurations[i];
                }
                long result = time + cumulative;
                if (result > Integer.MAX_VALUE) {
                        throw new ArithmeticException("total waiting time exceeds int range");
                }
                return (int) result;
        }

        /**
         * Calculates minimum possible total waiting time (greedy optimal).
         *
         * The optimal strategy is Shortest Processing Time first (SPT): sort task
         * durations ascending, then compute waiting time.
         *
         * @return minimal total waiting time among all possible task orders
         */
        public int getOptimalTotalTimeOfWait() {
                int[] sorted = Arrays.copyOf(taskDurations, taskDurations.length);
                Arrays.sort(sorted);
                long cumulative = 0;
                long time = 0;
                for (int task : sorted) {
                        cumulative += time;
                        time += task;
                }
                long result = time + cumulative;
                if (result > Integer.MAX_VALUE) {
                        throw new ArithmeticException("total waiting time exceeds int range");
                }
                return (int) result;
        }

}

