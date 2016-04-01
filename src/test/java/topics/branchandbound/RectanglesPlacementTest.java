package topics.branchandbound;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RectanglesPlacement JUnit tests
 * @author viceg
 */
public class RectanglesPlacementTest {
	private static Logger log = LoggerFactory.getLogger(RectanglesPlacementTest.class);
	private RectanglesPlacement rect;
	
	/**
	 * Initializes the object to perform tests
	 */
	@BeforeClass
	public static void setup() {
		log.trace("Rectangles Placement Tests - Setup");
	}
	
	/**
	 * Ends the object to perform tests
	 */
	@AfterClass
	public static void teardown() {
		log.trace("Rectangles Placement Tests - Teardown");
	}
	
	/**
	 * It gives the solution for a specific board and pieces (6 pieces)
	 */
	@Test
	public void testRectanglesPlacementOk() {
		int n = 5; //Size of the board
        
        //EXAMPLE 1
        List<Piece> pieces = new ArrayList<Piece>();
        Piece p1 = new Piece(2, 5);
        Piece p2 = new Piece(1, 3);
        Piece p3 = new Piece(1, 5);
        Piece p4 = new Piece(3, 1);
        Piece p5 = new Piece(1, 1);
        Piece p6 = new Piece(2, 1);
        pieces.add(p1); pieces.add(p2); pieces.add(p3); pieces.add(p4); pieces.add(p5); pieces.add(p6);
        
		rect = new RectanglesPlacement(n, pieces); 
		rect.branchAndBound(rect.getRootNode()); 
		rect.printSolutionTrace(); //There is always a solution for this problem
		int result = rect.getBestNode().getHeuristicValue();
		assertEquals(25, result);
	}
	
	/**
	 * It gives the solution for a specific board and pieces (3 pieces)
	 */
	@Test
	public void testRectanglesPlacementOk2() {
		int n = 5; //Size of the board
        
        //EXAMPLE 2
        List<Piece> pieces = new ArrayList<Piece>();
        Piece p1 = new Piece(1, 2);
        Piece p2 = new Piece(2, 2);
        Piece p3 = new Piece(1, 3);
        pieces.add(p1); pieces.add(p2); pieces.add(p3);
        
		rect = new RectanglesPlacement(n, pieces); 
		rect.branchAndBound(rect.getRootNode()); 
		rect.printSolutionTrace(); //There is always a solution for this problem
		int result = rect.getBestNode().getHeuristicValue();
		assertEquals(9, result);
	}
	
	/**
	 * It gives the solution for a specific board and pieces (2 pieces)
	 */
	@Test
	public void testRectanglesPlacementOk3() {
		int n = 5; //Size of the board
        
        //EXAMPLE 3
        List<Piece> pieces = new ArrayList<Piece>();
        Piece p1 = new Piece(1, 3);
        Piece p2 = new Piece(1, 1);
        pieces.add(p1); pieces.add(p2);     
        
		rect = new RectanglesPlacement(n, pieces); 
		rect.branchAndBound(rect.getRootNode()); 
		rect.printSolutionTrace(); //There is always a solution for this problem
		int result = rect.getBestNode().getHeuristicValue();
		assertEquals(4, result);
	}
	
	/**
	 * It gives no solution for a specific board and pieces (no enough space)
	 */
	@Test(expected=NullPointerException.class)
	public void testRectanglesPlacementNo() {
		int n = 2; //Size of the board

        List<Piece> pieces = new ArrayList<Piece>();
        Piece p1 = new Piece(1, 3);
        Piece p2 = new Piece(1, 1);
        pieces.add(p1); pieces.add(p2);     
        
		rect = new RectanglesPlacement(n, pieces); 
		rect.branchAndBound(rect.getRootNode()); 
		rect.printSolutionTrace(); //There is always a solution for this problem (with enough space)
		rect.getBestNode().getHeuristicValue();
	}
}
