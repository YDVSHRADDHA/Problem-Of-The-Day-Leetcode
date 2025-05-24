class Solution {
    // Directions the knight can move
    private final int[][] directions = {
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
        {-2, 1}, {-1, 2}, {1, 2}, {2, 1}
    };

    // Memoization table
    private Double[][][] memo;

    public double knightProbability(int n, int k, int row, int column) {
        memo = new Double[n][n][k + 1];
        return dfs(n, k, row, column);
    }

    private double dfs(int n, int k, int r, int c) {
        // Out of bounds
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return 0;
        }

        // No more moves, and still on board
        if (k == 0) {
            return 1;
        }

        // Return cached result if available
        if (memo[r][c][k] != null) {
            return memo[r][c][k];
        }

        double prob = 0;
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            prob += dfs(n, k - 1, newRow, newCol) / 8.0;
        }

        memo[r][c][k] = prob;
        return prob;
    }
}
