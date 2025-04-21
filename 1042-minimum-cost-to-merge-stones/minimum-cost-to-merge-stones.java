class Solution {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        
        // If impossible to end with exactly one pile
        if ((n - 1) % (k - 1) != 0) return -1;
        
        // Prefix sum for quick range sum queries
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }
        
        // dp[i][j] = min cost to merge stones[i..j]
        int[][] dp = new int[n][n];
        
        for (int len = k; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // We try every mid step that makes valid (k-1) splits
                for (int mid = i; mid < j; mid += k - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                
                // If (len - 1) % (k - 1) == 0, we can merge these into 1 pile
                if ((len - 1) % (k - 1) == 0) {
                    dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
