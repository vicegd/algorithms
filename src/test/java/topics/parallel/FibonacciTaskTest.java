package topics.parallel;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ForkJoinPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FibonacciTaskTest JUnit tests
 * @author viceg
 */
public class FibonacciTaskTest {
	private static Logger log = LoggerFactory.getLogger(FibonacciTaskTest.class);
	private static ForkJoinPool pool; //Task pool 
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Fibonacci Task Tests - Setup");
		pool = new ForkJoinPool(); //Task pool 
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Fibonacci Task Tests - Teardown");
	}
	
	/**
	 * Obtains the Fiboanacci value
	 */
	@Test
	public void executeTask() {
		 int n = 30;
		 FibonacciAlgorithm problem = new FibonacciAlgorithm(n);
		 FibonacciTask task = new FibonacciTask(problem);
 
		 long t1 = System.currentTimeMillis(); 
		 long result = pool.invoke(task); 
		 long t2 = System.currentTimeMillis();
	
		 log.trace("Fibonacci problem of size: " + n);
		 log.trace("Result: " + result);
		 assertEquals(832040, result);
		 log.trace("Elapsed time: " + (t2-t1) + " ms"); 		
	}



}
