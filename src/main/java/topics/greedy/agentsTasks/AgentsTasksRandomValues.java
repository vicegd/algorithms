package topics.greedy.agentsTasks;

import java.util.Random;

import topics.greedy.AgentsTasks; 

/**
 * GREEDY ALGORITHM PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * This program serves to randomly generate a matrix 
 * of costs (the size is introduced by the user).
 * This matrix will have random values between 1..999
 * @author viceg
 */
public class AgentsTasksRandomValues {
	static int [][]c;
	static int[]sol;
	
	public static void main(String arg []) {
		int n = Integer.parseInt(arg[0]); 
		
		c = new int[n][n] ;
		sol = new int[n];
		 
		fillIn(c);
		AgentsTasks tasks = new AgentsTasks(c);
		tasks.greedy1(sol);
		tasks.getCost1(sol);
		
		tasks.greedy2(sol);
		tasks.getCost2(sol);
	} 
	
	public static void fillIn(int[][]c) {
		Random r = new Random();
		int n = c.length;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				c[i][j] = r.nextInt(999)+1; //Values between 1 and 999
	}    

} 
