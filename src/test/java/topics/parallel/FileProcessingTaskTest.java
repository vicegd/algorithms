package topics.parallel;

import java.util.concurrent.ForkJoinPool;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileProcessingTask JUnit tests
 * @author viceg
 */
public class FileProcessingTaskTest {
	private static Logger log = LoggerFactory.getLogger(FileProcessingTaskTest.class);
	private static ForkJoinPool pool; //Task pool 
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("File Processing Taks Tests - Setup");
		pool = new ForkJoinPool();
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("File Processing Taks Tests - Teardown");
	}
	
	/**
	 * Process several files in parallel
	 */
	@Test
	public void executeTask() {
		FileProcessingTask problem = new FileProcessingTask("c:\\WINDOWS", null);                

        long t1 = System.currentTimeMillis(); 
	    pool.invoke(problem); 
	    long t2 = System.currentTimeMillis();
		  
	    log.trace("Elapsed time: " + (t2-t1) + " ms");;
	}
	
}
