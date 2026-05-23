//BACKTRACKING PROBLEM: PATH BETWEEN TWO DIFFERENT NODES OF A GRAPH
package seminars.en._20.seminar4;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* This class calculates, in a graph, all the simple paths 
 * from a source node v[source] to a destination node 
 * v[target] (source <> target).
 * 
 * This problem is a NP one, i.e., it has not a polynomial 
 * solution. We will solve it by backtracking, developing 
 * in depth the state tree of the graph. */
public class PathSimple {
	protected static Logger log = LoggerFactory.getLogger(PathSimple.class);
	protected int n; //number of nodes (cities)
	protected String []nodes; //nodes (cities)
	protected int[][]weights; //weights (distances between cities)
	protected int source; //the initial node (city of departure)
	protected int target; //the target node (city of destination)
	protected boolean []mark; //to mark the already visited cities and not repeat them
	protected int[] path;  //one of the possible paths (either the best or not) - in progress
	protected int cost; //cost the one of the possible paths - in progress
	protected int length; //size (level or length) of each of the paths
	protected int nsol; //the total number of discovered paths to destination
		
	public PathSimple(int n) {
		this.n = n;
		
		nodes = new String[n];	
		for (int i=0; i<n; i++)
			nodes[i]= "NODE"+i;
		
		weights = new int[n][n];
		giveValuesEdgeWeights();
		
		Random r = new Random();
		do {
			source = r.nextInt(n); //any source and any target. Obviously they cannot be the same
			target = r.nextInt(n);
		}
		while (source == target);
		
		//log.debug("THE SOURCE NODE IS " + nodes[source]);
		//log.debug("THE TARGET NODE IS " + nodes[target]);
		
		mark = new boolean[n]; //this node (city) is not visited yet
		mark[source] = true; //the source is visited
		
		path = new int[n];
		path[0] = source;
		length = 0;  
		nsol = 0; 
		cost = 0;
	}
	
	public void setSource(int source) {	
		mark[this.source] = false;		
		this.source = source;
		mark[this.source] = true; 
		path[0] = this.source;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
	
	public void setWeightMatrix(int [][] weights) {
		this.weights = weights;
	}
	
	public String writeWeights() {
		StringBuilder sb = new StringBuilder();
		int n = weights.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++)
				sb.append(String.format("%5d", weights[i][j]));
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}  
	
	public int getNumberSolutions() {
		return nsol;
	}
	
	public void backtracking() {
		backtracking(source);
	}
	
	//random weight matrix
	protected void giveValuesEdgeWeights() {
		Random r = new Random();
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++) {
				int x = r.nextInt(10);
				if (x == 0) 
					x = -1; //there is no edge
				weights[i][j] = x;
		    }
		
		for (int i=0; i<n; i++)
			weights[i][i] = -1; //you cannot go from a city to itself  
	}
	
	protected void backtracking(int current) {
		if (current == target) {  //it is a solution state
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
					path[length] = j;
	       
					backtracking(j);  //call on the child node j of the node i
	       
					//we leave it as it was (available to be visited)
					length--;
					cost = cost - weights[current][j];
					mark[j] = false;
	     }
	} //backtracking
	
}