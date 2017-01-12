package topics.parallel;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ForkJoinPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RecursiveTaskSum JUnit tests
 * @author viceg
 */
public class RecursiveTaskSumTest {
	private static Logger log = LoggerFactory.getLogger(RecursiveTaskSumTest.class);
	private static ForkJoinPool pool; //Task pool 
	private static double[] data; //Numbers to work with
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Recursive Task Sum Tests - Setup");
		pool = new ForkJoinPool(); //Task pool 
	    data = new double[99999]; //Numbers to work with
	   
	    
	    //Initialize numbers with values that alternate between positive and negative 
	    for(int i = 0; i < data.length; i++) //Some values
	    	data[i] = (double)(((i%2) == 0) ? i : -i) ;
	 
	    log.trace("The original sequence:"); 
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i < data.length; i++)  
	    	sb.append(data[i] + " ");
	    log.trace(sb.toString());
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Recursive Task Sum Tests - Teardown");
	}
	
	/**
	 * Obtains the sum of the values in an array
	 */
	@Test
	public void executeTask() {
	    RecursiveTaskSum task = new RecursiveTaskSum(data, 0, data.length); 
	    long t1 = System.currentTimeMillis(); //to measure the time
	    double result = pool.invoke(task); //Start the main ForkJoinTask 
	    long t2 = System.currentTimeMillis();
	    
	    log.trace("Elapsed time: " + (t2-t1) + " ms"); 
	    log.trace("Result: " + result);
	    
	    //0.001 is the "fuzz factor", something like the margin of error 
	    //since doubles may not be exactly equal
	   	assertEquals(49999, result, 0.001); 
	}



}
