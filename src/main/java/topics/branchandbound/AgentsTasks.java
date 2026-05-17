package topics.branchandbound;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import topics.branchandbound.util.BranchAndBound;
import topics.branchandbound.util.Node;

/**
 * <h1>Task Assignment Problem</h1>
 * <p>
 * Evaluates the optimal distribution of <i>N</i> independent tasks among <i>N</i> agents, 
 * minimizing the total overall operational cost. It employs a <strong>Branch and Bound</strong> 
 * strategy to systematically prune unfeasible or sub-optimal mathematical assignments.
 * </p>
 *
 * @author vicegd
 */
public class AgentsTasks extends BranchAndBound {
    
    /**
     * Initializes the execution tree with the root node.
     *
     * @param problemSize The dimension of the symmetric cost matrix.
     * @param costMatrix  The 2D array representing the cost of assigning task <i>j</i> to agent <i>i</i>.
     */
    public AgentsTasks(int problemSize, int[][] costMatrix) {
        rootNode = new AssignmentState(problemSize, costMatrix);
    }
}

/**
 * <p>
 * Represents a distinct state within the Branch and Bound execution tree for the 
 * Task Assignment problem. Each node tracks partial assignments and calculates a 
 * lower-bound heuristic to guide the search process and prune sub-optimal branches.
 * </p>
 * * <h2>Complexity</h2>
 * <ul>
 * <li><strong>Time Complexity:</strong> <code>O(N!)</code> in the theoretical worst-case, mapping to every permutation. The heuristic bounding radically flattens this curve in practice.</li>
 * <li><strong>Space Complexity:</strong> <code>O(N)</code> per node. Overall heap consumption depends on the underlying priority queue dimension during traversal.</li>
 * </ul>
 */
class AssignmentState extends Node {
    private static final Logger log = LoggerFactory.getLogger(AssignmentState.class);
    
    private final int problemSize;
    private final int[][] costMatrix;
    
    // Tracks the assigned task for each agent. assignedTaskForAgent[i] = j means agent i executes task j.
    private final int[] assignedTaskForAgent;
    // Tracks which tasks have already been distributed to prevent duplication.
    private final boolean[] isTaskAssigned;

    /**
     * Constructs the Root Node of the state space tree, representing the initial 
     * unassigned state of the system.
     *
     * @param problemSize The total number of agents and tasks.
     * @param costMatrix  The matrix mapping cost values for all agent-task pairs.
     */
    public AssignmentState(int problemSize, int[][] costMatrix) {
        super();
        this.problemSize = problemSize;
        this.costMatrix = costMatrix;
        
        this.assignedTaskForAgent = new int[problemSize];
        Arrays.fill(this.assignedTaskForAgent, -1);
        
        this.isTaskAssigned = new boolean[problemSize];
        
        logInitialCostMatrix();
    }

    /**
     * Constructs a child state derived from a parent node by assigning a specific 
     * task to the next available agent.
     *
     * @param parent       The origin state node.
     * @param taskToAssign The target task index to assign to the current agent.
     */
    public AssignmentState(AssignmentState parent, int taskToAssign) {
        super();
        this.problemSize = parent.problemSize;
        this.costMatrix = parent.costMatrix;
        
        this.assignedTaskForAgent = Arrays.copyOf(parent.assignedTaskForAgent, parent.assignedTaskForAgent.length);
        this.isTaskAssigned = Arrays.copyOf(parent.isTaskAssigned, parent.isTaskAssigned.length);
        
        this.depth = parent.depth;
        this.parentId = parent.getId();
        
        // State Transition: Assign the selected task to the agent at the current depth
        this.assignedTaskForAgent[this.depth] = taskToAssign;
        this.isTaskAssigned[taskToAssign] = true;
        this.depth++;
        
        calculateHeuristicValue();
        
        // Tree Compression Optimization: If only one task remains unassigned, 
        // dynamically resolve the final leaf node to skip redundant tree levels.
        if (this.depth == this.problemSize - 1) {
            resolveFinalAssignment();
        }
    }

    /**
     * Automatically assigns the last remaining task to the last remaining agent.
     */
    private void resolveFinalAssignment() {
        int finalTask = -1;
        for (int k = 0; k < problemSize; k++) {
            if (!isTaskAssigned[k]) {
                finalTask = k;
                break;
            }
        }
        
        this.assignedTaskForAgent[this.depth] = finalTask;
        this.isTaskAssigned[finalTask] = true;
        this.depth++;
        
        calculateHeuristicValue();
    }

    /**
     * Establishes a preliminary upper bound to initiate the pruning phase.
     * It uses the minimum cost between the primary and secondary diagonals of the matrix.
     *
     * @return The initial upper bound estimate.
     */
    @Override
    public int initialValuePruneLimit() {
        int primaryDiagonalCost = 0;
        int secondaryDiagonalCost = 0;
        
        for (int i = 0; i < problemSize; i++) {
            primaryDiagonalCost += costMatrix[i][i];
            secondaryDiagonalCost += costMatrix[problemSize - 1 - i][i];
        }
        
        return Math.min(primaryDiagonalCost, secondaryDiagonalCost);
    }

    /**
     * Calculates the lower bound heuristic estimate for this partial assignment.
     * The logic relies on accumulating the fixed costs of agents already assigned, 
     * plus the absolute minimum available costs for the remaining unassigned tasks.
     */
    @Override
    public void calculateHeuristicValue() {
        this.heuristicValue = 0;
        
        // Accumulate fixed costs for agents that have already received an assignment
        for (int i = 0; i < depth; i++) {
            this.heuristicValue += costMatrix[i][assignedTaskForAgent[i]];
        }
        
        // Accumulate the most optimistic (minimum) theoretical cost for the pending tasks
        for (int taskIndex = 0; taskIndex < problemSize; taskIndex++) {
            if (!isTaskAssigned[taskIndex]) {
                this.heuristicValue += getMinimumCostForTask(taskIndex);
            }
        }
    }

    /**
     * Identifies the minimum cost to assign a specific task among the agents 
     * that do not have an assignment yet.
     *
     * @param taskIndex The target task.
     * @return The minimum possible cost to execute this task.
     */
    private int getMinimumCostForTask(int taskIndex) {
        int minCost = Integer.MAX_VALUE;
        for (int agent = depth; agent < problemSize; agent++) {
            minCost = Math.min(minCost, costMatrix[agent][taskIndex]);
        }
        return minCost;
    }

    /**
     * Generates all mathematically valid mathematical combinations extending from 
     * the current state.
     *
     * @return A list containing the resulting child nodes.
     */
    @Override
    public ArrayList<Node> expand() {
        var children = new ArrayList<Node>();
        
        for (int taskIndex = 0; taskIndex < problemSize; taskIndex++) {
            if (!isTaskAssigned[taskIndex]) {
                children.add(new AssignmentState(this, taskIndex));
            }
        }
        
        return children;
    }

    /**
     * Determines whether the current node represents a fully resolved combination.
     *
     * @return <code>true</code> if all agents have been assigned a task; <code>false</code> otherwise.
     */
    @Override
    public boolean isSolution() {
        return depth == problemSize;
    }

    /**
     * Logs the raw matrix upon system initialization to maintain operational visibility.
     */
    private void logInitialCostMatrix() {
        if (!log.isDebugEnabled()) {
            return;
        }
        
        log.debug("COSTS MATRIX");
        var sb = new StringBuilder();
        for (int i = 0; i < problemSize; i++) {
            for (int j = 0; j < problemSize; j++) {
                sb.append(String.format("%5d", costMatrix[i][j]));
            }
            sb.append("\n");
        }
        log.debug("\n{}", sb);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("===============\n");
        for (int i = 0; i < assignedTaskForAgent.length; i++) {
            if (assignedTaskForAgent[i] != -1) {
                sb.append(String.format("THE TASK %d IS ASSIGNED TO WORKER %d%n", assignedTaskForAgent[i], i));
            } else {
                sb.append(String.format("THE TASK FOR WORKER %d IS NOT ASSIGNED YET%n", i));
            }
        }
        sb.append(String.format("Heuristic value = %d%n", heuristicValue));
        sb.append("===============\n");
        return sb.toString();
    }
}