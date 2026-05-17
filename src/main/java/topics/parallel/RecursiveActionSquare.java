package topics.parallel;

import java.util.concurrent.RecursiveAction;

/**
 * <h1>Parallel Array Squaring (Fork/Join)</h1>
 * <p>
 * Implements a parallel divide-and-conquer strategy using {@link RecursiveAction} 
 * to compute the square of each integer in a shared array in-place.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Total work remains linear, but runtime is reduced structurally by a factor proportional to the active hardware core count.</li>
 * <li><strong>Space Complexity:</strong> <code>O(log N)</code> - Stack depth of the thread pool corresponds directly to the binary decomposition height.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RecursiveActionSquare extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    
    private static final int SEQUENTIAL_THRESHOLD = 100;
    
    private final int[] data;
    private final int start;
    private final int end;
    
    /**
     * Initializes the parallel action for a targeted segment of the array.
     *
     * @param data  The shared array to mutate in-place.
     * @param start The inclusive starting boundary index.
     * @param end   The exclusive ending boundary index.
     */
    public RecursiveActionSquare(int[] data, int start, int end) { 
        this.data = data; 
        this.start = start; 
        this.end = end; 
    } 
    
    @Override
    protected void compute() {
        int workloadSize = end - start;
        
        // Base Case: Segment size falls below the performance threshold; execute sequentially.
        if (workloadSize < SEQUENTIAL_THRESHOLD) { 
            for (int i = start; i < end; i++) {           
                data[i] = data[i] * data[i]; 
            } 
        } else { 
            // Recursive Case: Bisect the segment space to spawn complementary asynchronous tasks.
            int middle = start + (workloadSize / 2);
            
            var leftTask = new RecursiveActionSquare(data, start, middle);
            var rightTask = new RecursiveActionSquare(data, middle, end);
            
            invokeAll(leftTask, rightTask); 
        } 
    }
}