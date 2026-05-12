package topics.introduction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Generates a test data file of random integers for the MaxPairWiseProduct problem.
 *
 * Writes 100,000 random integers (each in range [0, 99]) space-separated into
 * {@code MaxPairWiseProductRandomNumbers.txt} so that the MaxPairWiseProduct
 * algorithm implementations can be benchmarked against a large input.
 *
 * @author vicegd
 * @see topics.introduction.MaxPairWiseProduct
 */
public class MaxPairWiseProductRandomNumbers {
	public static void main(String...args) throws IOException {
		Path path = Paths.get("src/main/java/topics/introduction/MaxPairWiseProductRandomNumbers.txt");
		BufferedWriter writer = Files.newBufferedWriter(path);
		Random r = new Random();
		for (int i = 0; i < 100_000; i++) {
			writer.write(r.nextInt(100) + " ");
		}
		writer.close();
	}
}
