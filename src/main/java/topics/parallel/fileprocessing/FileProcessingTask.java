package topics.parallel.fileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

/**
 * <h1>Parallel File Processing (Fork/Join)</h1>
 * <p>
 * Demonstrates the divide-and-conquer pattern utilizing Java's {@link RecursiveAction}.
 * It recursively splits a list of files into smaller sub-tasks until they hit 
 * the defined threshold, allowing the ForkJoinPool to process the I/O workload in parallel.
 * </p>
 * <p>
 * <strong>Modernization Note:</strong> Utilizes NIO.2 ({@link Path}, {@link Files}) 
 * for robust and efficient filesystem traversal.
 * </p>
 *
 * @author vicegd
 * @see java.util.concurrent.ForkJoinPool
 */
public class FileProcessingTask extends RecursiveAction {
    private static final Logger log = LoggerFactory.getLogger(FileProcessingTask.class);
    private static final long serialVersionUID = 1L;
    
    /** The granularity threshold. Maximum files processed per thread. */
    private static final int THRESHOLD = 5;
    
    private final List<Path> filesToProcess;

    /**
     * Root Constructor: Initializes the task by scanning the target directory.
     * <p>
     * Utilizes Java Streams to efficiently filter and collect regular files, 
     * ignoring sub-directories.
     * </p>
     *
     * @param directory The root directory to scan.
     */
    public FileProcessingTask(Path directory) {
        List<Path> scannedFiles = List.of();
        try (var stream = Files.list(directory)) {
            scannedFiles = stream
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Failed to read directory: {}", directory, e);
        }
        this.filesToProcess = scannedFiles;
    }

    /**
     * Internal Recursive Constructor: Used strictly by the fork/join split logic.
     *
     * @param files The subset of files to process.
     */
    private FileProcessingTask(List<Path> files) {
        this.filesToProcess = files;
    }

    @Override
    protected void compute() {
        if (filesToProcess.isEmpty()) {
            return;
        }

        // Base Case: Process files if the list is within the threshold
        if (filesToProcess.size() <= THRESHOLD) {
            processFiles(filesToProcess);
        } else {
            // Recursive Case: Divide the list into two halves and invoke parallel tasks
            int center = filesToProcess.size() / 2;
            
            var part1 = new FileProcessingTask(filesToProcess.subList(0, center));
            var part2 = new FileProcessingTask(filesToProcess.subList(center, filesToProcess.size()));
            
            invokeAll(part1, part2);
        }
    }

    /**
     * Simulates the actual processing of the file batch within the current thread.
     */
    private void processFiles(List<Path> batch) {
        String threadName = Thread.currentThread().getName();
        for (Path file : batch) {
            log.trace("[{}] Processing: {}", threadName, file.getFileName());
        }
    }
}