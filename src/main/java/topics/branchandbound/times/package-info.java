/**
 * Execution-time benchmarks for branch-and-bound algorithms.
 *
 * <p>Classes in this package measure and compare the running times of the
 * branch-and-bound implementations under different input sizes and thread counts,
 * providing empirical evidence to complement their theoretical analyses.</p>
 *
 * <h2>Classes in This Package</h2>
 * <ul>
 *   <li>{@link topics.branchandbound.times.Game} — game-state representation used in benchmarks</li>
 *   <li>{@link topics.branchandbound.times.Piece} — piece model for rectangle-placement benchmarks</li>
 *   <li>{@link topics.branchandbound.times.PieceOrientation} — orientation variants for a piece</li>
 *   <li>{@link topics.branchandbound.times.RectanglesPlacementTestTime} — single-threaded time test</li>
 *   <li>{@link topics.branchandbound.times.RectanglesPlacementTestTimeThreads} — multi-threaded time test</li>
 * </ul>
 */
package topics.branchandbound.times;
