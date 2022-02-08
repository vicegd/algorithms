 package topics.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DYNAMIC PROGRAMMING PROBLEM: CHEAPER TRAVEL ON THE RIVER 
 * It consists on finding the minimum cost for each pair of points
 * @author viceg
 */
public class RiverTravel {
	private static Logger log = LoggerFactory.getLogger(RiverTravel.class);	
	
	/**
	 * The dynamic programming version has a cubic complexity O(n^3)
	 * @param t Initial matrix of fees
	 * @param c Final matrix of minimum cost for each pair of docks
	 */
	public void riverTravel(int[][]t, int[][]c) {
		int n = t.length;

		for (int i=n-2; i>=0; i--) //Be careful, it is a descendant loop
			for (int j=i+1; j<n; j++) {
				int min = Integer.MAX_VALUE;
				for (int k=i+1; k<=j; k++) {
					int valueK = 0;
					if (k==j)
						valueK = t[i][j];
					else valueK = c[i][k] + c[k][j];
					if (valueK < min) 
						min = valueK;
				}
				c[i][j] = min;
			}
	}
	
	/**
	 * Prints one of the matrix
	 * @param a Calculated matrix
	 */
	public void writeMatrix(int[][] a) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		int n = a.length;
		for (int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				sb.append(String.format("%5d", a[i][j]));
			sb.append("\n");
		}
		log.trace(sb.toString());
	}
}