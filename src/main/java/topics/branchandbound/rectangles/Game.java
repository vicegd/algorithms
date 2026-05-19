package topics.branchandbound.rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import topics.branchandbound.Node;

/**
 * <h1>Board State for Rectangle Placement</h1>
 * <p>
 * Represents a distinct physical configuration of the board within the execution 
 * tree of the <strong>Branch and Bound</strong> algorithm. It manages the insertion 
 * of rectangular pieces, enforces geometric constraints (boundaries, overlaps, adjacency), 
 * and calculates the heuristic bounding box area.
 * </p>
 *
 * @author vicegd
 */
class Game extends Node {
    private final int[][] board;
    private final List<Piece> pieces;

    /**
     * Constructs the root node representing an empty board.
     *
     * @param boardSize The dimension of the square board (N &times; N).
     * @param pieces    The sequence of pieces to be placed on the board.
     */
    public Game(int boardSize, List<Piece> pieces) {
        super();
        this.board = new int[boardSize][boardSize];
        this.pieces = pieces;
    }

    /**
     * Constructs a child node representing a subsequent placement state.
     *
     * @param board    The new board configuration matrix.
     * @param pieces   The sequence of pieces pending placement.
     * @param depth    The current depth in the state space tree.
     * @param parentId The unique identifier of the parent node.
     */
    public Game(int[][] board, List<Piece> pieces, int depth, UUID parentId) {
        super();
        this.board = board;
        this.pieces = pieces;
        this.depth = depth;
        this.parentId = parentId;
        calculateHeuristicValue();
    }

    /**
     * Generates all mathematically valid configurations extending from 
     * the current state by placing the next available piece.
     *
     * @return A list containing the resulting child nodes.
     */
    @Override
    public List<Node> expand() {
        var children = new ArrayList<Node>();
        var validPlacements = evaluateAllPossiblePlacements();
        
        for (int[][] nextBoard : validPlacements) {
            children.add(new Game(nextBoard, pieces, depth + 1, this.getId()));
        }
        
        return children;
    }

    /**
     * Iterates over every cell to attempt placing the piece in both orientations.
     *
     * @return A list of valid board matrices containing the new piece.
     */
    private List<int[][]> evaluateAllPossiblePlacements() {
        var validBoards = new ArrayList<int[][]>();
        
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                int[][] horizontalPlacement = tryPositionNewPiece(row, col, PieceOrientation.HORIZONTAL);
                if (horizontalPlacement != null) {
                    validBoards.add(horizontalPlacement);
                }

                int[][] verticalPlacement = tryPositionNewPiece(row, col, PieceOrientation.VERTICAL);
                if (verticalPlacement != null) {
                    validBoards.add(verticalPlacement);
                }
            }
        }
        
        return validBoards;
    }
    
    /**
     * Attempts to place the current piece at the specified coordinates and orientation.
     *
     * @param row         The target horizontal coordinate (row).
     * @param col         The target vertical coordinate (column).
     * @param orientation The rotation of the piece.
     * @return A new board matrix if the placement is geometrically valid; <code>null</code> otherwise.
     */
    private int[][] tryPositionNewPiece(int row, int col, PieceOrientation orientation) {
        int[][] nextBoard = copyBoard();
        
        boolean isValid = insertNewPiece(row, col, orientation, nextBoard, pieces.get(depth));
        return isValid ? nextBoard : null;
    }
    
    /**
     * Validates and applies the insertion of a piece onto the board matrix.
     * Imposes bounds checking, overlap prevention, and adjacency constraints.
     */
    private boolean insertNewPiece(int row, int col, PieceOrientation orientation, int[][] nextBoard, Piece piece) {
        int rowSpan = (orientation == PieceOrientation.HORIZONTAL) ? piece.x : piece.y;
        int colSpan = (orientation == PieceOrientation.HORIZONTAL) ? piece.y : piece.x;
        
        // Validation: Bounds check
        if (row + rowSpan > nextBoard.length || col + colSpan > nextBoard.length) {
            return false;
        }
        
        // Validation: Overlap check
        for (int i = row; i < row + rowSpan; i++) {
            for (int j = col; j < col + colSpan; j++) {
                if (nextBoard[i][j] != 0) {
                    return false;
                }
            }
        }

        // Validation: Adjacency constraint (must touch at least one existing piece unless it's the first one)
        if (depth > 0 && !hasAdjacentNeighbor(row, col, rowSpan, colSpan, nextBoard)) {
            return false;
        }
        
        // Apply State: Insert the piece marking it with the current step depth
        for (int i = row; i < row + rowSpan; i++) {
            for (int j = col; j < col + colSpan; j++) {
                nextBoard[i][j] = depth + 1;
            }
        }
        
        return true;
    }

    /**
     * Evaluates if the proposed placement touches an existing piece on the board.
     */
    private boolean hasAdjacentNeighbor(int row, int col, int rowSpan, int colSpan, int[][] nextBoard) {
        // Check Bottom Edge
        if (row + rowSpan < nextBoard.length) {
            for (int j = col; j < col + colSpan; j++) {
                if (nextBoard[row + rowSpan][j] != 0) return true;
            }
        }
        // Check Top Edge
        if (row > 0) {
            for (int j = col; j < col + colSpan; j++) {
                if (nextBoard[row - 1][j] != 0) return true;
            }
        }
        // Check Left Edge
        if (col > 0) {
            for (int i = row; i < row + rowSpan; i++) {
                if (nextBoard[i][col - 1] != 0) return true;
            }
        }
        // Check Right Edge
        if (col + colSpan < nextBoard.length) {
            for (int i = row; i < row + rowSpan; i++) {
                if (nextBoard[i][col + colSpan] != 0) return true;
            }
        }
        return false;
    }

    /**
     * Creates a deep copy of the 2D board matrix to preserve parent state.
     */
    private int[][] copyBoard() {
        int[][] copy = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board.length);
        }
        return copy;
    }

    /**
     * Computes the bounding box area of all placed pieces. This serves as the 
     * lower-bound heuristic estimate to prune sub-optimal configurations.
     */
    @Override
    public void calculateHeuristicValue() {
        int minRow = Integer.MAX_VALUE, maxRow = -1;
        int minCol = Integer.MAX_VALUE, maxCol = -1;
        boolean isEmpty = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    isEmpty = false;
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        if (isEmpty) {
            this.heuristicValue = 0;
        } else {
            this.heuristicValue = (maxRow - minRow + 1) * (maxCol - minCol + 1);
        }
    }

    /**
     * Determines whether the current node represents a fully resolved configuration.
     *
     * @return <code>true</code> if all pieces have been successfully placed.
     */
    @Override
    public boolean isSolution() {
        return depth == pieces.size();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("=============\n");
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell);
            }
            sb.append("\n");
        }
        sb.append("=============\n");
        return sb.toString();
    }
}