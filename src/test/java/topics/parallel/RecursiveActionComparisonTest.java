package topics.parallel;

import static org.junit.Assert.assertEquals;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RecursiveActionComparison JUnit tests
 * @author viceg
 */
public class RecursiveActionComparisonTest {
	private static Logger log = LoggerFactory.getLogger(RecursiveActionComparisonTest.class);
	private static int[] data; //Numbers to work with
	private static int[] srcData; //A copy of the original data
	private static int level = 3; //Level of parallelism
	private static int threshold = 100; //Threshold used
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Recursive Action Comparison Tests - Setup");
	    Random rnd = new Random(); //Random numbers
	    data = new int[1000000]; //Numbers to work with
	    
	    for(int i = 0; i < data.length; i++) //Some values
	      data[i] = rnd.nextInt(100);
	 
	    log.trace("The original sequence:"); 
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i < data.length; i++)  
	    	sb.append(data[i] + " ");
	    log.trace(sb.toString());
		srcData = data.clone();
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Recursive Action Comparison Tests - Teardown");
	}
	
	/**
	 * Obtains the cube root of the values in an array
	 */
	@Test
	public void executeTask() {
		data = srcData.clone();
		level = 3; //Level of parallelism
		threshold = 100; //Threshold used
		execution();
	}
	
	/**
	 * Obtains the cube root of the values in an array
	 */
	@Test
	public void executeTask2() {
		data = srcData.clone();
		level = 8; //Level of parallelism
		threshold = 1000; //Threshold used
		execution();
	}
	
	/**
	 * Obtains the cube root of the values in an array
	 */
	@Test
	public void executeTask3() {
		data = srcData.clone();
		level = 1; //Level of parallelism
		threshold = 10000; //Threshold used
		execution();
	}
	
	private void execution() {
		ForkJoinPool pool = new ForkJoinPool(level); //Task pool 
		RecursiveActionComparison task = new RecursiveActionComparison(data, 0, data.length, threshold); 
		
		long t1 = System.currentTimeMillis(); //to measure the time
		pool.invoke(task); //Start the main ForkJoinTask 
		long t2 = System.currentTimeMillis();
		
		log.trace("Level of parallelism: " + level); 
		log.trace("Sequential threshold: " + threshold); 
		log.trace("Elapsed time: " + (t2-t1) + " ms"); 
		
	    log.trace("The transformed sequence:"); 
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i < data.length; i++) {
	    	sb.append(data[i] + " ");
	    	assertEquals((int)Math.cbrt(srcData[i]), data[i]);
	    }
	    log.trace(sb.toString());
	}
	
}





