package topics.greedy;

import topics.sorting.Quicksort;

/**
 * <h1>Disk Packing</h1>
 * <p>
 * Demonstrates two distinct greedy strategies for packing files onto a disk with limited capacity.
 * Illustrates a critical concept in algorithm design: the same greedy paradigm can yield a 
 * mathematically optimal solution for one objective, but fail (act only as a heuristic) for another.
 * </p>
 *
 * @author vicegd
 */
public class DiskPacking {

    /**
     * <h2>Objective 1: Maximize Number of Files</h2>
     * <p>
     * <strong>Greedy Strategy:</strong> Sort files ascending. Pick the smallest files first.
     * <br><strong>Result:</strong> GUARANTEES the mathematically optimal maximum number of files.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N log N) - Bound by sorting.</li>
     * <li><strong>Space Complexity:</strong> O(N) - Defensive copy to prevent side-effects.</li>
     * </ul>
     *
     * @param files Array containing the sizes of available files.
     * @param discCapacity The maximum storage capacity of the disk.
     * @return The maximum number of files that can be stored.
     */
    public int maximizeFileCount(int[] files, int discCapacity) {
        if (files == null || files.length == 0 || discCapacity <= 0) return 0;

        // Defensive copy to prevent mutating the original input array
        int[] sortedFiles = files.clone();
        Quicksort quicksort = new Quicksort();
        quicksort.sort(sortedFiles);

        int usedSpace = 0;
        int fileCount = 0;

        // Iterate ascending: Smallest first
        for (int i = 0; i < sortedFiles.length; i++) {
            if (usedSpace + sortedFiles[i] <= discCapacity) {
                usedSpace += sortedFiles[i];
                fileCount++;
            } else {
                // Since it's sorted, if this one doesn't fit, no subsequent ones will either
                break; 
            }
        }
        return fileCount;
    }

    /**
     * <h2>Objective 2: Maximize Space Usage (Minimize Free Space)</h2>
     * <p>
     * <strong>Greedy Strategy:</strong> Sort files descending. Pick the largest files that fit.
     * <br><strong>Result:</strong> DOES NOT GUARANTEE the optimal solution. This is a heuristic 
     * (a fast approximation). Finding the absolute optimal configuration is a variant of the 
     * 0/1 Knapsack Problem, which requires Dynamic Programming O(N*W).
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N log N) - Bound by sorting.</li>
     * <li><strong>Space Complexity:</strong> O(N) - Defensive copy.</li>
     * </ul>
     *
     * @param files Array containing the sizes of available files.
     * @param discCapacity The maximum storage capacity of the disk.
     * @return The total amount of disk space utilized by the greedy heuristic.
     */
    public int maximizeSpaceUsage(int[] files, int discCapacity) {
        if (files == null || files.length == 0 || discCapacity <= 0) return 0;

        int[] sortedFiles = files.clone();
        Quicksort quicksort = new Quicksort();
        quicksort.sort(sortedFiles);

        int usedSpace = 0;

        // Iterate descending: Largest first
        for (int i = sortedFiles.length - 1; i >= 0; i--) {
            if (usedSpace + sortedFiles[i] <= discCapacity) {
                usedSpace += sortedFiles[i];
            }
        }
        return usedSpace;
    }
}