package topics.divideconquer;

/**
 * <h1>Maximum Subarray Sum</h1>
 * <p>
 * Evaluates a sequence of numbers to find the contiguous sub-sequence that 
 * produces the largest possible sum. This class demonstrates the architectural 
 * evolution from a brute-force Cubic approach to a Logarithmic Divide & Conquer approach.
 * </p>
 *
 * @author vicegd
 */
public class MaxSum {

    /**
     * <h2>1. Naive Cubic Approach O(N&sup3;)</h2>
     * <p>
     * Tests every possible combination of start and end indices (i and j), 
     * and uses a third nested loop (k) to calculate the sum from scratch every time.
     * </p>
     * * @param v Array of integers.
     * @return The maximum contiguous sum.
     */
    public int maxSubarrayCubic(int[] v) {
        if (v == null || v.length == 0) return 0;
        
        int max = Integer.MIN_VALUE; // Protects against all-negative arrays
        int n = v.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                // Recalculates the sum from scratch (The cubic bottleneck)
                for (int k = i; k <= j; k++) {
                    sum += v[k];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    } 

    /**
     * <h2>2. Optimized Quadratic Approach O(N&sup2;)</h2>
     * <p>
     * Eliminates the third loop. It recognizes that the sum of array[i..j] 
     * is simply array[i..j-1] + array[j]. It accumulates the sum dynamically.
     * </p>
     * * @param v Array of integers.
     * @return The maximum contiguous sum.
     */
    public int maxSubarrayQuadratic(int[] v) {
        if (v == null || v.length == 0) return 0;
        
        int max = Integer.MIN_VALUE;
        int n = v.length;
        
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += v[j]; // Accumulates dynamically
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    } 

    /**
     * <h2>3. Divide & Conquer Approach O(N log N)</h2>
     * <p>
     * Divides the array into two halves recursively. The maximum subarray must lie:
     * 1. Entirely in the left half.
     * 2. Entirely in the right half.
     * 3. Crossing the midpoint.
     * </p>
     * * @param v Array of integers.
     * @return The maximum contiguous sum.
     */
    public int maxSubarrayDivideAndConquer(int[] v) {
        if (v == null || v.length == 0) return 0;
        return maxSumByDivision(0, v.length - 1, v);
    }

    private int maxSumByDivision(int left, int right, int[] v) {  
        if (left == right) {
            return v[left]; // Base case: 1 element
        }

        // Prevent Integer Overflow mathematically
        int center = left + (right - left) / 2;
        
        int maxLeft = maxSumByDivision(left, center, v);
        int maxRight = maxSumByDivision(center + 1, right, v);  

        // CROSSING SUM CALCULATION
        // Scan leftward from the center
        int sum1 = 0;
        int maxSum1 = Integer.MIN_VALUE;
        for (int i = center; i >= left; i--) {
            sum1 += v[i];
            if (sum1 > maxSum1) maxSum1 = sum1;
        }

        // Scan rightward from the center
        int sum2 = 0;
        int maxSum2 = Integer.MIN_VALUE;
        for (int i = center + 1; i <= right; i++) {
            sum2 += v[i];
            if (sum2 > maxSum2) maxSum2 = sum2;
        }

        return biggest(maxLeft, maxRight, maxSum1 + maxSum2);
    }

    /**
     * Helper to return the largest of three numbers.
     */
    private int biggest(int a, int b, int c) { 
        return Math.max(a, Math.max(b, c));
    }
}