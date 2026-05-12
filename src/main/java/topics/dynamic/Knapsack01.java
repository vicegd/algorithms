package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 0/1 Knapsack Problem - Dynamic Programming Solution.
 *
 * Problem Statement:
 * Given a knapsack with maximum weight capacity W and n items, each with weight
 * w[i] and benefit/value v[i], and a binary choice (take item or leave it),
 * find the maximum benefit obtainable without exceeding weight limit W.
 *
 * Why Greedy Fails:
 * The greedy algorithm (selecting items by value-to-weight ratio) does NOT work
 * for 0/1 knapsack when items cannot be fractionated.
 *
 * Why Dynamic Programming Works:
 * The 0/1 knapsack has optimal substructure (if we include an item, the remaining
 * capacity must be filled optimally) and overlapping subproblems (many weight
 * capacities are computed multiple times).
 *
 * DP Recurrence Relation:
 *   dp[i][w] = max(
 *     dp[i-1][w],                               // Don't take item i-1
 *     benefits[i-1] + dp[i-1][w-weights[i-1]]  // Take item i-1
 *   )
 *   Base case: dp[0][w] = 0 for all w (no items = no value)
 *
 * Complexity Analysis:
 * - Time Complexity:  O(n x W) where n = number of items, W = capacity
 * - Space Complexity: O(n x W) for the DP table
 * - Space can be optimized to O(W) using a 1D array with careful iteration
 *
 * Example Problem (capacity = 5, weights = [2, 3, 4], values = [3, 4, 5]):
 *   DP Table (rows = items 0..n, columns = capacity 0..W):
 *        0  1  2  3  4  5
 *     0  0  0  0  0  0  0
 *     1  0  0  3  3  3  3
 *     2  0  0  3  4  4  7
 *     3  0  0  3  4  5  7
 *   Result: Maximum value = 7 (take items 1 and 2)
 *
 * When to Use:
 * - Capacity and values are integers
 * - Reasonable capacity (not millions)
 * - Moderate number of items (not billions)
 * - Not suitable if items can be fractionated (use greedy fractional knapsack instead)
 *
 * Real-World Applications:
 * - Resource allocation with fixed budget
 * - Portfolio optimization
 * - Cutting stock problems
 * - Cargo loading
 * - Memory allocation
 *
 * @author vicegd
 * @see topics.greedy.Knapsack for fractional knapsack (greedy approach)
 * @see topics.dynamic.Fibonacci for another DP example
 */
public class Knapsack01 {
	private static Logger log = LoggerFactory.getLogger(Knapsack01.class);
	
	/**
	 * Solves the 0/1 knapsack problem using Dynamic Programming.
	 *
	 * Algorithm - Bottom-Up DP Table Construction:
	 * 1. Create 2D table v[n][maxWeight+1]
	 * 2. Initialize first row: v[0][w] = weight[0]*benefit[0] if w >= weight[0], else 0
	 * 3. Fill remaining rows using recurrence relation:
	 *    - Option 1: Don't take item i  ->  value = v[i-1][w]
	 *    - Option 2: Take item i        ->  value = benefit[i]*weight[i] + v[i-1][w-weight[i]]
	 *    - Choose the maximum of both options
	 * 4. Return v[n-1][maxWeight] (bottom-right cell)
	 *
	 * Execution Trace Example (maxWeight=5, benefits=[3,4,5], weights=[2,3,4]):
	 *   Row 0 (item weight=2, benefit=3): [0, 0, 3, 3, 3, 3]
	 *   Row 1 (item weight=3, benefit=4): [0, 0, 3, 4, 4, 7]
	 *   Row 2 (item weight=4, benefit=5): [0, 0, 3, 4, 5, 7]
	 *   Final answer: v[2][5]
	 *
	 * Key Implementation Details:
	 * - v[i][j] represents the answer for items 0..i with capacity j
	 * - First row: only first item is available
	 * - Boundary check: can only take item i if j >= weights[i]
	 * - Unreachable states are marked as Integer.MIN_VALUE when impossible
	 *
	 * Reconstructing Solution:
	 * Start at v[n-1][maxWeight] and work backwards to v[0][0].
	 * If v[i][w] came from taking item i, w decreases by weight[i];
	 * otherwise w stays the same. Mark taken items as you backtrack.
	 *
	 * @param maxWeight the maximum weight capacity of the knapsack
	 * @param benefits  the value/benefit of each item (indexed 0 to n-1)
	 * @param weights   the weight of each item (indexed 0 to n-1)
	 * @return the maximum total value that can be achieved within the weight constraint
	 * @throws IllegalArgumentException      if maxWeight is negative or if arrays are null/empty
	 * @throws ArrayIndexOutOfBoundsException if benefits and weights have different lengths
	 */
	public float knapsack01(int maxWeight, float[]benefits, int[]weights) {
		int n = weights.length;
		float[][]v = new float[n][maxWeight+1]; //Creates the table [different types of objects][value we need to deal with + 1 because we start in zero]
		
		float notInsertingNewObject = 0;
		float insertingNewObject = 0;
		for (int i=0; i<=maxWeight; i++) 
			if (i >= weights[0]) //We only insert the first element when we have capacity
				v[0][i] = weights[0]*benefits[0]; //We insert the values => value = weight*benefit
		
		for (int i=1;i<n;i++)
			for (int j=0; j<=maxWeight; j++) {
				notInsertingNewObject = v[i-1][j]; //The value from the previous row
				if (j >= weights[i]) //If we can get an object from weights[i] and we still have objects to insert
					insertingNewObject = benefits[i]*weights[i] + v[i-1][j-weights[i]];
				else insertingNewObject = Integer.MIN_VALUE; //It is not reachable
				v[i][j] = Math.max(notInsertingNewObject, insertingNewObject); //We always choose the most valuable object => we want much value
			}
		
		//Prints the calculated matrix
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		for (int i=0;i<n;i++) {
			for (int j=0;j<=maxWeight;j++)
				sb.append(String.format("%15f", v[i][j]));
			sb.append("\n");
		}
		log.trace(sb.toString());
		return v[n-1][maxWeight];
	}

}
