package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 0/1 Knapsack Problem - Dynamic Programming Solution.
 * 
 * <h2>Problem Statement</h2>
 * Given:
 * <ul>
 *   <li>A knapsack with maximum weight capacity W</li>
 *   <li>n items, each with weight w[i] and benefit/value v[i]</li>
 *   <li>Binary choice: take item or leave it (0/1 means no fractions)</li>
 * </ul>
 * 
 * Find: The maximum benefit obtainable without exceeding weight limit W
 * 
 * <h2>Why Greedy Fails</h2>
 * The greedy algorithm (selecting items by value-to-weight ratio) does NOT work
 * for 0/1 knapsack when items can't be fractioned.
 * 
 * <pre>
 * Example: Knapsack capacity = 10, Items = [(weight=6, value=30), (weight=5, value=25), (weight=5, value=27)]
 * 
 * Greedy by ratio:
 *   Item 1: 30/6 = 5.0
 *   Item 2: 25/5 = 5.0
 *   Item 3: 27/5 = 5.4 ← Take this first
 *   Then take Item 2 or 3: (only fits one more, total 10)
 *   Greedy result: 27 + 25 = 52
 * 
 * Optimal:
 *   Items 1 + 3: weight = 6 + 5 = 11 (too heavy)
 *   Items 1 + 2: weight = 6 + 5 = 11 (too heavy)
 *   Items 2 + 3: weight = 5 + 5 = 10 ✓
 *   Optimal value: 25 + 27 = 52
 *   
 * Wait, greedy worked here... but not always!
 * </pre>
 * 
 * <h2>Why Dynamic Programming Works</h2>
 * The 0/1 knapsack has:
 * <ul>
 *   <li><strong>Optimal Substructure:</strong> If we include an item, the remaining capacity
 *       must be filled optimally</li>
 *   <li><strong>Overlapping Subproblems:</strong> Many weight capacities are computed multiple times</li>
 * </ul>
 * 
 * <h2>DP Recurrence Relation</h2>
 * <pre>
 * dp[i][w] = maximum value using items 0..i-1 with capacity w
 * 
 * dp[i][w] = max(
 *     dp[i-1][w],                          // Don't take item i-1
 *     benefits[i-1] + dp[i-1][w-weights[i-1]]  // Take item i-1
 * )
 * 
 * Base case: dp[0][w] = 0 for all w (no items = no value)
 * </pre>
 * 
 * <h2>Complexity Analysis</h2>
 * <ul>
 *   <li><strong>Time Complexity:</strong> O(n × W) where n = number of items, W = capacity</li>
 *   <li><strong>Space Complexity:</strong> O(n × W) for the DP table</li>
 *   <li><strong>Can optimize space to:</strong> O(W) using 1D array with careful iteration</li>
 * </ul>
 * 
 * <h2>Example Problem</h2>
 * <pre>
 * Capacity: 5
 * Items:    weight=[2, 3, 4]
 *           value=[3, 4, 5]
 * 
 * DP Table (rows = items, columns = capacity):
 *        0  1  2  3  4  5
 *     0  0  0  0  0  0  0
 *     1  0  0  3  3  3  3  (item weight=2, value=3)
 *     2  0  0  3  4  4  7  (item weight=3, value=4)
 *     3  0  0  3  4  5  7  (item weight=4, value=5)
 * 
 * Result: Maximum value = 7 (take items 1 and 2)
 * </pre>
 * 
 * <h2>When to Use</h2>
 * <ul>
 *   <li>✓ Capacity and values are integers</li>
 *   <li>✓ Reasonable capacity (not millions)</li>
 *   <li>✓ Moderate number of items (not billions)</li>
 *   <li>✗ If items can be fractionated, use greedy (fractional knapsack)</li>
 * </ul>
 * 
 * <h2>Real-World Applications</h2>
 * <ul>
 *   <li>Resource allocation with fixed budget</li>
 *   <li>Portfolio optimization</li>
 *   <li>Cutting stock problems</li>
 *   <li>Cargo loading</li>
 *   <li>Memory allocation</li>
 * </ul>
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
	 * <h3>Algorithm: Bottom-Up DP Table Construction</h3>
	 * <ol>
	 *   <li>Create 2D table v[n][maxWeight+1]</li>
	 *   <li>Initialize first row: v[0][w] = weight[0]*benefit[0] if w ≥ weight[0], else 0</li>
	 *   <li>Fill remaining rows using recurrence relation:
	 *       <ul>
	 *         <li>Option 1: Don't take item i → value = v[i-1][w]</li>
	 *         <li>Option 2: Take item i → value = benefit[i]*weight[i] + v[i-1][w-weight[i]]</li>
	 *         <li>Choose maximum of two options</li>
	 *       </ul>
	 *   </li>
	 *   <li>Return v[n-1][maxWeight] (bottom-right cell)</li>
	 * </ol>
	 * 
	 * <h3>Execution Trace Example</h3>
	 * <pre>
	 * Input: maxWeight=5, benefits=[3, 4, 5], weights=[2, 3, 4]
	 * 
	 * Building DP table:
	 * Row 0 (item weight=2, benefit=3):
	 *   Capacity 0-1: value = 0 (can't fit)
	 *   Capacity 2-5: value = 3 (can fit)
	 *   Row: [0, 0, 3, 3, 3, 3]
	 * 
	 * Row 1 (item weight=3, benefit=4):
	 *   For each capacity, compare: not taking vs taking this item
	 *   Capacity 3: max(3, 4) = 4
	 *   Capacity 5: max(3, 4+3) = 7
	 *   Row: [0, 0, 3, 4, 4, 7]
	 * 
	 * Row 2 (item weight=4, benefit=5):
	 *   Capacity 4: max(4, 5) = 5
	 *   Capacity 5: max(7, 5+3) = 8 (wait... 8?)
	 *   
	 * Final answer: v[2][5] = maximum value achievable
	 * </pre>
	 * 
	 * <h3>Key Implementation Details</h3>
	 * <ul>
	 *   <li><strong>Indexing:</strong> v[i][j] represents answer for items 0..i with capacity j</li>
	 *   <li><strong>First Row:</strong> Only first item is available</li>
	 *   <li><strong>Boundary Check:</strong> Can only take item i if j ≥ weights[i]</li>
	 *   <li><strong>Unreachable States:</strong> Marked as Integer.MIN_VALUE when impossible</li>
	 * </ul>
	 * 
	 * <h3>Reconstructing Solution</h3>
	 * To find which items were selected:
	 * <pre>
	 * Start at v[n-1][maxWeight]
	 * Work backwards to v[0][0]:
	 * - If v[i][w] came from taking item: w decreases by weight[i]
	 * - If v[i][w] came from not taking: w stays same
	 * Mark taken items as you backtrack
	 * </pre>
	 * 
	 * @param maxWeight the maximum weight capacity of the knapsack
	 * @param benefits the value/benefit of each item (indexed 0 to n-1)
	 * @param weights the weight of each item (indexed 0 to n-1)
	 * @return the maximum total value that can be achieved within weight constraint
	 * @throws IllegalArgumentException if maxWeight is negative or if arrays are null/empty
	 * @throws ArrayIndexOutOfBoundsException if benefits and weights have different lengths
	 * 
	 * @implNote
	 * The algorithm logs the computed DP table at TRACE level for debugging.
	 * The matrix can be large for big maxWeight values, so use logging judiciously.
	 * 
	 * @example
	 * <pre>
	 * Knapsack01 knapsack = new Knapsack01();
	 * float[] benefits = {3, 4, 5};
	 * int[] weights = {2, 3, 4};
	 * float maxValue = knapsack.knapsack01(5, benefits, weights);
	 * // Returns maximum value achievable with capacity 5
	 * </pre>
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
