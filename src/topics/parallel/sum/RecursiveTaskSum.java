package topics.parallel.sum;

import java.util.concurrent.RecursiveTask;

/**
 * <h1>Parallel Array Summation (Fork/Join)</h1>
 * <p>
 * Implements a parallel reduction strategy using {@link RecursiveTask} to compute 
 * the mathematical sum of an array of <code>double</code> primitives. It splits the 
 * array into sub-segments until they fall within a specific sequential threshold, 
 * then aggregates fractional calculations up the execution tree.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N)</code> - Distributed work over <i>P</i> cores reduces real-world processing duration near to <code>O(N/P)</code>.</li>
 * <li><strong>Space Complexity:</strong> <code>O(log N)</code> - Stack consumption matches the binary tree depth during splitting.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RecursiveTaskSum extends RecursiveTask<Double> {
    private static final long serialVersionUID = 1L;
    
    private static final int SEQUENTIAL_THRESHOLD = 10_000;
    
    private final double[] data;
    private final int start;
    private final int end;
    
    /**
     * Initializes a sum task for a dedicated boundary segment of the array
     *
     * @param data  The source numerical array.
     * @param start The inclusive starting boundary index.
     * @param end   The exclusive ending boundary index.
     */
    public RecursiveTaskSum(double[] data, int start, int end) { 
        this.data = data; 
        this.start = start; 
        this.end = end; 
    } 
    
    @Override
    protected Double compute() {
        int workloadSize = end - start;
        
        // Base Case: Segment size falls beneath the sequential threshold.
        if (workloadSize < SEQUENTIAL_THRESHOLD) { 
            double partialSum = 0;
            for (int i = start; i < end; i++) { 
                partialSum += data[i]; 
            } 
            return partialSum;
        } 
        
        // Recursive Case: Bisect workload into subtasks to push down the ForkJoinPool
        int middle = start + (workloadSize / 2);
        
        var leftTask = new RecursiveTaskSum(data, start, middle); 
        var rightTask = new RecursiveTaskSum(data, middle, end); 
        
        // Fork the left side asynchronously to another thread worker
        leftTask.fork(); 
        
        // Compute the right task synchronously utilizing the current active thread execution path
        double rightResult = rightTask.compute();
        
        // Join the left asynchronous result (blocks until computation completes) and aggregate
        double leftResult = leftTask.join();
        
        return leftResult + rightResult;
    }
}