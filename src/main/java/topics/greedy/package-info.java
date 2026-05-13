/**
 * Greedy algorithms — globally optimal solutions through locally optimal choices.
 *
 * <p>A greedy algorithm always selects the locally best option at each step without
 * reconsidering previous decisions. When the problem satisfies the
 * <em>greedy-choice property</em> and <em>optimal substructure</em>, the greedy strategy
 * yields a globally optimal solution — usually in O(n log n) or better.</p>
 *
 * <h2>When Greedy Works</h2>
 * <ul>
 *   <li><strong>Greedy-choice property</strong> — a locally optimal choice is always part
 *       of some globally optimal solution.</li>
 *   <li><strong>Optimal substructure</strong> — the optimal solution to the whole problem
 *       contains optimal solutions to its sub-problems.</li>
 * </ul>
 *
 * <h2>When Greedy Fails</h2>
 * <p>Consider coin change with denominations {1, 3, 4} and target 6:</p>
 * <ul>
 *   <li>Greedy picks 4 + 1 + 1 = <strong>3 coins</strong></li>
 *   <li>Optimal is 3 + 3 = <strong>2 coins</strong></li>
 * </ul>
 * <p>In such cases, Dynamic Programming or Branch &amp; Bound is required.</p>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.greedy.AgentsTasks} — greedy assignment of agents to tasks</li>
 *   <li>{@link topics.greedy.Change} — greedy coin-change (may not be optimal)</li>
 *   <li>{@link topics.greedy.ChessHorse} — greedy knight's-tour heuristic</li>
 *   <li>{@link topics.greedy.ChessHorseSimpleHeuristic} — simpler knight's-tour heuristic</li>
 *   <li>{@link topics.greedy.FilesDisc1} — greedy file-packing on a disc (variant 1)</li>
 *   <li>{@link topics.greedy.FilesDisc2} — greedy file-packing on a disc (variant 2)</li>
 *   <li>{@link topics.greedy.Knapsack} — fractional knapsack (greedy is optimal)</li>
 *   <li>{@link topics.greedy.Knapsack01} — 0/1 knapsack (greedy is <em>not</em> optimal)</li>
 *   <li>{@link topics.greedy.Plumber} — greedy plumber scheduling</li>
 *   <li>{@link topics.greedy.SomePlumbers} — multi-plumber scheduling variant</li>
 * </ul>
 */
package topics.greedy;
