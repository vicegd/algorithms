package topics.greedy.agentsTasks;

import topics.greedy.AgentsTasks;

/**
 * GREEDY ALGORITHM PROBLEM: THE PROBLEM OF ASSIGNING N TASK TO AGENTS
 * This program serves to increase the size of the 
 * execution times of the problem and to check the 
 * quadratic behavior expected O(n^2) 
 * @author viceg
 */
public class AgentsTasksDifferentSizesTimes {
	static int [][] c;
	static int[] sol;

	public static void main(String arg []) {
		int nTimes = Integer.parseInt(arg[0]); 
		long t1,t2;
		
		for (int n=10; n<=1000000; n*=2) {
			c = new int[n][n] ;
			sol = new int[n];
		 
			AgentsTasksRandomValues.fillIn(c);
		
			t1 = System.currentTimeMillis();
			for (int r=1;r<=nTimes;r++) {  
				AgentsTasks tasks = new AgentsTasks(c);
				tasks.greedy1(sol);
				tasks.greedy2(sol);
			}
			t2=System.currentTimeMillis();
		
			System.out.println("n="+n+"***"+"TIME="+(t2-t1)+"***"+"nTimes="+nTimes);
		}  
	
	}  

}  
