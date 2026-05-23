//BACKTRACKING PROBLEM: PATH BETWEEN TWO DIFFERENT NODES OF A GRAPH
package seminars.en._20.seminar4;

/* The idea is to complete this program to calculate and 
 * write all paths (both simple and not simple) in a graph 
 * from a  source node v[source] to a destination node 
 * v[target] (source <> destination) having a cost equal to 
 * a given threshold.
 * 
 * These paths must be smaller than 10*n nodes.
 * 
 * Test the program using different examples */
public class PathAll extends PathSimple {
	protected int threshold; //the cost we are looking for. We avoid paths with different cost
		
	public PathAll(int n, int threshold) {
		super(n);
		this.threshold = threshold;		
		path = new int[10*n];
	}
	
	@Override
	public void setSource(int source) {	
		super.setSource(source);	
		path = new int[10*n];
		path[0] = source;
	}
		
	@Override
	protected void backtracking(int current) {
		if ((current==target) && (cost==threshold)) {  //it is a solution state
			nsol++;
			StringBuilder result = new StringBuilder();
			for (int l=0; l<=length; l++) 
				result.append(nodes[path[l]]+"*");
			log.debug(result.toString());
			log.debug("ITS COST IS = " + cost + "\n");
		} 
		else
			for (int j=0; j<n ;j++) 	
				if (weights[current][j] != -1 //child j of the current node
						&& length < 10*n && cost < threshold) { //PROPOSED PRUNING 
					length++;
					cost = cost + weights[current][j];
					path[length] = j;
	       
					backtracking(j);  //call on the child node j of the node i
	       
					//we leave it as it was (available to be visited)
					length--;
					cost = cost - weights[current][j];
				}
	} //backtracking
	
}