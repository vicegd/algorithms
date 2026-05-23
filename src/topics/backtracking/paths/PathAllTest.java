package topics.backtracking.paths;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seminars.en._20.seminar4.PathAll;

public class PathAllTest {
	private static Logger log = LoggerFactory.getLogger(PathAllTest.class);
	private PathAll path;
		
	@Test
	public void testPathInput4() {	
		int n = 4;
		int threshold = 80;
		
		int[][]weights = new int[n][n]; 
		weights[0][0] = -1; weights[0][1] = 4; weights[0][2] = 9; weights[0][3] = -1;
		weights[1][0] = 3; weights[1][1] = -1; weights[1][2] = 2; weights[1][3] = 8;
		weights[2][0] = -1; weights[2][1] = 9; weights[2][2] = -1; weights[2][3] = 1;
		weights[3][0] = 2; weights[3][1] = 6; weights[3][2] = -1; weights[3][3] = -1;
		
		path = new PathAll(n, threshold);
		path.setSource(3);
		path.setTarget(2);
		path.setWeightMatrix(weights);
		path.backtracking();
		
		assertEquals(310422, path.getNumberSolutions());
		log.debug("The number of solutions found were = " + path.getNumberSolutions());
	}
}
