package topics.branchandbound;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EightPuzzle JUnit tests
 * @author viceg
 */
public class EightPuzzleTest {
	private static Logger log = LoggerFactory.getLogger(EightPuzzleTest.class);
	private EightPuzzle puzzle;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Eight Puzzle Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Eight Puzzle Tests - Teardown");
	}
	
	/**
	 * It gives the solution for a specific position of the pieces (18 steps)
	 */
	@Test
	public void testEightPuzzleOk() {
		HeuristicType heuristicType = HeuristicType.Manhattan; //Manhattan or WrongPlace	
    	
		//EXAMPLE 1 (IT HAS A VALID SOLUTION). 18 Steps with Manhattan
		int[] board = new int[9]; 
		board[0] = 2; board[1] = 3; board[2] = 6; board[3] = 1; board[4] = 5;
    	board[5] = 4; board[6] = 9; board[7] = 7; board[8] = 8;
		puzzle = new EightPuzzle(heuristicType, board); 
		puzzle.branchAndBound(puzzle.getRootNode()); 
		puzzle.printSolutionTrace(); 
		int result = puzzle.getBestNode().getHeuristicValue();
		assertEquals(0, result);
	}
	
	/**
	 * It gives the solution for a specific position of the pieces (54 steps)
	 */
	@Test
	public void testEightPuzzleOk2() {
		HeuristicType heuristicType = HeuristicType.WrongPlace; //Manhattan or WrongPlace	
    	
		//EXAMPLE 2 (IT HAS A VALID SOLUTION). 54 Steps with WrongPlace
		int[] board = new int[9]; 
		board[0] = 2; board[1] = 3; board[2] = 6; board[3] = 1; board[4] = 5;
    	board[5] = 4; board[6] = 9; board[7] = 7; board[8] = 8;
		puzzle = new EightPuzzle(heuristicType, board); 
		puzzle.branchAndBound(puzzle.getRootNode()); 
		puzzle.printSolutionTrace(); 
		int result = puzzle.getBestNode().getHeuristicValue();
		assertEquals(0, result);
	}
	
	/**
	 * It gives the solution for a specific position of the pieces (1 step)
	 */
	@Test
	public void testEightPuzzleOk3() {
		HeuristicType heuristicType = HeuristicType.Manhattan; //Manhattan or WrongPlace

    	//EXAMPLE 3 (IT HAS A VALID SOLUTION). IT IS ALREADY DONE
		int[] board = new int[9];
    	board[0] = 1; board[1] = 2; board[2] = 3; board[3] = 4; board[4] = 5; 
    	board[5] = 6; board[6] = 7; board[7] = 8; board[8] = 9;

		puzzle = new EightPuzzle(heuristicType, board); 
		puzzle.branchAndBound(puzzle.getRootNode()); 
		puzzle.printSolutionTrace(); 
		int result = puzzle.getBestNode().getHeuristicValue();
		assertEquals(0, result);
	}
	
	/**
	 * It gives no solution for a specific position of the pieces
	 */
	@Test(expected=NullPointerException.class)
	public void testEightPuzzleNo() {
		HeuristicType heuristicType = HeuristicType.Manhattan; //Manhattan or WrongPlace

    	//EXAMPLE 4 (IT HAS NOT A VALID SOLUTION)
		int[] board = new int[9];
    	board[0] = 9; board[1] = 3; board[2] = 7; board[3] = 6; board[4] = 5;
    	board[5] = 4; board[6] = 8; board[7] = 2; board[8] = 1;
		
		puzzle = new EightPuzzle(heuristicType, board); 
		puzzle.branchAndBound(puzzle.getRootNode()); 
		puzzle.printSolutionTrace(); 
		puzzle.getBestNode().getHeuristicValue();
	}
}
