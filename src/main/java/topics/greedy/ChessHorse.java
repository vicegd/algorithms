package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Knight's Tour (Warnsdorff's Heuristic)</h1>
 * <p>
 * Attempts to move a knight across every square of a chessboard exactly once.
 * It uses Warnsdorff's Rule as its greedy heuristic: always choose the next jump 
 * that has the fewest onward accessible unvisited squares.
 * </p>
 *
 * <h2>The Greedy Trap</h2>
 * <p>
 * While Warnsdorff's heuristic is extremely fast and works magically well for many 
 * board sizes and starting positions, it is a purely Greedy approach and does 
 * NOT guarantee a solution in all theoretically solvable cases (it can hit dead ends).
 * Absolute guarantees require backtracking (Divide & Conquer / DFS).
 * </p>
 *
 * @author vicegd
 */
public class ChessHorse {
    private static final Logger log = LoggerFactory.getLogger(ChessHorse.class);
    
    private final int n; // Size of the board (N x N)
    private final int[][] board; // -1 means unvisited, >= 0 indicates jump order

    // Directional arrays for the 8 possible L-shaped knight jumps
    private static final int[] DX = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] DY = {2, 1, -1, -2, -2, -1, 1, 2};

    /**
     * Constructs the chessboard.
     * @param n Dimension of the board.
     */
    public ChessHorse(int n) {
        this.n = n;
        this.board = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1; 
            }
        }
    }

    /**
     * Attempts to complete the Knight's Tour starting from a specific position.
     *
     * @param startPos Initial position [x, y]
     * @return true if the knight successfully visited every square, false if it hit a dead end.
     */
    public boolean solveTour(int[] startPos) {
        int currentX = startPos[0];
        int currentY = startPos[1];
        
        // Total squares to visit is N*N
        for (int step = 0; step < n * n; step++) {
            board[currentX][currentY] = step;
            
            // If we are at the last step, we successfully finished the board
            if (step == n * n - 1) {
                break;
            }

            int nextMoveIndex = findBestNextMove(currentX, currentY);
            
            // If no moves are available but we haven't finished the board, we failed (Dead End)
            if (nextMoveIndex == -1) {
                if (log.isTraceEnabled()) writeSolution();
                return false; 
            }
            
            // Apply the chosen jump
            currentX += DX[nextMoveIndex];
            currentY += DY[nextMoveIndex];
        }
        
        if (log.isTraceEnabled()) writeSolution();
        return true; 
    }

    /**
     * Uses Warnsdorff's heuristic to find the best next jump.
     * Returns the index (0-7) of the directional jump to take, or -1 if trapped.
     */
    private int findBestNextMove(int x, int y) {
        int minAccessibleJumps = Integer.MAX_VALUE;
        int bestJumpIndex = -1;

        for (int i = 0; i < 8; i++) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];

            if (isValidSquare(nextX, nextY)) {
                // Count how many onward moves we would have from this prospective square
                int onwardJumps = countOnwardJumps(nextX, nextY);
                
                // Warnsdorff's Rule: Pick the jump with the fewest onward options
                if (onwardJumps < minAccessibleJumps) {
                    minAccessibleJumps = onwardJumps;
                    bestJumpIndex = i;
                }
            }
        }
        return bestJumpIndex;
    }

    /**
     * Counts how many valid unvisited squares can be reached from a given position.
     */
    private int countOnwardJumps(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];
            if (isValidSquare(nextX, nextY)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Validates if a coordinate is within the board bounds and unvisited.
     */
    private boolean isValidSquare(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n && board[x][y] == -1);
    }

    /**
     * Dumps the board state to the logger.
     */
    private void writeSolution() {
        StringBuilder sb = new StringBuilder("\nKnight's Tour Result:\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%3d\t", board[i][j]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}