package topics.branchandbound.times;

/**
 * <h1>Piece Orientation</h1>
 * <p>
 * Defines the permissible geometric alignments for placing a rectangular 
 * {@link Piece} onto the algorithmic execution board.
 * </p>
 * <p>
 * The orientation dictates how the intrinsic dimensions of the piece map 
 * directly to the 2D matrix grid:
 * </p>
 * <ul>
 * <li><strong>HORIZONTAL:</strong> The piece maintains its native dimensions, extending <i>x</i> units across columns and <i>y</i> units down rows.</li>
 * <li><strong>VERTICAL:</strong> The piece's dimensions are mathematically transposed, extending <i>y</i> units across columns and <i>x</i> units down rows.</li>
 * </ul>
 *
 * @author vicegd
 * @see Piece
 * @see Game
 */
enum PieceOrientation {
    /** Represents the default structural alignment. */
    HORIZONTAL,
    
    /** Represents a 90-degree rotational transposition. */
    VERTICAL
}