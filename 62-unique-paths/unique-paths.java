class Solution {
    public int uniquePaths(int m, int n) {
 
        int[][] dp = new int[m][n];
        
        // Step 1: Initialize first row and first column to 1
        // Because you can only move right along the first row (1 way)
        // and only move down along the first column (1 way)
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Step 2: Fill the rest of the dp array
        // For each cell, ways to get there = ways from the cell above + ways from the cell left
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        // Step 3: Return the ways to get to the bottom-right corner
        return dp[m-1][n-1];
    }
}
