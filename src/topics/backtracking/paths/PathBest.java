//BACKTRACKING PROBLEM: PATH BETWEEN TWO DIFFERENT NODES OF A GRAPH
package topics.backtracking.paths;

/* This class calculates, in a graph, the lowest cost simple
 * path (the best) from a source node v[source] to a destination 
 * node v[target] (source <> target).
 * 
 * This problem is NOT a NP one, i.e., it has a polynomial 
 * solution since, for example Floyd, solves this problem with 
 * a cubic polynomial cost O(n^3)
 * 
 * In spite of having best solutions, we will solve it by 
 * backtracking, developing in depth the state tree of 
 * the graph. */
public class PathBest extends PathSimple {	
	protected int[] bestPath; //to annotate the best path
	protected int bestCost; //cost of the best path
	protected int bestLength; //number of edges of the best path
		
	public PathBest(int n) {
		super(n);
		bestPath = new int[n];
		bestCost = Integer.MAX_VALUE; //a very high value (the highest)
	}
	
	public String getBestPath() {
		StringBuilder sb = new StringBuilder();
		for (int l=0; l<=bestLength; l++)
			sb.append(nodes[bestPath[l]]+ "**");
		return sb.toString();
	}
	
	public int getBestCost() {
		return bestCost;
	}
	
	@Override
	protected void backtracking(int current) {
		if (current == target) {  //it is a solution state
			nsol++;
			if (cost < bestCost) { //we reduce the cost => we change the current best path and best cost
				for (int l=0; l<=length; l++) 
					bestPath[l] = path[l];
				bestCost = cost;
				bestLength = length;
			}
		} 
		else
			for (int j=0; j<n; j++)
				if (!mark[j] && weights[current][j]!=-1) { //child j of the current node
					length++;
					cost = cost + weights[current][j];
					mark[j] = true;
					path[length] = j;
	       
					backtracking(j);  //call on the child node j of the node i
	       
					//we leave it as it was (available to be visited)
					length--;
					cost = cost - weights[current][j];
					mark[j] = false;
	     }
	} //backtracking
	
}