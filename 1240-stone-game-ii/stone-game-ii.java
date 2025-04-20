public class Solution {
    int[] piles;
    int[][] memo;
    int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        this.piles = piles;
        this.memo = new int[n][n + 1];

        // Suffix sum to optimize total remaining stones from i
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        int n = piles.length;
        if (i >= n) return 0;
        if (memo[i][m] != 0) return memo[i][m];
        if (i + 2 * m >= n) return suffixSum[i]; // take all remaining

        int minOpponent = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * m; x++) {
            minOpponent = Math.min(minOpponent, dfs(i + x, Math.max(m, x)));
        }

        memo[i][m] = suffixSum[i] - minOpponent;
        return memo[i][m];
    }
}
