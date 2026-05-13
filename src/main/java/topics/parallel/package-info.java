/**
 * Parallel algorithms using Java's Fork/Join Framework.
 *
 * <p>Parallel algorithms divide work among multiple processor cores to reduce
 * wall-clock time. This package demonstrates parallelisation through the
 * divide-and-conquer paradigm using {@link java.util.concurrent.ForkJoinPool}
 * and its {@link java.util.concurrent.RecursiveAction} /
 * {@link java.util.concurrent.RecursiveTask} abstractions.</p>
 *
 * <h2>Key Concepts</h2>
 * <ul>
 *   <li><strong>Work splitting</strong> — sub-problems below a threshold are solved
 *       sequentially to amortise task-creation overhead.</li>
 *   <li><strong>Work stealing</strong> — idle threads steal tasks from busy threads'
 *       queues, maximising CPU utilisation.</li>
 *   <li><strong>Amdahl's Law</strong> — speedup is bounded by the sequential fraction
 *       of the computation.</li>
 * </ul>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.parallel.FibonacciAlgorithm} — sequential Fibonacci baseline</li>
 *   <li>{@link topics.parallel.FibonacciTask} — parallel Fibonacci via {@code RecursiveTask}</li>
 *   <li>{@link topics.parallel.FileProcessingTask} — parallel file processing</li>
 *   <li>{@link topics.parallel.RecursiveActionComparison} — compares sequential vs parallel actions</li>
 *   <li>{@link topics.parallel.RecursiveActionSquare} — parallel squaring via {@code RecursiveAction}</li>
 *   <li>{@link topics.parallel.RecursiveTaskSum} — parallel array sum via {@code RecursiveTask}</li>
 * </ul>
 */
package topics.parallel;
