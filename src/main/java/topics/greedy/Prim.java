package topics.greedy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GREEDY ALGORITHM PROBLEM: PRIM
 * It finds a minimum spanning tree for a connected weighted undirected graph. 
 * This means it finds a subset of the edges that forms a tree that includes 
 * every vertex, where the total weight of all the edges in the tree is minimized. 
 * The algorithm was developed in 1930 by Czech mathematician Vojtěch Jarník and 
 * later independently by computer scientist Robert C. Prim in 1957 and 
 * rediscovered by Edsger Dijkstra in 1959. Therefore it is also sometimes called 
 * the DJP algorithm, the Jarník algorithm, or the Prim–Jarník algorithm.
 * It has an optimal solution
 * @author viceg
 */
@SuppressWarnings("unused")
public class Prim {    
	private static Logger log = LoggerFactory.getLogger(Prim.class);
	private int weight[][] = new int[50][50]; //At most we will have 50 vertices
    private int numberOfVertices; //Number of vertices we will have
    private boolean visited[] = new boolean[50]; //If the node has been visited (or not)
    private int connected[] = new int[50]; //Connected[i] tells the predecessor of vertex i
    private int connectionCost[] = new int[50]; //The cost of each of the connections of the connected vertices 
    
    /**
     * Constructor for Prim objects
     * @param weight Matrix of costs for the graph
     * @param numberOfVertices Number of different vertices (nodes) in the graph
     */
    public Prim(int[][] weight, int numberOfVertices) {
    	this.weight = weight;
    	this.numberOfVertices = numberOfVertices;
    	
        //Initializations
        for (int i=0; i<numberOfVertices; i++) { 
        	connected[i] = -1; //It is not connected yet
            connectionCost[i] = Integer.MAX_VALUE; //...So the cost is infinite
            visited[i] = false; //...And it has not been visited yet
        } 
    }
    
    /**
     * To calculate the minimum spanning tree
     * O(n^2)
     */
    public void minimumSpanningTree() {  
    	throw new UnsupportedOperationException("This operation needs to be implemented");
    } 
    
    /**
     * Gets the final solution and shows it
     * @return The total cost of the minimum spanning tree
     */
	public int getSolution() {
	 	int cost = 0; 
	 	for(int i=0; i<numberOfVertices; i++) 
	 		cost = cost + connectionCost[i]; 
	 	log.trace("\tThe minimum cost = " + cost); 
	 	log.trace("\tThe minimum Spanning tree is:"); 
	 	for (int i=0; i<numberOfVertices; i++) 
	 		if (connected[i] != -1)
	 			log.trace("\t\tThe " + connected[i] + " vertex is the predecessor of the " + i + " vertex"); 
	 		else
	 			log.trace("\t\tThe " + i + " vertex has not predecessor"); 
	 	return cost;
	 }

}
