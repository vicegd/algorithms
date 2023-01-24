package topics.introduction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
