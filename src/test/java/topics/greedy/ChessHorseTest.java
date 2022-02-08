package topics.greedy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChessHorse JUnit tests
 * @author viceg
 */
public class ChessHorseTest {
	private static Logger log = LoggerFactory.getLogger(ChessHorseTest.class);
	private ChessHorse chess;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Chess Horse - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Chess Horse - Teardown");
	}
	
	/**
	 * Shows the state of the board
	 * In this case there is not solution
	 */
	@Test
	public void testChessNo() {
		int n = 5;
		int[] initialPos = new int[]{ 3, 4}; //PosX and PosY
		
		chess = new ChessHorse(n);
		boolean result = chess.horse(initialPos); //Initial position of the horse (x, y)
		chess.writeSolution();
		assertFalse(result);
	}
	
	/**
	 * Shows the state of the board
	 * In this case there is solution
	 */
	@Test
	public void testChessOk() {
		int n = 8;
		int[] initialPos = new int[]{ 1, 3}; //PosX and PosY
		
		chess = new ChessHorse(n);
		boolean result = chess.horse(initialPos); //Initial position of the horse (x, y)
		chess.writeSolution();
		assertTrue(result);
	}
	
}
