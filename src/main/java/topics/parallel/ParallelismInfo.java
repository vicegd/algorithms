package topics.parallel;

import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelismInfo {
	static Logger log = LoggerFactory.getLogger(ParallelismInfo.class);
	
	public static void main(String[] args) {
	    ForkJoinPool pool = new ForkJoinPool(5); //Task pool 

	    log.debug("Level of parallelism: " + 
	    pool.getParallelism());
	    
	    log.debug("Available processors: " + 
	    Runtime.getRuntime().availableProcessors());
	}
}

