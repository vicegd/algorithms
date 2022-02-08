package topics.backtracking.times;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * This program can solve the problem using a backtracking algorithm
 * @author viceg
 */
public class AgentsTasksTimes {
	private static Logger log = LoggerFactory.getLogger(AgentsTasksTimes.class);
	private int n; //Number of agents/tasks to be assigned 
	private int[][] costs; //Cost matrix
	private int[] sol; //Solution of the problem
	private int[] bestSol; //Tasks of the best solution (until the moment)
	private int costBestSol; //Total cost of the best solution (until the moment)
	
	public static void main(String args[]) {
		int n = 5; //Number of agents/tasks to be assigned
		AgentsTasksTimes problem = new AgentsTasksTimes(n);
		problem.execute(); 
	}
	
	/**
	 * Constructor, sets the size of the problem and 
	 * makes the array to store the state of the problem
	 * @param n Number of agents and tasks
	 */
	public AgentsTasksTimes(int n) {
		this.n = n;
		sol = new int[n];
		for (int i=0; i<n; i++)
			sol[i] = -1; //We create the array of values and we initialize it to -1 to avoid confusion with the task 0
		costBestSol = Integer.MAX_VALUE;  //Initially the cost is very large (assumed infinite)
	}
	
	/**
	 * To measure execution times for a specific problem
	 * @param nTimes Number of times the task is performed
	 */
	public void execute() {
		long t1, t2;
		
		generateCostMatrix();
		printCosts();		
		
		t1=System.currentTimeMillis();
		backtracking(0); //We only measure the time it takes the backtracking
		t2=System.currentTimeMillis();
		
		printBestSol();
		log.debug("n="+n+" *** "+"Time="+(t2-t1));
	}
	
	/**
	 * Random generation of the cost matrix
	 */
	public void generateCostMatrix() {
		costs = new int[n][n];
		Random r = new Random();
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				costs[i][j] = r.nextInt(999)+1;  //Between 1 and 999
	}
	
	/**
	 * Prints cost matrix
	 */
	public void printCosts() {
		log.debug("THE MATRIX OF COSTS IS:");
		StringBuilder sb = new StringBuilder();
		for (int i= 0; i<n; i++) {
			for (int j= 0; j<n; j++)
				sb.append(String.format("%5d", costs[i][j]));
			sb.append("\n");
		}
		log.debug(sb.toString());
	}
	
	/**
	 * Backtracking that solves the stated problem
	 * @param agent, number of the agent we are going to work with
	 */
	public void backtracking(int agent) {
		if (agent==n) {  //Solution condition
			checkIfBestSolution(sol); //If the new solution improves the past solutions => we get a new best solution
		}
		else {
			for (int task=0; task<n; task++) {
				if (!assigned(task)) {
					sol[agent] = task;
					
					backtracking(agent+1); 
					
					sol[agent] = -1; //We leave it as it was 
				}
			} //for
		} //else	
	}
	
	/**
	 * Calculates if the current new solution is the best or not
	 * If so, it updates the best solution found so far
	 * @param sol Current solution
	 */
	private void checkIfBestSolution(int[] sol) {
		int cost = 0;
		for (int i=0; i<n; i++)
			cost += costs[i][sol[i]]; //[agent][task]
		
		//If the cost of the current solution is better than the best solution so far
		if (cost < costBestSol) {
			bestSol = (int[])sol.clone(); //we keep this solution as the best
			costBestSol = cost;
		}
	}
	
	/**
	 * Checks whether a condition is true, i.e., if a task is assigned 
	 * to the current solution state
	 * @param task We want to assign, and we're going to look for it 
	 * in the array of state (sol[])
	 * @return True if the task is already assigned so it would not be 
	 * valid to assign the same task again			
	 */
	private boolean assigned(int task) {
		for (int i=0; i<n; i++)
			if (sol[i]==task) //it is already assigned
				return true;
		return false;
	}
	
	/**
	 * Prints the best solution found
	 */
	public void printBestSol() {
		log.debug("Best solution: ");
		for (int i=0; i<n; i++) {
			System.out.println("(Worker:"+ i + "-Task:"+bestSol[i]+") "); 
		}
		log.debug("Solution cost: " + costBestSol);
	}
}

