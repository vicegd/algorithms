//BACKTRACKING PROBLEM: THE TRAVELING SALESMAN PROBLEM
package topics.backtracking.tsp;

/* The traveling salesman problem is a NP problem, 
 * i.e., it has not a polynomial solution.
 * 
 * This class calculates the best cycle of all simple 
 * cycles of length n (which pass through all the nodes) */
public class SalesmanPruning extends Salesman {
	public SalesmanPruning(int n, int source, int[][] weights) {
		super(n, source, weights);
	}

	@Override
	protected void backtracking(int current) {
		if (current == source && length == n) {  //it is a solution state
			if (cost < bestCost) { //we reduce the cost => we change the current best path and best cost
				for (int l=0; l<=length; l++) 
					bestPath[l] = path[l];
				bestCost = cost;
				nsol++;
			}
		}
		else
			for (int j=0; j<n; j++)
		         if(!mark[j] && weights[current][j]!=-1 //child j of the current node
		    	  && cost < bestCost && !mark[source]) { //PROPOSED PRUNING
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