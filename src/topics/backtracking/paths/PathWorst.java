//BACKTRACKING PROBLEM: PATH BETWEEN TWO DIFFERENT NODES OF A GRAPH
package seminars.en._20.seminar4;

/* This class calculates, in a graph, the highest cost simple
 * path (the worst) from a source node v[source] to a destination 
 * node v[target] (source <> target).
 * 
 * This problem is a NP one, i.e., it has not a polynomial 
 * solution. We will solve it by backtracking, developing 
 * in depth the state tree of the graph.
 * 
 * For the worst case of the complete graph, as soon as n 
 * grows, the problem becomes untreatable because it has a 
 * factorial time complexity.
 * 
 * When calculating the highest cost path, prune cannot be 
 * done. However, we can do it when calculating the lowest 
 * cost path */
public class PathWorst extends PathSimple {	
	protected int[] worstPath; //to annotate the worst path
	protected int worstCost; //cost of the worst path
	protected int worstLength; //number of edges of the worst path
		
	public PathWorst(int n) {
		super(n);
		worstPath = new int[n];
		worstCost = Integer.MIN_VALUE; //a very low value (the lowest)
	}
	
	public String getWorstPath() {
		StringBuilder sb = new StringBuilder();
		for (int l=0; l<=worstLength; l++)
			sb.append(nodes[worstPath[l]]+ "**");
		return sb.toString();
	}
	
	public int getWorstCost() {
		return worstCost;
	}
	
	@Override
	protected void backtracking(int current) {
		if (current == target) {  //it is a solution state
			nsol++;
			if (cost > worstCost) { //we increase the cost => we change the current worst path and best cost
				for (int l=0; l<=length; l++) 
					worstPath[l] = path[l];
				worstCost = cost;
				worstLength = length;
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