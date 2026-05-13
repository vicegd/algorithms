/**
 * Utility classes shared by branch-and-bound implementations.
 *
 * <p>Provides the priority-queue-based infrastructure (heap and node structures)
 * and the core best-first search logic used by the branch-and-bound algorithms
 * in {@link topics.branchandbound}.</p>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.branchandbound.util.BranchAndBound} — best-first B&amp;B driver</li>
 *   <li>{@link topics.branchandbound.util.Heap} — min/max heap implementation</li>
 *   <li>{@link topics.branchandbound.util.Node} — search-tree node with cost and bound</li>
 * </ul>
 */
package topics.branchandbound.util;
