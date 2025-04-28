class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: Each single character requires 1 turn.
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the dp table for substrings of increasing lengths.
        for (int len = 2; len <= n; len++) { // len is the length of the substring
            for (int i = 0; i <= n - len; i++) { // i is the starting index of the substring
                int j = i + len - 1; // j is the ending index of the substring

                // Initialize dp[i][j] as the worst case: print the substring one character at a time
                dp[i][j] = dp[i][j - 1] + 1;

                // If s[i] == s[j], we may reduce the number of turns
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }

                // Try splitting the substring into two parts
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }
}




