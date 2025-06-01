import java.util.Arrays;

class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] allCuts = new int[m + 2];
        allCuts[0] = 0;
        allCuts[m + 1] = n;
        System.arraycopy(cuts, 0, allCuts, 1, m);
        Arrays.sort(allCuts);
        
        int[][] dp = new int[m + 2][m + 2];
        
        // bottom-up DP
        for (int length = 2; length <= m + 1; length++) {
            for (int i = 0; i + length <= m + 1; i++) {
                int j = i + length;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = allCuts[j] - allCuts[i] + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j] = 0; // no cuts between i and j
            }
        }
        
        return dp[0][m + 1];
    }
}
