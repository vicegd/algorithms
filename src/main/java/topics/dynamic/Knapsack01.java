package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DYNAMIC PROGRAMMING PROBLEM: KNAPSACK PROBLEM (0/1)
 * We will now discuss two cases. The two fail if
 * we apply the greedy algorithm already seen.
 * Here we only calculate the maximum benefit
 * It has always an optimal solution
 * @author viceg
 */
public class Knapsack01 {
	private static Logger log = LoggerFactory.getLogger(Knapsack01.class);
	
	/**
	 * Dynamic programming version. The temporal 
	 * complexity is quadratic 0(maxWeight*objects)
	 * @param maxWeight Maximum weight that can be carried in the backpack
	 * @param benefits Benefits for each object
	 * @param weights Weights for each object
	 * @return The maximum value when taken objects taking into accout the maxWeight constraint
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