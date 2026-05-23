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
 * the graph. 
 * 
 * In this case, we perform a pruning so that it does not 
 * develop a node if the cumulative cost to reach it 
 * is greater than or equal to the cost of the best path 
 * found so far. */
public class PathBestPruning extends PathBest {		
	public PathBestPruning(int n) {
		super(n);
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
				if (!mark[j] && weights[current][j]!=-1 //child j of the current node
						&& cost<bestCost) { //PROPOSED PRUNING 	
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