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
		throw new UnsupportedOperationException("This operation needs to be implemented");
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