import java.util.*;

public class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        // Total positions available (each slot can have up to 2 numbers)
        int totalPositions = numSlots * 2;
        // Use memoization: dp[mask] stores the maximum AND sum from this state
        int[] dp = new int[1 << totalPositions];
        Arrays.fill(dp, -1);
        return dfs(nums, numSlots, 0, dp);
    }
    
    private int dfs(int[] nums, int numSlots, int mask, int[] dp) {
        int totalPositions = numSlots * 2;
        // index is the count of numbers already placed
        int index = Integer.bitCount(mask);
        if (index == nums.length) return 0;
        if (dp[mask] != -1) return dp[mask];
        
        int maxSum = 0;
        // Try to place nums[index] into any free slot position.
        for (int pos = 0; pos < totalPositions; pos++) {
            // If the current position is not used
            if ((mask & (1 << pos)) == 0) {
                // Determine slot number (1-indexed); each slot corresponds to two positions.
                int slot = pos / 2 + 1;
                // Calculate current AND value and add the result of further placements.
                int curr = (nums[index] & slot) + dfs(nums, numSlots, mask | (1 << pos), dp);
                maxSum = Math.max(maxSum, curr);
            }
        }
        
        dp[mask] = maxSum;
        return maxSum;
    }
  
}
