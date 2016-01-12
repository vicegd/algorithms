package topics.introduction;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloWorldTest {
	private static HelloWorld helloWorld;
	
	@BeforeClass
	public static void setup() {
		helloWorld = new HelloWorld();
	}
	
	@Test
	public void testSum() {
		int sum = helloWorld.sum(10, 40);
		assertEquals("The addition was not executed correctly", 50, sum);
	}
	
	@Test
	public void testSum2() {
		int sum = helloWorld.sum(10, 40);
		assertNotEquals("The addition was not executed correctly", 51, sum);
		assertNotEquals("The addition was not executed correctly", 49, sum);
	}

}
