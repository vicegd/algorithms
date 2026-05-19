package topics.parallel.fileprocessing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;

/**
 * <h1>Validation Suite for Parallel File Processing</h1>
 * <p>
 * Provisions a concurrent environment to benchmark the Fork/Join file processor.
 * </p>
 *
 * @author vicegd
 */
class FileProcessingTaskTest {
    private static final Logger log = LoggerFactory.getLogger(FileProcessingTaskTest.class);
    private static ForkJoinPool pool; 
  
    @BeforeAll
    static void setup() {
        log.trace("File Processing Task Tests - Initializing ForkJoinPool");
        pool = new ForkJoinPool();
    }
  
    @AfterAll
    static void teardown() {
        log.trace("File Processing Task Tests - Shutting Down ForkJoinPool");
        if (pool != null) {
            pool.shutdown();
        }
    }
  
    /**
     * <p><strong>Scenario:</strong> Scanning and processing a directory in parallel.</p>
     */
    @Test
    void shouldProcessFilesInParallel() {
        // Cross-platform safe path. Replaces the hardcoded "C:\\WINDOWS" to prevent 
        // test failures for students using macOS or Linux environments.
        var targetDirectory = Path.of(System.getProperty("user.dir"));
        
        var task = new FileProcessingTask(targetDirectory);                

        Instant start = Instant.now(); 
        pool.invoke(task); 
        Instant end = Instant.now();
      
        log.trace("Parallel File Processing completed in {} ms", Duration.between(start, end).toMillis());
    }
}