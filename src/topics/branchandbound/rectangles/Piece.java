package topics.branchandbound.rectangles;

/**
 * <h1>Rectangular Piece</h1>
 * <p>
 * Represents a distinct rectangular entity defined by its width and height, 
 * utilized as the fundamental unit of placement within the 2D bin packing 
 * variants of the Branch and Bound algorithm.
 * </p>
 * <p>
 * The piece's effective footprint on the board is determined dynamically by 
 * the algorithmic engine based on its assigned {@link PieceOrientation}.
 * </p>
 *
 * @author vicegd
 * @see PieceOrientation
 * @see Game
 */
class Piece {
    /** The primary horizontal dimension (width). */
    int x;
    
    /** The primary vertical dimension (height). */
    int y;
    
    /**
     * Constructs a new rectangular piece with the specified dimensions.
     *
     * @param x The width of the piece.
     * @param y The height of the piece.
     */
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;    
    }
}