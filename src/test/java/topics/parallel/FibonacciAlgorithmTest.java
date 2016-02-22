package topics.parallel;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FibonacciAlgorithm JUnit tests
 * @author viceg
 */
public class FibonacciAlgorithmTest {
	private static Logger log = LoggerFactory.getLogger(FibonacciAlgorithmTest.class);
	private static FibonacciAlgorithm fib;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Fibonacci Algorithm Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Fibonacci Algorithm Tests - Teardown");
	}
	
	/**
	 * Obtains the Fiboanacci value
	 */
	@Test
	public void executeTask() {
		 int n = 30;
		 fib = new FibonacciAlgorithm(n);
		 
		 long t1 = System.currentTimeMillis(); 
		 long result = fib.solve();   
		 long t2 = System.currentTimeMillis();
	 
		 log.trace("Fibonacci problem of size: " + n);
		 log.trace("Result: " + result);
		 assertEquals(832040, result);
		 log.trace("Elapsed time: " + (t2-t1) + " ms"); 
	}
	
//	@Test
//	public void executeTask() {
//		 int n = 50;
//		 fib = new FibonacciAlgorithm(n);
//		 
//		 long t1 = System.currentTimeMillis(); 
//		 long result = fib.solve();   
//		 long t2 = System.currentTimeMillis();
//	 
//		 log.trace("Fibonacci problem of size: " + n);
//		 log.trace("Result: " + result);
//		 assertEquals(12586269025l, result);
//		 log.trace("Elapsed time: " + (t2-t1) + " ms"); 
//	}

	  	

}
