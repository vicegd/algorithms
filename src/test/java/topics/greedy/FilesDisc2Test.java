package topics.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FilesDisc1 JUnit tests
 * @author viceg
 */
public class FilesDisc2Test {
	private static Logger log = LoggerFactory.getLogger(FilesDisc2Test.class);
	private FilesDisc2 filesDisc;
	private int[] files; //Size of each of the files
	private int discCapacity; //Maximum capacity of the disk where files will be saved
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("FilesDisc2 - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("FilesDisc2 - Teardown");
	}
	
	/**
	 * It gives the space that is used. Not optimal solution expected
	 */
	@Ignore("Not ready yet")
	@Test
	public void testUsedSpace() {
		files = new int[] {100, 350, 450, 370, 5000, 500, 700, 800, 50};
		discCapacity = 1200;
		filesDisc = new FilesDisc2(files, discCapacity);
		
		log.trace("We have " + discCapacity + " of space in the disc");
		int result = filesDisc.calculate();
		
		assertEquals(1170, result);
		
		log.trace("\tIt would be better [700,500] -> full disk");
		log.trace("\tIt would be better [500,350,200,100,50] -> full disk");
	}

}
