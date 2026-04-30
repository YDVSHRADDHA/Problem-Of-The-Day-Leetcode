class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int NEG = -1_000_000_000;

        int[][] dp = new int[n][k + 1];

        // initialize dp with NEG
        for (int j = 0; j < n; j++) {
            for (int c = 0; c <= k; c++) {
                dp[j][c] = NEG;
            }
        }

        dp[0][0] = 0;

        for (int i = 0; i < m; i++) {
            int[][] newDp = new int[n][k + 1];

            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    newDp[j][c] = NEG;
                }
            }

            for (int j = 0; j < n; j++) {
                int cell = grid[i][j];
                int cost = (cell == 0) ? 0 : 1;
                int score = cell;

                for (int c = 0; c <= k; c++) {
                    if (c < cost) continue;

                    // from UP
                    if (i > 0 && dp[j][c - cost] != NEG) {
                        newDp[j][c] = Math.max(newDp[j][c], dp[j][c - cost] + score);
                    }

                    // from LEFT
                    if (j > 0 && newDp[j - 1][c - cost] != NEG) {
                        newDp[j][c] = Math.max(newDp[j][c], newDp[j - 1][c - cost] + score);
                    }

                    // start cell
                    if (i == 0 && j == 0 && c == 0) {
                        newDp[j][c] = 0;
                    }
                }
            }

            dp = newDp;
        }

        int ans = NEG;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, dp[n - 1][c]);
        }

        return ans == NEG ? -1 : ans;
    }
}