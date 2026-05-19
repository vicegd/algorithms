package topics.parallel.cuberoot;

import java.util.concurrent.RecursiveAction;

/**
 * <h1>Parallel Array Transformation (Fork/Join)</h1>
 * <p>
 * Demonstrates the divide-and-conquer pattern utilizing {@link RecursiveAction} 
 * to mutate a shared array in-place. It calculates the mathematical cube root 
 * for each element, distributing the workload across multiple threads.
 * </p>
 *
 * @author vicegd
 */
public class RecursiveActionComparison extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    
    private final int[] data;
    private final int start;
    private final int end;
    private final int threshold;
    
    /**
     * Initializes the parallel action for a specific segment of the array.
     *
     * @param data      The shared array to be mutated in-place.
     * @param start     The inclusive starting index of the segment.
     * @param end       The exclusive ending index of the segment.
     * @param threshold The granularity limit. Segments smaller than this are processed sequentially.
     */
    public RecursiveActionComparison(int[] data, int start, int end, int threshold) { 
        this.data = data; 
        this.start = start; 
        this.end = end; 
        this.threshold = threshold;
    } 
    
    @Override
    protected void compute() {
        int workloadSize = end - start;
        
        // Base Case: If the workload is small enough, process it sequentially
        if (workloadSize < threshold) { 
            // Time-consuming CPU operation to make parallel speedup observable
            for (int i = start; i < end; i++) { 
                data[i] = (int) Math.cbrt(data[i]); 
            } 
        } else { 
            // Recursive Case: Divide the array segment exactly in half
            int middle = start + (workloadSize / 2); 
            
            var leftTask = new RecursiveActionComparison(data, start, middle, threshold);
            var rightTask = new RecursiveActionComparison(data, middle, end, threshold);
            
            // Invoke both sub-tasks to be scheduled in the ForkJoinPool
            invokeAll(leftTask, rightTask); 
        } 
    }
}