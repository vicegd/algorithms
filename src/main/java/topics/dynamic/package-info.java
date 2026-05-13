/**
 * Dynamic Programming algorithms — optimisation via overlapping sub-problems.
 *
 * <p>Dynamic Programming (DP) solves problems that exhibit two key properties:</p>
 * <ul>
 *   <li><strong>Optimal substructure</strong> — an optimal solution can be built from
 *       optimal solutions to its sub-problems.</li>
 *   <li><strong>Overlapping sub-problems</strong> — the same sub-problems are solved
 *       multiple times; caching eliminates redundant work.</li>
 * </ul>
 *
 * <h2>Two Complementary Approaches</h2>
 * <ul>
 *   <li><strong>Top-down (memoisation)</strong> — recursive with a cache; natural to
 *       write, but carries call-stack overhead.</li>
 *   <li><strong>Bottom-up (tabulation)</strong> — iterative table filled in dependency
 *       order; avoids recursion and is often faster in practice.</li>
 * </ul>
 *
 * <h2>Difference from Divide &amp; Conquer</h2>
 * <p>Divide &amp; Conquer splits into <em>independent</em> sub-problems and solves each
 * exactly once. DP splits into <em>overlapping</em> sub-problems and stores results to
 * avoid recomputation.</p>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.dynamic.Change} — minimum-coins coin-change (bottom-up DP)</li>
 *   <li>{@link topics.dynamic.Combinations} — combinatorial counting via Pascal's triangle</li>
 *   <li>{@link topics.dynamic.Fibonacci} — Fibonacci with memoisation and tabulation</li>
 *   <li>{@link topics.dynamic.Knapsack01} — 0/1 knapsack problem</li>
 *   <li>{@link topics.dynamic.RiverTravel} — minimum-cost river crossing</li>
 * </ul>
 */
package topics.dynamic;
