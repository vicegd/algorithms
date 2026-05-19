package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Knight's Tour</h1>
 * <p>
 * Attempts to move a knight across every square of a chessboard exactly once.
 * Unlike Warnsdorff's Rule, this implementation uses a "Naive Heuristic": 
 * it simply picks the <strong>first available legal jump</strong> it evaluates, 
 * without looking ahead or weighing its options.
 * </p>
 *
 * <h2>Educational Purpose</h2>
 * <p>
 * This class serves as a counter-example to demonstrate that a Greedy Algorithm 
 * is only as good as its heuristic. Because it blindly takes the first valid move, 
 * it almost always traps itself in a dead end very quickly, failing to complete 
 * even simple boards.
 * </p>
 *
 * @author vicegd
 */
public class ChessHorseSimpleHeuristic {
    private static final Logger log = LoggerFactory.getLogger(ChessHorseSimpleHeuristic.class);
    
    private final int n; 
    private final int[][] board; 

    // Directional arrays for the 8 possible L-shaped knight jumps (matching the previous switch order)
    private static final int[] DX = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] DY = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Constructs the chessboard.
     * @param n Dimension of the board.
     */
    public ChessHorseSimpleHeuristic(int n) {
        this.n = n;
        this.board = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = -1; // -1 indicates an unvisited square
            }
        }
    }

    /**
     * Attempts to complete the Knight's Tour using a naive first-fit greedy approach.
     *
     * @param startPos Initial position [x, y]
     * @return true if the knight successfully visited every square, false if it hit a dead end.
     */
    public boolean solveTour(int[] startPos) {
        int currentX = startPos[0];
        int currentY = startPos[1];
        
        for (int step = 0; step < n * n; step++) {
            board[currentX][currentY] = step;
            
            // Success condition: We placed the last number
            if (step == n * n - 1) {
                break;
            }

            int nextMoveIndex = findFirstValidMove(currentX, currentY);
            
            // Failure condition: No valid moves left, but the board isn't full
            if (nextMoveIndex == -1) {
                if (log.isTraceEnabled()) writeSolution();
                return false; 
            }
            
            // Apply the first valid jump found
            currentX += DX[nextMoveIndex];
            currentY += DY[nextMoveIndex];
        }
        
        if (log.isTraceEnabled()) writeSolution();
        return true; 
    }

    /**
     * Naive Heuristic: Scans the 8 possible jumps and returns the index 
     * of the VERY FIRST one that is legal and unvisited.
     */
    private int findFirstValidMove(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];

            if (isValidSquare(nextX, nextY)) {
                return i; // Instantly return the first valid option
            }
        }
        return -1; // No moves available
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
        StringBuilder sb = new StringBuilder("\nKnight's Tour Result (Naive Heuristic):\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(String.format("%3d\t", board[i][j]));
            }
            sb.append("\n");
        }
        log.trace(sb.toString());
    }
}