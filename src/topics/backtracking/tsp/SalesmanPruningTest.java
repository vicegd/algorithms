package topics.backtracking.tsp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesmanPruningTest {
	private static Logger log = LoggerFactory.getLogger(SalesmanPruningTest.class);
	private SalesmanPruning salesman;
	
	@Test
	public void testSalesman() {	
		int n = 7;
		int source = 0;		
		int[][] w = new int[n][n];				
		w[0][1]=27;w[1][0]=27;w[0][2]=11;w[2][0]=11;
		w[0][3]=17;w[3][0]=17;w[0][4]=20;w[4][0]=20;
		w[0][5]=12;w[5][0]=12;w[0][6]=32;w[6][0]=32;
		w[1][2]=15;w[2][1]=15;w[1][3]=26;w[3][1]=26;
		w[1][4]=21;w[4][1]=21;w[1][5]=30;w[5][1]=30;
		w[1][6]=13;w[6][1]=13;w[2][3]=28;w[3][2]=28;
		w[2][4]=14;w[4][2]=14;w[2][5]=23;w[5][2]=23;
		w[2][6]=22;w[6][2]=22;w[3][4]=19;w[4][3]=19;
		w[3][5]=18;w[5][3]=18;w[3][6]=22;w[6][3]=22;
		w[4][5]=16;w[5][4]=16;w[4][6]=23;w[6][4]=23;
		w[5][6]=24;w[6][5]=24;
		w[0][0]=-1;w[1][1]=-1;w[2][2]=-1;w[3][3]=-1;
		w[4][4]=-1;w[5][5]=-1;w[6][6]=-1;
		
		salesman = new SalesmanPruning(n, source, w); 

		log.debug("WEIGHT MATRIX");
		log.debug(salesman.writeWeights());
		
		salesman.backtracking();		
		log.debug("The number of solutions found were = " + salesman.getNumberSolutions());
		log.debug("The best cost is = " + salesman.getBestCost()); 
		log.debug("The best cycle is = " + salesman.getBestPath());
		
		assertEquals(8, salesman.getNumberSolutions());
		assertEquals(108, salesman.getBestCost());	
		assertEquals("NODE0**NODE2**NODE1**NODE6**NODE3**NODE4**NODE5**NODE0**", salesman.getBestPath());
	}
}
