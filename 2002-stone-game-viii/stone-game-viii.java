public class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Step 1: Build prefix sum array
        int[] prefix = new int[n];
        prefix[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // Step 2: dp[i] = max score difference starting at index i
        // We only consider i >= 1 because Alice must remove at least one stone
        int dp = prefix[n - 1];  // Initial value when only last prefix is considered

        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }

        return dp;
    }
}
