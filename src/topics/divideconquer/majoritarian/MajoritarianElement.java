package topics.divideconquer.majoritarian;

import java.util.Arrays;

/**
 * <h1>Majoritarian Element</h1>
 * <p>
 * Evaluates whether an array contains a "Majoritarian Element" (an element that 
 * appears strictly more than N/2 times). This class demonstrates the evolution 
 * of algorithmic efficiency from a brute-force approach to a linear Divide & Conquer approach.
 * </p>
 *
 * @author vicegd
 */
public class MajoritarianElement {
    /**
     * <h2>1. Naive Iterative Approach</h2>
     * <p>
     * Checks every single element and counts its total occurrences across the entire array.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N&sup2;) - Quadratic</li>
     * <li><strong>Space Complexity:</strong> O(1) - Constant</li>
     * </ul>
     *
     * @param v Array of elements.
     * @return True if a majoritarian element exists, false otherwise.
     */
    public boolean hasMajorityNaive(int[] v) {
        if (v == null || v.length == 0) return false;
        
        int n = v.length;
        int majorityThreshold = n / 2 + 1; 
        
        // We only need to check the first half. If the majority element hasn't 
        // appeared by the midpoint, it's mathematically impossible for it to be the majority.
        for (int i = 0; i < n / 2 + 1; i++) {
            int counter = 0; 
            for (int j = i; j < n; j++) {
                if (v[j] == v[i]) counter++;
            }
            if (counter >= majorityThreshold) return true; 
        }
        return false;
    }    

    /**
     * <h2>2. Sorting Approach</h2>
     * <p>
     * Sorts the array first. If a majoritarian element exists, it <em>must</em> 
     * occupy the mathematical center of the sorted array (index N/2). We then 
     * just count how many times this central element appears.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N log N) - Bound by the sorting step</li>
     * <li><strong>Space Complexity:</strong> O(N) - Defensive copy to avoid mutating the input</li>
     * </ul>
     *
     * @param v Array of elements.
     * @return True if a majoritarian element exists, false otherwise.
     */
    public boolean hasMajoritySorting(int[] v) {
        if (v == null || v.length == 0) return false;

        // Defensive copy: do not mutate the caller's array
        int[] copy = v.clone();
        Arrays.sort(copy);
        
        int n = copy.length;
        int majorityThreshold = n / 2 + 1;
        int counter = 0;
        int candidate = copy[n / 2]; // The theoretical candidate MUST be in the middle
        
        for (int i = 0; i < n; i++) {
            if (copy[i] == candidate) counter++;
        }
        
        return counter >= majorityThreshold; 
    }    

    /**
     * <h2>3. Divide & Conquer Approach (Tournament/Pairing Method)</h2>
     * <p>
     * Recursively eliminates pairs of elements. If adjacent elements are identical, 
     * one is kept for the next round. If they differ, both are discarded. 
     * This dramatically reduces the search space linearly.
     * </p>
     * <ul>
     * <li><strong>Time Complexity:</strong> O(N) - Linear</li>
     * <li><strong>Space Complexity:</strong> O(N) - Array cloning and call stack</li>
     * </ul>
     *
     * @param v Array of elements.
     * @return True if a majoritarian element exists, false otherwise.
     */
    public boolean hasMajorityDivideAndConquer(int[] v) {
        if (v == null || v.length == 0) return false;
        
        int n = v.length;
        int majorityThreshold = n / 2 + 1;
        int[] candidateBox = new int[1]; // Array used as a mutable pointer reference
        
        // Backup the original array (JVM native, extremely fast)
        // since the recursive division strictly mutates it in-place.
        int[] backup = v.clone(); 
        
        boolean candidateFound = majorityByDivision(0, n - 1, candidateBox, v);
        
        // Restore the original array state
        System.arraycopy(backup, 0, v, 0, n);
        
        // Validation step: We must verify if the surviving candidate actually meets the threshold
        if (candidateFound) {
            int counter = 0;
            for (int i = 0; i < n; i++) {
                if (v[i] == candidateBox[0]) counter++;
            }
            return counter >= majorityThreshold; 
        }  
        return false; 
    }
    
    /**
     * Private recursive helper for the Tournament method.
     */
    private boolean majorityByDivision(int left, int right, int[] candidateBox, int[] v) { 
        int segmentLength = right - left + 1;
        
        if (segmentLength <= 0) return false;
        
        candidateBox[0] = v[left];
        if (segmentLength == 1) return true;
        
        int j = left;
        
        if (segmentLength % 2 == 0) { // Even length
            for (int i = left + 1; i <= right; i += 2) {
                if (v[i - 1] == v[i]) {
                    v[j] = v[i];
                    j++;
                }
            }
            return majorityByDivision(left, j - 1, candidateBox, v);
        } else { // Odd length
            for (int i = left + 1; i <= right - 1; i += 2) {
                if (v[i - 1] == v[i]) {
                    v[j] = v[i];
                    j++;
                }
            }
            if (!majorityByDivision(left, j - 1, candidateBox, v)) {
                candidateBox[0] = v[right]; // The leftover odd element becomes the candidate
            }
            return true;
        }
    }
}