package seminars.en._20.seminar4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seminars.en._20.seminar4.PathWorst;

public class PathWorstTest {
	private static Logger log = LoggerFactory.getLogger(PathWorstTest.class);
	private PathWorst path;
	
	@Test
	public void testPath() {	
		int n = 4;
		int[][]weights = new int[n][n]; 
		weights[0][0] = -1; weights[0][1] = 7; weights[0][2] = 9; weights[0][3] = 4;
		weights[1][0] = 3; weights[1][1] = -1; weights[1][2] = 2; weights[1][3] = -1;
		weights[2][0] = 4; weights[2][1] = 3; weights[2][2] = -1; weights[2][3] = 8;
		weights[3][0] = -1; weights[3][1] = 9; weights[3][2] = 9; weights[3][3] = -1;
		
		path = new PathWorst(n);
		path.setSource(0);
		path.setTarget(3);
		path.setWeightMatrix(weights);
		path.backtracking();
		
		log.debug("WEIGHT MATRIX");
		log.debug(path.writeWeights());
				
		log.debug("The number of solutions found were = " + path.getNumberSolutions());
		log.debug("The best cost is = " + path.getWorstCost()); 
		log.debug("The best cycle is = " + path.getWorstPath());
		
		assertEquals(3, path.getNumberSolutions());
		assertEquals(17, path.getWorstCost());	
		assertEquals("NODE0**NODE1**NODE2**NODE3**", path.getWorstPath());
	}
}
