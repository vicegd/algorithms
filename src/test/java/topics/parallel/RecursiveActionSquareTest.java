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
 * RecursiveActionSquare JUnit tests
 * @author viceg
 */
public class RecursiveActionSquareTest {
	private static Logger log = LoggerFactory.getLogger(RecursiveActionSquareTest.class);
	private static ForkJoinPool pool; //Task pool 
	private static int[] data; //Numbers to work with
	private static int[] srcData; //A copy of the original data
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Recursive Action Square Tests - Setup");
	    Random rnd = new Random(); //Random numbers
		pool = new ForkJoinPool(); //Task pool 
	    data = new int[1000]; //Numbers to work with
	    
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
		log.trace("Recursive Action Square Tests - Teardown");
	}
	
	/**
	 * Obtains the square of the values in an array
	 */
	@Test
	public void executeTask() {
		data = srcData.clone();
		
	    RecursiveActionSquare task = new RecursiveActionSquare(data, 0, data.length); 
	    pool.invoke(task); //Start the main ForkJoinTask 
	 
	    log.trace("The transformed sequence:"); 
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i < data.length; i++) {
	    	sb.append(data[i] + " ");
	    	assertEquals(srcData[i]*srcData[i], data[i]);
	    }
	    log.trace(sb.toString());
	}
	
}
