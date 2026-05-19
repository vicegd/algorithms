package topics.branchandbound;

import java.util.ArrayList;
import java.util.UUID;
import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Node;

/**
 * <h1>The 8-Puzzle</h1>
 * <p>
 * Evaluates and solves the classic sliding puzzle (8-Puzzle) using a 
 * <strong>Branch and Bound</strong> algorithmic strategy. The system leverages 
 * mathematical heuristics to estimate the cost to the target configuration, 
 * efficiently navigating the state space tree.
 * </p>
 *
 * @author vicegd
 */
public class EightPuzzle extends BranchAndBound {
    
    /**
     * Initializes the puzzle solver and builds the execution tree root.
     *
     * @param heuristicType The specific evaluation strategy (e.g., Manhattan distance).
     * @param board         The 1D array representation of the 3x3 grid.
     */
    public EightPuzzle(HeuristicType heuristicType, int[] board) {
        rootNode = new PuzzleState(heuristicType, board);
    }
}

/**
 * <p>
 * Represents a distinct physical configuration of the board within the execution tree.
 * It calculates heuristic bounds and generates topologically valid child states.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(b<sup>d</sup>)</code> - Where <i>b</i> is the branching factor (average 3 valid moves) and <i>d</i> is the depth of the optimal solution. The heuristic severely prunes unpromising branches.</li>
 * <li><strong>Space Complexity:</strong> <code>O(b &times; d)</code> - Dictated by the nodes residing in the priority queue waiting to be evaluated.</li>
 * </ul>
 */
class PuzzleState extends Node {
    private static final int EMPTY_TILE = 9;
    private static final int BOARD_DIMENSION = 3;

    private final int[] board;
    private final HeuristicType heuristicType;

    /**
     * Constructs the root node of the state space tree.
     *
     * @param heuristicType The heuristic function applied to evaluate paths.
     * @param board         The initial state of the puzzle.
     */
    public PuzzleState(HeuristicType heuristicType, int[] board) {
        super();
        this.heuristicType = heuristicType;
        this.board = board;
    }

    /**
     * Constructs a child node representing a subsequent move.
     *
     * @param board         The state of the board after the move.
     * @param heuristicType The heuristic function to evaluate the state.
     * @param depth         The current depth level in the execution tree.
     * @param parentID      The unique identifier of the preceding state.
     */
    public PuzzleState(int[] board, HeuristicType heuristicType, int depth, UUID parentID) {
        super();
        this.board = board;
        this.heuristicType = heuristicType;
        this.depth = depth;
        this.parentId = parentID;
        calculateHeuristicValue();
    }

    /**
     * Generates all mathematically and physically valid child states.
     *
     * @return A list of valid subsequent board configurations.
     */
    @Override
    public ArrayList<Node> expand() {
        var children = new ArrayList<Node>();
        int emptyIndex = getEmptyTileIndex();

        // Evaluate physical boundaries and branch conditionally
        if (emptyIndex >= BOARD_DIMENSION) { // Can move UP
            children.add(createChildNode(emptyIndex, emptyIndex - BOARD_DIMENSION));
        }
        if (emptyIndex < board.length - BOARD_DIMENSION) { // Can move DOWN
            children.add(createChildNode(emptyIndex, emptyIndex + BOARD_DIMENSION));
        }
        if (emptyIndex % BOARD_DIMENSION != 0) { // Can move LEFT
            children.add(createChildNode(emptyIndex, emptyIndex - 1));
        }
        if (emptyIndex % BOARD_DIMENSION != BOARD_DIMENSION - 1) { // Can move RIGHT
            children.add(createChildNode(emptyIndex, emptyIndex + 1));
        }

        return children;
    }

    private int getEmptyTileIndex() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY_TILE) {
                return i;
            }
        }
        throw new IllegalStateException("Corrupted board state: No empty tile found.");
    }

    private PuzzleState createChildNode(int emptyIndex, int targetIndex) {
        int[] nextBoard = board.clone();
        nextBoard[emptyIndex] = nextBoard[targetIndex];
        nextBoard[targetIndex] = EMPTY_TILE;
        return new PuzzleState(nextBoard, heuristicType, depth + 1, this.getId());
    }

    /**
     * Computes the lower-bound heuristic value. Applies mathematical pruning 
     * by evaluating inversion parity to instantly discard unsolvable configurations.
     */
    @Override
    public void calculateHeuristicValue() {
        if (isUnsolvable()) {
            this.heuristicValue = Integer.MAX_VALUE;
            return;
        }
        
        this.heuristicValue = switch (heuristicType) {
            case MANHATTAN -> calculateManhattanDistance();
            case WRONG_PLACE -> calculateMisplacedTiles();
        };
    }

    /**
     * Evaluates the parity of inversions across the 1D grid representation.
     * Odd total combinations dictate that the puzzle can never reach the target state.
     *
     * @return <code>true</code> if the configuration is mathematically unsolvable.
     */
    private boolean isUnsolvable() {
        int inversions = 0;
        int emptyTilePosition = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == EMPTY_TILE) {
                emptyTilePosition = i + 1; // 1-based index calculation
            }
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] > board[j]) {
                    inversions++;
                }
            }
        }

        int parityAdjustment = (emptyTilePosition % 2 == 0) ? 1 : 0;
        return (inversions + parityAdjustment) % 2 != 0;
    }

    /**
     * Calculates the sum of the absolute horizontal and vertical distances 
     * from each tile to its target final position.
     */
    private int calculateManhattanDistance() {
        int manhattan = 0;
        for (int i = 0; i < board.length; i++) {
            int targetX = (board[i] - 1) % BOARD_DIMENSION;
            int currentX = i % BOARD_DIMENSION;
            
            int targetY = (board[i] - 1) / BOARD_DIMENSION;
            int currentY = i / BOARD_DIMENSION;
            
            manhattan += Math.abs(targetX - currentX) + Math.abs(targetY - currentY);
        }
        return manhattan;
    }

    /**
     * Evaluates the raw count of tiles that do not reside in their target position.
     */
    private int calculateMisplacedTiles() {
        int misplaced = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != i + 1) {
                misplaced++;
            }
        }
        return misplaced;
    }

    @Override
    public boolean isSolution() {
        return getHeuristicValue() == 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("===============\n");
        for (int i = 0; i < board.length; i++) {
            if (i % BOARD_DIMENSION == 0) sb.append(" | ");
            
            sb.append(board[i] == EMPTY_TILE ? "  | " : board[i] + " | ");
            
            if (i % BOARD_DIMENSION == BOARD_DIMENSION - 1) sb.append("\n===============\n");
        }
        return sb.toString();
    }
}

/**
 * Defines the available mathematical heuristics applied to evaluate proximity 
 * to the puzzle's final state.
 */
enum HeuristicType {
    MANHATTAN,
    WRONG_PLACE
}