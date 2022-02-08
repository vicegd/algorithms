package topics.backtracking;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChessQueensAll JUnit tests
 * @author viceg
 */
public class ChessHorseAllTest {
	private static Logger log = LoggerFactory.getLogger(ChessHorseAllTest.class);
	private ChessHorseAll chess;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Chess Horse All Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Chess Horse All - Teardown");
	}
	
	/**
	 * Indicates whether it is possible move the horse through the complete board 5x5
	 */
	@Test
	public void testChessHorseOk() {
		int startingX = 0;
		int startingY = 0;
		
		chess = new ChessHorseAll(5, startingX, startingY);
		chess.backtracking(2, startingX, startingY);
		int result = chess.getNumberOfSolutions();
		assertEquals(304, result);
	}
	
	/**
	 * Indicates whether it is possible move the horse through the complete board 5x5
	 */
	@Test
	public void testChessHorseNo() {
		int startingX = 1;
		int startingY = 4;
		
		chess = new ChessHorseAll(5, startingX, startingY);
		chess.backtracking(2, startingX, startingY);
		int result = chess.getNumberOfSolutions();
		assertEquals(0, result);
	}
	
}
