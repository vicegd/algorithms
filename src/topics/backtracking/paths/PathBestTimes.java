//BACKTRACKING PROBLEM: PATH BETWEEN TWO DIFFERENT NODES OF A GRAPH
package seminars.en._20.seminar4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* This class measures times to solve the problem of
 * calculating the lowest cost simple path in the 
 * following two cases: with no pruning 
 * and with pruning */
public class PathBestTimes {
	private static Logger log = LoggerFactory.getLogger(PathBestTimes.class);
	
	public static void main(String arg[]) {
		for (int n = 3; n < Integer.MAX_VALUE; n += 1) {
			
			long t1 = System.currentTimeMillis();		
			PathBest pathBest = new PathBest(n);
			pathBest.backtracking();
			long t2 = System.currentTimeMillis();
			log.debug(String.format("PathBest - size: %d time: %d ms", n, t2 - t1));
			
			t1 = System.currentTimeMillis();		
			PathBestPruning pathBestPruning = new PathBestPruning(n);
			pathBestPruning.backtracking();
			t2 = System.currentTimeMillis();
			
			log.debug(String.format("PathBestPruning - size: %d time: %d ms\n", n, t2 - t1));
		}
	}
} 

