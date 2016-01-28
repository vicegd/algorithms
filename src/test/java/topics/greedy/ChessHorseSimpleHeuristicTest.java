package topics.greedy;

import static org.junit.Assert.assertFalse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChessHorseSimpleHeuristic JUnit tests
 * @author viceg
 */
public class ChessHorseSimpleHeuristicTest {
	private static Logger log = LoggerFactory.getLogger(ChessHorseSimpleHeuristicTest.class);
	private ChessHorseSimpleHeuristic chess;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Chess Horse Simple Heuristic - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Chess Horse Simple Heuristic - Teardown");
	}
	
	/**
	 * Shows the state of the board
	 * In this case there is not solution
	 */
	@Ignore("Not ready yet")
	@Test
	public void testChess() {
		int n = 5;
		int[] initialPos = new int[]{ 3, 4}; //PosX and PosY
		
		chess = new ChessHorseSimpleHeuristic(n);
		boolean result = chess.horse(initialPos); //Initial position of the horse (x, y)
		assertFalse(result);
	}
	
}
