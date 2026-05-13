package topics.branchandbound.times;

/**
 * Represents a rectangular piece with dimensions x (width) and y (height).
 *
 * Used by the Branch and Bound rectangle placement problem as the item
 * to be placed on the board. A piece may be placed in either
 * {@link PieceOrientation#Horizontal} or {@link PieceOrientation#Vertical} orientation.
 *
 * @author vicegd
 * @see PieceOrientation
 * @see Game
 */
class Piece {
  int x;
  int y;
  
  public Piece(int x, int y) {
    this.x = x;
    this.y = y;    
  }
}
