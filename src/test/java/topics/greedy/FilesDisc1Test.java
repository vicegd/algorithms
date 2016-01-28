package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FilesDisc1 JUnit tests
 * @author viceg
 */
public class FilesDisc1Test {
	private static Logger log = LoggerFactory.getLogger(FilesDisc1Test.class);
	private FilesDisc1 filesDisc;
	private int[] files; //Size of each of the files
	private int discCapacity; //Maximum capacity of the disk where files will be saved
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("FilesDisc1 - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("FilesDisc1 - Teardown");
	}
	
	/**
	 * It gives the files that are used. Optimal solution expected
	 */
	@Test
	public void testNumberOfFiles() {
		files = new int[] {100, 350, 450, 370, 5000, 500, 700, 800, 50};
		discCapacity = 880;
		filesDisc = new FilesDisc1(files, discCapacity);
		
		log.trace("We have a limit of: " + discCapacity + " in the disc");
		int result = filesDisc.calculate();
		
		assertEquals(4, result);
	}
	
}
