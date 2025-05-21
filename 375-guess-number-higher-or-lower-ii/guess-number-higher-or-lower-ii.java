class Solution {
    public int getMoneyAmount(int n) {
 
        int[][] dp = new int[n + 1][n + 1];

        // start from smaller ranges to bigger ranges
        for (int length = 2; length <= n; length++) {
            for (int start = 1; start <= n - length + 1; start++) {
                int end = start + length - 1;
                dp[start][end] = Integer.MAX_VALUE;

                for (int x = start; x <= end; x++) {
                    int costLeft = (x - 1 >= start) ? dp[start][x - 1] : 0;
                    int costRight = (x + 1 <= end) ? dp[x + 1][end] : 0;
                    int cost = x + Math.max(costLeft, costRight);

                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }

        return dp[1][n];
    }
}
