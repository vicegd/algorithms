package topics.backtracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>The Knight's Tour (First Solution)</h1>
 * <p>
 * This class calculates a single valid path that a Knight can take to visit 
 * every square on an <i>N &times; N</i> chessboard exactly once. It employs a 
 * <strong>Backtracking</strong> algorithm designed to halt the execution tree 
 * immediately upon discovering the first valid topological path.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(8<sup>N&sup2;</sup>)</code> - The theoretical worst-case explores 8 branching possibilities at each depth level. However, the early termination mechanism drastically reduces the practical execution time if a solution exists.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N&sup2;)</code> - Required for storing the board matrix and the maximum depth of the JVM call stack.</li>
 * </ul>
 *
 * @author vicegd
 */
public class ChessHorseOne {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseOne.class);

    /**
     * Represents a 2D mathematical displacement vector for a knight's move.
     * @param dx The displacement along the x-axis.
     * @param dy The displacement along the y-axis.
     */
    private record Move(int dx, int dy) {}

    /**
     * The 8 standard valid moves a knight can make on a chessboard.
     */
    private static final Move[] KNIGHT_MOVES = {
        new Move(1, 2), new Move(2, 1), new Move(2, -1), new Move(1, -2),
        new Move(-1, -2), new Move(-2, -1), new Move(-2, 1), new Move(-1, 2)
    };

    private final int boardSize;
    private final int[][] board;
    private final int startingX;
    private final int startingY;
    private boolean solutionFound;

    /**
     * Initializes the chessboard and sets up the starting position.
     *
     * @param boardSize The size of the side of the square board (N &times; N).
     * @param startingX The initial horizontal coordinate (0-indexed).
     * @param startingY The initial vertical coordinate (0-indexed).
     */
    public ChessHorseOne(int boardSize, int startingX, int startingY) {
        this.boardSize = boardSize;
        this.startingX = startingX;
        this.startingY = startingY;
        
        this.board = new int[boardSize][boardSize];
        this.solutionFound = false;
        
        // Set the initial position as the first jump
        this.board[startingX][startingY] = 1;
    }

    /**
     * Triggers the backtracking execution to find the first valid tour.
     * <p>
     * The search begins from the second jump, as the first jump is already 
     * placed at the starting coordinates.
     * </p>
     */
    public void solve() {
        backtrack(2, startingX, startingY);
    }

    /**
     * The core recursive engine that explores the state space tree.
     *
     * @param jumpNumber The sequential number of the current jump.
     * @param currentX   The current horizontal coordinate.
     * @param currentY   The current vertical coordinate.
     */
    private void backtrack(int jumpNumber, int currentX, int currentY) {
        // Base Case: The knight has successfully visited every cell on the board.
        if (jumpNumber == boardSize * boardSize + 1) {
            solutionFound = true;
            logSolution();
            return;
        }

        // Branching: Iteratively explore all theoretical knight moves from the current position.
        for (var move : KNIGHT_MOVES) {
            // Short-circuit execution if a valid path has already been discovered in another branch
            if (solutionFound) {
                return; 
            }

            int nextX = currentX + move.dx();
            int nextY = currentY + move.dy();

            // Feasibility Check: Ensure the target cell is inside the board and unvisited.
            if (isValidMove(nextX, nextY)) {
                // Apply state transition
                board[nextX][nextY] = jumpNumber;
                
                // Recursive descent
                backtrack(jumpNumber + 1, nextX, nextY); 
                
                // Rollback (Pruning): Restore the cell state ONLY if the solution hasn't been found.
                // If found, we preserve the board state to extract the final path.
                if (!solutionFound) {
                    board[nextX][nextY] = 0; 
                }
            }
        }
    }

    /**
     * Validates if a target coordinate is within the boundaries of the board
     * and has not been visited yet.
     *
     * @param x The target horizontal coordinate.
     * @param y The target vertical coordinate.
     * @return <code>true</code> if the move is valid; <code>false</code> otherwise.
     */
    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[x][y] == 0;
    }

    /**
     * Formats and logs the final successful state of the board.
     * <p>
     * To optimize CPU cycles, string construction is bypassed if debug logging 
     * is not currently enabled.
     * </p>
     */
    private void logSolution() {
        if (!log.isDebugEnabled()) {
            return;
        }
        
        log.debug("SOLUTION FOUND:");
        var sb = new StringBuilder();
        
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(String.format("%5d", cell));
            }
            sb.append("\n");
        }
        log.debug("\n{}", sb);
    }

    /**
     * Retrieves the execution status to determine if a valid path was discovered.
     *
     * @return <code>true</code> if a solution exists; <code>false</code> otherwise.
     */
    public boolean hasFoundSolution() {
        return solutionFound;
    }
}