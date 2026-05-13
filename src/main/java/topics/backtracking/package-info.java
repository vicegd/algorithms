/**
 * Backtracking algorithms — systematic exploration of solution spaces with pruning.
 *
 * <p>Backtracking builds candidate solutions incrementally and abandons a branch
 * ("backtracks") as soon as it determines the branch cannot lead to a valid solution.
 * This avoids the full cost of brute-force enumeration.</p>
 *
 * <h2>Key Difference from Brute Force</h2>
 * <p>Brute force generates every possible candidate before checking validity.
 * Backtracking prunes invalid branches <em>early</em>, often reducing the effective
 * search space from O(n!) or O(2<sup>n</sup>) to a much smaller subset.</p>
 *
 * <h2>When to Use Backtracking</h2>
 * <ul>
 *   <li>Constraint-satisfaction problems (N-Queens, Sudoku)</li>
 *   <li>Combinatorial enumeration (permutations, subsets)</li>
 *   <li>Path-finding on graphs (Hamiltonian path, knight's tour)</li>
 * </ul>
 *
 * <h2>General Template</h2>
 * <pre>
 * void backtrack(List&lt;T&gt; current, List&lt;T&gt; remaining) {
 *     if (isSolution(current)) { record(current); return; }
 *     for (T candidate : remaining) {
 *         if (isValid(current, candidate)) {
 *             current.add(candidate);
 *             backtrack(current, remaining without candidate);
 *             current.remove(candidate);   // undo
 *         }
 *     }
 * }
 * </pre>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.backtracking.ChessHorseAll} — finds all knight's-tour paths</li>
 *   <li>{@link topics.backtracking.ChessHorseOne} — finds one knight's-tour path</li>
 *   <li>{@link topics.backtracking.ChessQueensAll} — finds all N-Queens solutions</li>
 *   <li>{@link topics.backtracking.ChessQueensOne} — finds one N-Queens solution</li>
 *   <li>{@link topics.backtracking.Permutations} — generates all permutations</li>
 *   <li>{@link topics.backtracking.SubsetsGivenSum} — finds subsets with a target sum</li>
 * </ul>
 */
package topics.backtracking;
