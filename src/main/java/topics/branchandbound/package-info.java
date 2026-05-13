/**
 * Branch-and-Bound algorithms — intelligent search for combinatorial optimisation.
 *
 * <p>Branch and Bound systematically enumerates candidate solutions by partitioning
 * the search space into sub-problems (branching) while using upper/lower bounds to
 * discard sub-problems that cannot yield a solution better than the current best
 * (bounding). It guarantees optimality while avoiding full enumeration.</p>
 *
 * <h2>Core Steps</h2>
 * <ol>
 *   <li><strong>Branch:</strong> partition the current node into child nodes.</li>
 *   <li><strong>Bound:</strong> compute a bound on the optimal value reachable from each child.</li>
 *   <li><strong>Prune:</strong> discard any child whose bound is worse than the best solution found so far.</li>
 *   <li><strong>Select:</strong> choose the next node to expand (best-first, depth-first, etc.).</li>
 * </ol>
 *
 * <h2>Comparison with Related Techniques</h2>
 * <ul>
 *   <li><strong>Backtracking</strong> — explores all valid solutions; no cost bounds.</li>
 *   <li><strong>Dynamic Programming</strong> — solves overlapping sub-problems; polynomial time.</li>
 *   <li><strong>Branch &amp; Bound</strong> — optimal solution via bounding; variable time.</li>
 * </ul>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.branchandbound.AgentsTasks} — optimal assignment of agents to tasks</li>
 *   <li>{@link topics.branchandbound.EightPuzzle} — 8-puzzle solver</li>
 *   <li>{@link topics.branchandbound.RectanglesPlacement} — optimal rectangle packing</li>
 *   <li>{@link topics.branchandbound.RectanglesPlacementThreads} — multi-threaded variant</li>
 * </ul>
 */
package topics.branchandbound;
