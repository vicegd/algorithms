package topics.introduction;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicAlgorithmsTest {
	private static BasicAlgorithms alg;
	
	@BeforeClass
	public static void setup() {
		alg = new BasicAlgorithms();
	}
	
	@Test
	public void testSum() {
		int sum = alg.sum(10, 40);
		assertEquals("The addition was not executed correctly", 50, sum);
	}
	
	@Test
	public void testSum2() {
		int sum = alg.sum(10, 40);
		assertNotEquals("The addition was not executed correctly", 51, sum);
		assertNotEquals("The addition was not executed correctly", 49, sum);
	}

}
