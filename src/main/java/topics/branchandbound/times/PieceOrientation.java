package topics.branchandbound.times;

/**
 * Orientation of a rectangular piece on the placement board.
 *
 * A piece with dimensions (x, y) placed {@code Horizontal} occupies x columns
 * and y rows; placed {@code Vertical} it occupies y columns and x rows.
 *
 * @author vicegd
 * @see Piece
 */
enum PieceOrientation {
  Horizontal,
  Vertical
}