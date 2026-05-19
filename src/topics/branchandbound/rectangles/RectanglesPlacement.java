package topics.branchandbound.rectangles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import topics.branchandbound.BranchAndBound;
import topics.branchandbound.Node;

/**
 * <h1>Optimal Placement of Rectangles</h1>
 * <p>
 * This class solves a 2D bin packing variant where a given set of rectangular pieces 
 * must be placed on an <i>N &times; N</i> grid. The objective is to minimize the 
 * bounding box area of the placed pieces. It utilizes a <strong>Branch and Bound</strong> 
 * algorithm to systematically evaluate positions and orientations while pruning sub-optimal 
 * configurations.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O((2 &times; N&sup2;)<sup>P</sup>)</code> - Where <i>N</i> is the board dimension and <i>P</i> is the number of pieces. For each piece, the algorithm explores all grid cells in two orientations. Bounding heuristics heavily prune this theoretical worst-case.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N&sup2;)</code> per state node - Required for storing the 2D matrix representing the board configuration.</li>
 * </ul>
 *
 * @author vicegd
 */
public class RectanglesPlacement extends BranchAndBound {

    /**
     * Initializes the problem solver and establishes the execution tree root.
     *
     * @param boardSize The dimension of the square board (N &times; N).
     * @param pieces    The collection of rectangular pieces to be placed.
     */
    public RectanglesPlacement(int boardSize, List<Piece> pieces) {
        rootNode = new BoardState(boardSize, pieces);
    }
}

/**
 * <p>
 * Represents a distinct physical configuration of the board within the state space tree.
 * It tracks placed rectangles, calculates the bounding area heuristic, and generates 
 * valid subsequent placements.
 * </p>
 */
class BoardState extends Node {
    private final int[][] board;
    private final List<Piece> pieces;

    /**
     * Constructs the root node representing an empty board.
     *
     * @param boardSize The dimension of the square board.
     * @param pieces    The list of pieces pending placement.
     */
    public BoardState(int boardSize, List<Piece> pieces) {
        super();
        this.board = new int[boardSize][boardSize];
        this.pieces = pieces;
    }

    /**
     * Constructs a child node representing a subsequent placement state.
     *
     * @param board    The new board configuration.
     * @param pieces   The list of pieces pending placement.
     * @param depth    The current depth in the state space tree.
     * @param parentID The unique identifier of the parent node.
     */
    public BoardState(int[][] board, List<Piece> pieces, int depth, UUID parentID) {
        super();
        this.board = board;
        this.pieces = pieces;
        this.depth = depth;
        this.parentId = parentID;
        calculateHeuristicValue();
    }

    /**
     * Generates all mathematically valid mathematical configurations extending from 
     * the current state by placing the next available piece.
     *
     * @return A list containing the resulting child nodes.
     */
    @Override
    public ArrayList<Node> expand() {
        var children = new ArrayList<Node>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int[][] horizontalPlacement = tryPositionNewPiece(i, j, PieceOrientation.HORIZONTAL);
                if (horizontalPlacement != null) {
                    children.add(new BoardState(horizontalPlacement, pieces, depth + 1, this.getId()));
                }

                int[][] verticalPlacement = tryPositionNewPiece(i, j, PieceOrientation.VERTICAL);
                if (verticalPlacement != null) {
                    children.add(new BoardState(verticalPlacement, pieces, depth + 1, this.getId()));
                }
            }
        }
        
        return children;
    }

    /**
     * Attempts to place the current piece at the specified coordinates and orientation.
     *
     * @param x           The target horizontal coordinate.
     * @param y           The target vertical coordinate.
     * @param orientation The orientation of the piece.
     * @return A new board matrix if the placement is valid; <code>null</code> otherwise.
     */
    private int[][] tryPositionNewPiece(int x, int y, PieceOrientation orientation) {
        int[][] nextBoard = copyBoard();
        
        boolean isValid = insertNewPiece(x, y, orientation, nextBoard, pieces.get(depth));
        return isValid ? nextBoard : null;
    }

    /**
     * Validates and applies the insertion of a piece onto the board matrix.
     * Imposes bounds checking, overlap prevention, and adjacency constraints.
     */
    private boolean insertNewPiece(int x, int y, PieceOrientation orientation, int[][] nextBoard, Piece piece) {
        int pieceWidth = (orientation == PieceOrientation.HORIZONTAL) ? piece.x : piece.y;
        int pieceHeight = (orientation == PieceOrientation.HORIZONTAL) ? piece.y : piece.x;
        
        // Validation: Bounds check
        if (x + pieceWidth > nextBoard.length || y + pieceHeight > nextBoard.length) {
            return false;
        }
        
        // Validation: Overlap check
        for (int i = x; i < x + pieceWidth; i++) {
            for (int j = y; j < y + pieceHeight; j++) {
                if (nextBoard[i][j] != 0) {
                    return false;
                }
            }
        }

        // Validation: Adjacency constraint (must touch at least one existing piece unless it's the first one)
        if (depth > 0 && !hasAdjacentNeighbor(x, y, pieceWidth, pieceHeight, nextBoard)) {
            return false;
        }
        
        // Apply State: Insert the piece
        for (int i = x; i < x + pieceWidth; i++) {
            for (int j = y; j < y + pieceHeight; j++) {
                nextBoard[i][j] = depth + 1;
            }
        }
        
        return true;
    }

    /**
     * Evaluates if the proposed placement touches an existing piece on the board.
     */
    private boolean hasAdjacentNeighbor(int x, int y, int width, int height, int[][] nextBoard) {
        // Check Bottom Edge
        if (x + width < nextBoard.length) {
            for (int k = y; k < y + height; k++) {
                if (nextBoard[x + width][k] != 0) return true;
            }
        }
        // Check Top Edge
        if (x > 0) {
            for (int k = y; k < y + height; k++) {
                if (nextBoard[x - 1][k] != 0) return true;
            }
        }
        // Check Left Edge
        if (y > 0) {
            for (int k = x; k < x + width; k++) {
                if (nextBoard[k][y - 1] != 0) return true;
            }
        }
        // Check Right Edge
        if (y + height < nextBoard.length) {
            for (int k = x; k < x + width; k++) {
                if (nextBoard[k][y + height] != 0) return true;
            }
        }
        return false;
    }

    /**
     * Creates a deep copy of the 2D board matrix.
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
        int minX = Integer.MAX_VALUE, maxX = -1;
        int minY = Integer.MAX_VALUE, maxY = -1;
        boolean isEmpty = true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    isEmpty = false;
                    minY = Math.min(minY, i);
                    maxY = Math.max(maxY, i);
                    minX = Math.min(minX, j);
                    maxX = Math.max(maxX, j);
                }
            }
        }

        if (isEmpty) {
            this.heuristicValue = 0;
        } else {
            this.heuristicValue = (maxX - minX + 1) * (maxY - minY + 1);
        }
    }

    /**
     * Determines whether the current node represents a fully resolved configuration.
     *
     * @return <code>true</code> if all pieces have been placed; <code>false</code> otherwise.
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