import java.util.Arrays;

class Solution {
    public int countWays(int[][] ranges) {
        int MOD = 1_000_000_007;
        
        // Step 1: Sort intervals by start
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

         // Step 2: Merge overlapping intervals and count connected components
        int components = 0;
        int prevEnd = -1;

        for (int[] range : ranges) {
            if (range[0] > prevEnd) {
                components++;  // New component found
            }
            prevEnd = Math.max(prevEnd, range[1]); // Update previous end
        }

           // Step 3: Calculate 2^components % MOD
        long result = 1;
        for (int i = 0; i < components; i++) {
            result = (result * 2) % MOD;
        }

        return (int) result;
    }
}