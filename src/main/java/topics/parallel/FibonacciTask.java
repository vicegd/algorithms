package topics.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * <h1>Parallel Fibonacci (Fork/Join)</h1>
 * <p>
 * Computes the Fibonacci sequence utilizing Java's Fork/Join parallel execution model.
 * The task recursively splits the workload into smaller asynchronous sub-tasks 
 * until a predefined granularity threshold is reached, at which point it delegates 
 * the computation to the sequential algorithm.
 * </p>
 *
 * @author vicegd
 * @see FibonacciAlgorithm
 */
public class FibonacciTask extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;
    
    /** * The granularity threshold. Sub-problems smaller than this size are evaluated 
     * sequentially to avoid the overhead of thread context switching and task allocation.
     */
    private static final int THRESHOLD = 15;
    
    private final int targetIndex;
    
    /**
     * Initializes the parallel task.
     * <p>
     * <i>Note: Accepting a primitive int instead of an object instance significantly 
     * reduces heap memory allocation and Garbage Collector pressure during deep recursion.</i>
     * </p>
     *
     * @param targetIndex The mathematical index (N) to compute.
     */
    public FibonacciTask(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * The primary computation logic that dictates whether to fork the problem 
     * or solve it sequentially.
     *
     * @return The computed Fibonacci sequence value.
     */
    @Override
    protected Long compute() {
        // Base Case: Problem is small enough to solve sequentially without thread overhead
        if (targetIndex < THRESHOLD) { 
            return new FibonacciAlgorithm(targetIndex).solve();
        }

        // Recursive Case: Split the problem
        var subTask1 = new FibonacciTask(targetIndex - 1);
        var subTask2 = new FibonacciTask(targetIndex - 2);

        // Fork the first task to be executed asynchronously in another thread
        subTask1.fork();
        
        // Compute the second task synchronously in the current thread
        long result2 = subTask2.compute(); 
        
        // Block and wait for the asynchronous task to complete, then aggregate
        long result1 = subTask1.join();

        return result1 + result2;
    }
}