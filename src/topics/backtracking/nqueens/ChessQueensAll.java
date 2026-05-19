package topics.backtracking.nqueens;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>The N-Queens (All Solutions)</h1>
 * <p>
 * This class calculates all mathematically valid arrangements of <i>N</i> queens 
 * on an <i>N &times; N</i> chessboard such that no two queens threaten each other.
 * It employs a highly optimized <strong>Backtracking</strong> algorithm utilizing 
 * 1D boolean arrays to achieve <code>O(1)</code> feasibility checks for rows and diagonals.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> Bounded by <code>O(N!)</code> - The algorithm places exactly one queen per column, drastically reducing the search space compared to testing all board cells. Extensive pruning further limits actual execution paths.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Required for the state-tracking boolean arrays and the maximum depth of the JVM call stack.</li>
 * </ul>
 *
 * @author vicegd
 */
public class ChessQueensAll {
    private static final Logger log = LoggerFactory.getLogger(ChessQueensAll.class);

    private final int boardSize;
    
    // State-tracking arrays
    private final int[] queensInRowByColumn;
    private final boolean[] rowOccupied;
    private final boolean[] primaryDiagonalOccupied;
    private final boolean[] secondaryDiagonalOccupied;
    
    private int solutionCount;

    /**
     * Initializes the N-Queens solver and allocates the necessary memory 
     * for tracking the state of the board.
     *
     * @param boardSize The size of the side of the square board (N &times; N), 
     * which also equals the number of queens to place.
     */
    public ChessQueensAll(int boardSize) {
        this.boardSize = boardSize;
        
        // Maps column indices to row indices
        this.queensInRowByColumn = new int[boardSize];
        Arrays.fill(this.queensInRowByColumn, -1);
        
        // O(1) lookup tables for attack vectors
        this.rowOccupied = new boolean[boardSize];
        this.primaryDiagonalOccupied = new boolean[2 * boardSize - 1];
        this.secondaryDiagonalOccupied = new boolean[2 * boardSize - 1];
        
        this.solutionCount = 0;
    }

    /**
     * Triggers the backtracking execution to find all valid N-Queens arrangements.
     */
    public void solve() {
        backtrack(0);
    }

    /**
     * The core recursive engine that systematically places queens column by column.
     *
     * @param currentColumn The current depth in the state space tree, representing 
     * the column where a queen is currently being placed.
     */
    private void backtrack(int currentColumn) {
        // Base Case: All N queens have been successfully placed without conflicts.
        if (currentColumn == boardSize) {
            solutionCount++;
            logSolution();
            return;
        }

        // Branching: Attempt to place a queen in every row of the current column.
        for (int row = 0; row < boardSize; row++) {
            if (isSafeToPlace(row, currentColumn)) {
                // Apply state transition (Place queen)
                placeQueen(row, currentColumn);
                
                // Recursive descent to the next column
                backtrack(currentColumn + 1);
                
                // Rollback state (Pruning / Remove queen)
                removeQueen(row, currentColumn);
            }
        }
    }

    /**
     * Evaluates whether a queen can be safely placed at the specified coordinates.
     *
     * @param row    The target row index.
     * @param column The target column index.
     * @return <code>true</code> if the cell is not under attack; <code>false</code> otherwise.
     */
    private boolean isSafeToPlace(int row, int column) {
        return !rowOccupied[row] 
            && !primaryDiagonalOccupied[row + column] 
            && !secondaryDiagonalOccupied[row - column + boardSize - 1];
    }

    /**
     * Updates the internal state arrays to reflect a newly placed queen.
     */
    private void placeQueen(int row, int column) {
        queensInRowByColumn[column] = row;
        rowOccupied[row] = true;
        primaryDiagonalOccupied[row + column] = true;
        secondaryDiagonalOccupied[row - column + boardSize - 1] = true;
    }

    /**
     * Restores the internal state arrays by removing a queen, allowing the 
     * algorithm to explore alternative paths.
     */
    private void removeQueen(int row, int column) {
        queensInRowByColumn[column] = -1;
        rowOccupied[row] = false;
        primaryDiagonalOccupied[row + column] = false;
        secondaryDiagonalOccupied[row - column + boardSize - 1] = false;
    }

    /**
     * Formats and logs the current valid arrangement of queens.
     * <p>
     * To optimize CPU cycles, string construction is bypassed if debug logging 
     * is not currently enabled.
     * </p>
     */
    private void logSolution() {
        if (!log.isDebugEnabled()) {
            return;
        }
        
        log.debug("SOLUTION FOUND NUMBER {}", solutionCount);
        var sb = new StringBuilder();
        
        for (int column = 0; column < boardSize; column++) {
            sb.append(String.format("COLUMN %d  *** ROW %d%n", column, queensInRowByColumn[column]));
        }
        log.debug("\n{}", sb);
    }

    /**
     * Retrieves the total number of valid board configurations discovered.
     *
     * @return The integer count of valid N-Queens solutions.
     */
    public int getSolutionCount() {
        return solutionCount;
    }
}