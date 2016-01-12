package topics.introduction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
	static Logger log = LoggerFactory.getLogger(HelloWorld.class);
	
	public int sum(int a, int b) {
		log.info("Adding two numbers");
		return a+b;
	}
}
