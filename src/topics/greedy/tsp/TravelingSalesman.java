package topics.greedy.tsp;

/**
 * <h1>Traveling Salesman</h1>
 * <p>
 * Implements the "Nearest Neighbor" greedy strategy to approximate the shortest 
 * Hamiltonian cycle in a weighted graph.
 * </p>
 *
 * <h2>The Greedy Trap</h2>
 * <p>
 * This algorithm is extremely fast O(N&sup2;), but it is <strong>sub-optimal</strong>.
 * By picking the closest city at every step, the salesman often finds himself trapped 
 * at the end of the tour with no choice but to take an extremely expensive edge 
 * to return to the starting city.
 * </p>
 *
 * @author vicegd
 */
public class TravelingSalesman {
    /**
     * Executes the Nearest Neighbor heuristic.
     * * @param weights Adjacency matrix of the graph.
     * @param startNode The starting city index.
     * @return A solution object containing the path and the total cost.
     */
    public SalesmanSolution solve(int[][] weights, int startNode) {
        int n = weights.length;
        int[] path = new int[n + 1];
        boolean[] visited = new boolean[n];

        path[0] = startNode;
        visited[startNode] = true;
        
        int currentCity = startNode;
        int totalCost = 0;

        for (int i = 1; i < n; i++) {
            int nearestCity = -1;
            int minDistance = Integer.MAX_VALUE;

            // Find the closest unvisited city
            for (int j = 0; j < n; j++) {
                if (!visited[j] && weights[currentCity][j] < minDistance) {
                    minDistance = weights[currentCity][j];
                    nearestCity = j;
                }
            }

            // Move to the nearest city
            path[i] = nearestCity;
            visited[nearestCity] = true;
            totalCost += minDistance;
            currentCity = nearestCity;
        }

        // Close the cycle
        path[n] = startNode;
        totalCost += weights[currentCity][startNode];

        return new SalesmanSolution(path, totalCost);
    }
}