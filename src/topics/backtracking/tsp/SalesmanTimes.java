//BACKTRACKING PROBLEM: THE TRAVELING SALESMAN PROBLEM
package topics.backtracking.tsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesmanTimes {
	private static Logger log = LoggerFactory.getLogger(SalesmanTimes.class);
	
	public static void main(String arg[]) {
		for (int n = 3; n < Integer.MAX_VALUE; n += 1) {
			long t1 = System.currentTimeMillis();
			
			Salesman salesmanBest = new Salesman(n, 0, null);
			salesmanBest.backtracking();
			
			long t2 = System.currentTimeMillis();
			log.debug(String.format("SalesmanBest - size: %d time: %d ms", n, t2 - t1));
			
			t1 = System.currentTimeMillis();
			
			SalesmanPruning salesmanBestPruning = new SalesmanPruning(n, 0, null);
			salesmanBestPruning.backtracking();
			
			t2 = System.currentTimeMillis();
			log.debug(String.format("SalesmanBestPruning - size: %d time: %d ms\n", n, t2 - t1));
		}
	}
} 

