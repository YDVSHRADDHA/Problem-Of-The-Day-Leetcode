class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;

        // dp[i][j][k] = max coins at (i,j) using k neutralizations
        int[][][] dp = new int[m][n][3];

        // Initialize with very small values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2;
                }
            }
        }

        // Base case (0,0)
        if (coins[0][0] >= 0) {
            for (int k = 0; k < 3; k++) {
                dp[0][0][k] = coins[0][0];
            }
        } else {
            dp[0][0][0] = coins[0][0]; // no neutralization
            dp[0][0][1] = 0;           // neutralize
            dp[0][0][2] = 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                for (int k = 0; k < 3; k++) {

                    int bestPrev = Integer.MIN_VALUE / 2;

                    // from top
                    if (i > 0) {
                        bestPrev = Math.max(bestPrev, dp[i - 1][j][k]);
                    }

                    // from left
                    if (j > 0) {
                        bestPrev = Math.max(bestPrev, dp[i][j - 1][k]);
                    }

                    // Case 1: do NOT neutralize
                    dp[i][j][k] = bestPrev + coins[i][j];

                    // Case 2: neutralize (only if negative and k > 0)
                    if (coins[i][j] < 0 && k > 0) {
                        int bestPrevK1 = Integer.MIN_VALUE / 2;

                        if (i > 0) {
                            bestPrevK1 = Math.max(bestPrevK1, dp[i - 1][j][k - 1]);
                        }
                        if (j > 0) {
                            bestPrevK1 = Math.max(bestPrevK1, dp[i][j - 1][k - 1]);
                        }

                        dp[i][j][k] = Math.max(dp[i][j][k], bestPrevK1);
                    }
                }
            }
        }

        return Math.max(dp[m - 1][n - 1][0],
               Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}