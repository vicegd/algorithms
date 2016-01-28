package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREEDY ALGORITHM PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * This program can solve the problem using two 
 * greedy algorithms and test operation. 
 * It has not an optimal solution in some cases
 * @author viceg
 */
public class AgentsTasks {
	private static Logger log = LoggerFactory.getLogger(AgentsTasks.class);
	private int [][] c;
	
	/**
	 * Constructor for AgentsTasks objects
	 * @param c Matrix of costs
	 */
	public AgentsTasks(int[][] c) {
		this.c = c;
		write(c); 
	} 
	
	/* Writes the cost matrix */
	private void write(int[][] c) {
	    int n = c.length;
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
	    for (int i=0; i<n; i++) {
	    	for (int j=0; j<n; j++)
	    		sb.append(c[i][j]+"//");
	    	sb.append("\n");
	    }
	    log.trace(sb.toString());
	}   
	
	/**
	 * We assign each agent to the less expensive task
	 * This method has a quadratic complexity O(n^2)
	 * @param sol To save the solution
	 */
	public void greedy1(int[] sol) {
	    int n = sol.length;
	    boolean [] mark = new boolean[n];
	 
	    for (int k=0; k<n; k++) 
	    	mark[k] = false; //To mark columns as used
	 
	    int min,pos;
	 
	    for(int i=0; i<n; i++) { //We get the minimum from each of the rows  
	    	min = Integer.MAX_VALUE; 
	        pos = -1;
	        for(int j=0; j<n; j++)
	        	if (!mark[j] && c[i][j] < min) { //If the column is not already used and the value is less than the current one
	        		min = c[i][j];
	        		pos=j;
	        	}
	        sol[i] = pos; //The agent i performs the task pos
	        mark[pos] = true; //We mark the column as used
	     } 
	} 
	
	/**
	 * We assign each task to the agent for which the it is less expensive
	 * This method has a quadratic complexity O(n^2)
	 * @param sol To save the solution
	 */
	public void greedy2(int[] sol) {
	    int n = sol.length;
	    boolean []mark = new boolean[n];
	 
	    for (int k=0; k<n; k++) 
	    	mark[k] = false; //To mark rows as used 
	 
	    throw new UnsupportedOperationException("This operation needs to be implemented");
	}  
	
	/**
	 * Gets the solution for Greedy1
	 * @param sol Array with the solution 
	 * @return The total cost with Greedy1
	 */
	public int getCost1(int[] sol) {
	    int n = sol.length;
	    log.trace("\tThe solution is:"); 
	    for (int i=0; i<n; i++)
	    	log.trace("\tThe worker "+i+" has assigned the task " + sol[i]);
	    log.trace("");
	       
	    int cost = 0;
	    for (int i=0; i<n; i++)
	    	cost += c[i][sol[i]];
	    return cost;
	}  
	
	/**
	 * Gets the solution for Greedy2
	 * @param sol Array with the solution 
	 * @return The total cost with Greedy2
	 */
	public int getCost2(int[] sol) {
	    int n = sol.length;
	    log.trace("\tThe solution is:");  
	    for (int i=0; i<n; i++)
	    	log.trace("\tThe task "+i+" is assigned to worker "+sol[i]);
	    log.trace("");
	
	    throw new UnsupportedOperationException("This operation needs to be implemented");
	}     

} 