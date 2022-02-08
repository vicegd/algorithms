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
public class ChessQueensAllTest {
	private static Logger log = LoggerFactory.getLogger(ChessQueensAllTest.class);
	private ChessQueensAll chess;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Chess Queens All Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Chess Queens All - Teardown");
	}
	
	/**
	 * Calculates how many combinations for the queens problem in a board 4x4
	 */
	@Test
	public void testChessQueensOk() {
		chess = new ChessQueensAll(4);
		chess.backtracking(0);
		int result = chess.getNumberOfSolutions();
		assertEquals(2, result);
	}
	
	/**
	 * Calculates how many combinations for the queens problem in a board 5x5
	 */
	@Test
	public void testChessQueensOk2() {
		chess = new ChessQueensAll(5);
		chess.backtracking(0);
		int result = chess.getNumberOfSolutions();
		assertEquals(10, result);
	}
	
	/**
	 * Calculates how many combinations for the queens problem in a board 3x3
	 */
	@Test
	public void testChessQueensNo() {
		chess = new ChessQueensAll(3);
		chess.backtracking(0);
		int result = chess.getNumberOfSolutions();
		assertEquals(0, result);
	}
	
}
