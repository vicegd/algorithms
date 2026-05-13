package topics.parallel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parallel file processing task using the Fork/Join framework.
 *
 * Recursively splits a list of Java source files into smaller sub-tasks
 * (using {@link java.util.concurrent.RecursiveAction}) until each sub-task
 * contains at most {@code THRESHOLD} files, then processes them. Demonstrates
 * the Fork/Join divide-and-conquer pattern for parallel I/O workloads.
 *
 * @author vicegd
 * @see java.util.concurrent.ForkJoinPool
 * @see java.util.concurrent.RecursiveAction
 */
class FileProcessingTask extends RecursiveAction {
  private static Logger log = LoggerFactory.getLogger(FileProcessingTask.class);
  private static final long serialVersionUID = 1L;
  private static final int THRESHOLD = 5;
  private List<File> javaFiles = null;
  private String dirPath;
   
  public FileProcessingTask(String dirPath, List<File> javaFiles) {
    this.dirPath = dirPath;
    this.javaFiles = javaFiles;
  }
   
  @Override
  protected void compute() {
        if (javaFiles == null) { //First time to start processing files
          javaFiles = new ArrayList<File>();
          File sourceDir = new File(dirPath);
            if (sourceDir.isDirectory()) {
              for (File file : sourceDir.listFiles()){
                javaFiles.add(file);
              }
            }
        }
        if (javaFiles.size() <= THRESHOLD) {
          processFiles(javaFiles);
        }
        else {
            int center = javaFiles.size() / 2;
            List<File> part1 = javaFiles.subList(0, center);
            List<File> part2 = javaFiles.subList(center, javaFiles.size());
            invokeAll(new FileProcessingTask(dirPath, part1),
                new FileProcessingTask(dirPath, part2));
        }
  }
   
  protected void processFiles(List<File> filesToProcess) {
    for (File file : filesToProcess){
      log.trace(Thread.currentThread().getName()
          + " " + file.getName());
    }
  }
}
   
