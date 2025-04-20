public class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] prefix = new int[n + 1];  // prefix sum

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        int[][] dp = new int[n][n];  // dp[i][j] = max score diff from i to j

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                int removeLeft = prefix[j + 1] - prefix[i + 1] - dp[i + 1][j];
                int removeRight = prefix[j] - prefix[i] - dp[i][j - 1];
                dp[i][j] = Math.max(removeLeft, removeRight);
            }
        }

        return dp[0][n - 1];
    }
}
