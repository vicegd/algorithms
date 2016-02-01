package topics.backtracking.times;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BACKTRACKING PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * This program can solve the problem using a backtracking algorithm
 * @author viceg
 */
@SuppressWarnings("unused")
public class AgentsTasksTimes {
	private static Logger log = LoggerFactory.getLogger(AgentsTasksTimes.class);
	private int n; //Number of agents/tasks to be assigned 
	private int[][] costs; //Cost matrix
	private int[] sol; //Solution of the problem
	private int[] bestSol; //Tasks of the best solution (until the moment)
	private int costBestSol; //Total cost of the best solution (until the moment)
	
	public static void main(String args[]) {
		int smallestSize = Integer.parseInt(args[0]); //Number of agents/tasks to be assigned
		int biggestSize = Integer.parseInt(args[1]);
		for (int n = smallestSize; n <= biggestSize; n++) {
			AgentsTasksTimes at = new AgentsTasksTimes(n); //We create different instances of the object AgentsTasks
			at.measureTimes(1); //The param is the number of repetitions (nTimes) 	
		}
	}
	
	/**
	 * Constructor, sets the size of the problem and 
	 * makes the array to store the state of the problem
	 * @param n Number of agents and tasks
	 */
	public AgentsTasksTimes(int n) {
		this.n = n;
		sol = new int[n];
		for (int i= 0; i<n; i++)
			sol[i] = -1; //We create the array of values and we initialize it to -1 to avoid confusion with the task 0
		costBestSol = Integer.MAX_VALUE;  //Initially the cost is very large (assumed infinite)
	}
	
	/**
	 * To measure execution times for a specific problem
	 * @param nTimes
	 */
	public void measureTimes(int nTimes) {
		long t1, t2;
		
		generateCostMatrix();
		printCosts();		
		
		t1=System.currentTimeMillis();
		for (int r= 0; r<nTimes; r++) 		
			backtracking(0); //We only measure the time it takes the backtracking
		t2=System.currentTimeMillis();
		
		printBestSol();
		log.debug("n="+n+" *** "+"Time="+(t2-t1)+" *** "+"nTimes="+nTimes);
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
			//printState(sol);
			findBestSolution(sol); //If the new solution improves the past solutions => we get a new best solution
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
	 * @return True if the current best solution is now sol. False otherwise
	 */
	private boolean findBestSolution(int[] sol) {
		int cost = calculateCost(sol);
		//If the cost of the current solution is better than the best solution so far
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
	
	/**
	 * Calculate the cost of the sol[] solution from the cost matrix
	 * @param sol State from which we calculate the cost
	 * @return Accumulated cost carrying out the tasks assigned by the agents
	 */
	private int calculateCost(int[] sol) {
		throw new UnsupportedOperationException("This operation needs to be implemented");
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
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
	
	/**
	 * Prints the best solution found
	 */
	public void printBestSol() {
		throw new UnsupportedOperationException("This operation needs to be implemented");
	}
	
	/**
	 * Prints the state of the problem
	 * @param sol Array with the tasks already assigned
	 */
	private void printState(int[] sol) {
		int costSol = 0;
		for (int i=0; i<n; i++) {
			System.out.println("(Worker:"+ i + "-Task:"+sol[i]+") "); 
			costSol  += costs[i][sol[i]]; //[agent][task]
		}
		log.debug("Solution cost: " + costSol);
	}

}
