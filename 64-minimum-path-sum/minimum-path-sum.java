class Solution {
    public int minPathSum(int[][] grid) {
 
        int m = grid.length;
        int n = grid[0].length;

        // Go through every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // First row (can only come from left)
                if (i == 0 && j != 0) {
                    grid[i][j] += grid[i][j - 1];
                }
                // First column (can only come from top)
                else if (j == 0 && i != 0) {
                    grid[i][j] += grid[i - 1][j];
                }
                // Else: take min of top or left
                else if (i != 0 && j != 0) {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        // The answer is in the bottom-right
        return grid[m - 1][n - 1];
    }
}
