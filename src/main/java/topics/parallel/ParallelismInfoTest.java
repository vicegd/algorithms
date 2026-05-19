package topics.parallel;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

/**
 * <h1>Hardware Parallelism Inspection</h1>
 * <p>
 * Demonstrates how to programmatically query the Java Virtual Machine (JVM) 
 * to determine the available hardware execution threads, and how to explicitly 
 * override the default parallelism level when provisioning a {@link ForkJoinPool}.
 * </p>
 *
 * @author vicegd
 */
class ParallelismInfoTest {
    private static final Logger log = LoggerFactory.getLogger(ParallelismInfoTest.class);
  
    /**
     * <p><strong>Scenario:</strong> Querying system resources and pool constraints.</p>
     * <p><strong>Purpose:</strong> Outputs the maximum parallel capabilities of the host 
     * system alongside an artificially constrained thread pool to demonstrate isolation.</p>
     */
    @Test
    void shouldReportSystemParallelismCapabilities() {
        // Native JVM Hardware Capability Inquiry
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.debug("Available hardware processors (Logical Cores): {}", availableProcessors); 

        // ForkJoinPool explicit configuration (e.g., overriding defaults)
        int customParallelismLevel = 7;
        
        // Modernization: Utilizing try-with-resources to guarantee the pool 
        // shuts down cleanly and releases OS resources without manual finally blocks.
        try (var pool = new ForkJoinPool(customParallelismLevel)) {
            log.debug("Configured ForkJoinPool parallelism level: {}", pool.getParallelism());
        }
    }
}