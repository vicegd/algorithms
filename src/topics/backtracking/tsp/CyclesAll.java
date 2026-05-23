//BACKTRACKING PROBLEM: CYCLES OF A NODE IN A GRAPH
package topics.backtracking.tsp;

/* This class calculates by backtracking all simple cycles 
 * there are in a graph starting in a node v[source]. 
 * 
 * This problem is very similar to the traveling salesman 
 * problem and is also a NP problem, i.e., it has not a 
 * polynomial solution. Difference between both problems:
 * 	- Traveling Salesman Problem => SIMPLE AND COMPLETE CYCLES
 *  - Cycles of a node in a graph => SIMPLE CYCLES
 *  
 *  Note that for the worst case of the complete graph, as 
 *  soon as n grows, the time complexity becomes 
 *  untreatable because it is factorial */
public class CyclesAll extends HamiltonianAll {
		
	public CyclesAll(int n, int source, int[][] weights) {
		super(n, source, weights);
	}

	@Override
	protected void backtracking(int current) {
		if (current == source && length > 0) {  //it is a solution state 
			//there is no need to check if (length==n) because we do not need complete cycles
			nsol++;
			StringBuilder result = new StringBuilder();
			for (int l=0; l<=length; l++) 
				result.append(nodes[path[l]]+"*");
			log.debug(result.toString());
			log.debug("ITS COST IS = " + cost + "\n");
		}
		else
			for (int j=0; j<n; j++)
				if (!mark[j] && weights[current][j]!=-1) { //child j of the current node
					length++;
					cost = cost + weights[current][j];
					mark[j] = true;
					path[length] = j; //the city j is visited at the position "length"
	       
					backtracking(j);  //call on the child node j of the node i
	       
					//we leave it as it was (available to be visited)
					length--;
					cost = cost - weights[current][j];
					mark[j] = false;
	     }
	} //backtracking
	
}