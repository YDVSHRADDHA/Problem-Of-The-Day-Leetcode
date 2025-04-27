class Solution {
    public boolean isMatch(String s, String p) {
       int m = s.length();
        int n = p.length();
        
        // DP table initialization
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // Base case: empty pattern matches empty string
        dp[0][0] = true;
        
        // Handle cases where the pattern contains '*' that can match an empty string
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        
        // Return the result for the entire string and pattern
        return dp[m][n];
    }
   
    }
