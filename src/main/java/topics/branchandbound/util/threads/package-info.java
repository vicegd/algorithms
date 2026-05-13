/**
 * Multi-threaded branch-and-bound utilities.
 *
 * <p>Parallel variants of the core B&amp;B infrastructure that distribute node
 * expansion across multiple worker threads, enabling faster searches on
 * multi-core hardware.</p>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.branchandbound.util.threads.BranchAndBoundThreads} — thread-pool B&amp;B driver</li>
 *   <li>{@link topics.branchandbound.util.threads.HeapThreads} — thread-safe heap</li>
 *   <li>{@link topics.branchandbound.util.threads.WorkerThread} — individual search worker</li>
 * </ul>
 */
package topics.branchandbound.util.threads;
