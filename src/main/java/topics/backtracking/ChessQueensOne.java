package topics.backtracking;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>The N-Queens (First Solution)</h1>
 * <p>
 * This class calculates a single mathematically valid arrangement of <i>N</i> queens 
 * on an <i>N &times; N</i> chessboard such that no two queens threaten each other.
 * It employs a highly optimized <strong>Backtracking</strong> algorithm that halts 
 * execution immediately upon discovering the first valid topological configuration.
 * </p>
 * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> Bounded by <code>O(N!)</code> - The algorithm attempts to place exactly one queen per column. The early termination mechanism drastically reduces the practical execution time if a valid arrangement exists early in the search tree.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> - Required for the state-tracking boolean arrays and the maximum depth of the JVM call stack.</li>
 * </ul>
 *
 * @author vicegd
 */
public class ChessQueensOne {
    private static final Logger log = LoggerFactory.getLogger(ChessQueensOne.class);

    private final int boardSize;
    
    // State-tracking arrays
    private final int[] queensInRowByColumn;
    private final boolean[] rowOccupied;
    private final boolean[] primaryDiagonalOccupied;
    private final boolean[] secondaryDiagonalOccupied;
    
    private boolean solutionFound;

    /**
     * Initializes the N-Queens solver and allocates the necessary memory 
     * for tracking the state of the board in constant time.
     *
     * @param boardSize The size of the side of the square board (N &times; N), 
     * which also equals the number of queens to place.
     */
    public ChessQueensOne(int boardSize) {
        this.boardSize = boardSize;
        
        // Maps column indices to row indices
        this.queensInRowByColumn = new int[boardSize];
        Arrays.fill(this.queensInRowByColumn, -1);
        
        // O(1) lookup tables for attack vectors
        this.rowOccupied = new boolean[boardSize];
        this.primaryDiagonalOccupied = new boolean[2 * boardSize - 1];
        this.secondaryDiagonalOccupied = new boolean[2 * boardSize - 1];
        
        this.solutionFound = false;
    }

    /**
     * Triggers the backtracking execution to find the first valid N-Queens arrangement.
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
            solutionFound = true;
            logSolution();
            return;
        }

        // Branching: Attempt to place a queen in every row of the current column.
        for (int row = 0; row < boardSize; row++) {
            // Short-circuit execution if a valid configuration has already been discovered
            if (solutionFound) {
                return;
            }

            if (isSafeToPlace(row, currentColumn)) {
                // Apply state transition (Place queen)
                placeQueen(row, currentColumn);
                
                // Recursive descent to the next column
                backtrack(currentColumn + 1);
                
                // Rollback state (Pruning / Remove queen) ONLY if a solution has not been found.
                // If a solution is found, the state is preserved to allow extraction of the final path.
                if (!solutionFound) {
                    removeQueen(row, currentColumn);
                }
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
     *
     * @param row    The row index where the queen is placed.
     * @param column The column index where the queen is placed.
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
     *
     * @param row    The row index from which the queen is removed.
     * @param column The column index from which the queen is removed.
     */
    private void removeQueen(int row, int column) {
        queensInRowByColumn[column] = -1;
        rowOccupied[row] = false;
        primaryDiagonalOccupied[row + column] = false;
        secondaryDiagonalOccupied[row - column + boardSize - 1] = false;
    }

    /**
     * Formats and logs the final valid arrangement of queens.
     * <p>
     * To optimize CPU cycles, string construction is bypassed if debug logging 
     * is not currently enabled.
     * </p>
     */
    private void logSolution() {
        if (!log.isDebugEnabled()) {
            return;
        }
        
        log.debug("SOLUTION FOUND");
        var sb = new StringBuilder();
        
        for (int column = 0; column < boardSize; column++) {
            sb.append(String.format("COLUMN %d  *** ROW %d%n", column, queensInRowByColumn[column]));
        }
        log.debug("\n{}", sb);
    }

    /**
     * Retrieves the execution status to determine if a valid arrangement was discovered.
     *
     * @return <code>true</code> if a solution exists; <code>false</code> otherwise.
     */
    public boolean hasFoundSolution() {
        return solutionFound;
    }
}