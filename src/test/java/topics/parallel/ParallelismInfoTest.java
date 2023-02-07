package topics.parallel;

import java.util.concurrent.ForkJoinPool;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ParallelismInfo JUnit tests
 * @author viceg
 */
public class ParallelismInfoTest {
	private static Logger log = LoggerFactory.getLogger(ParallelismInfoTest.class);
	
	/**
	 * Shows the level of parallelism of the computer
	 */
	@Test
	public void executeTask() {
	   ForkJoinPool pool = new ForkJoinPool(7);
	   log.debug("Level of parallelism: " + 
	   pool.getParallelism());
		
	   log.debug("Available processors: " + 
	   Runtime.getRuntime().availableProcessors()); 
	}

	  	

}
