class Solution {
    //112ms
    public boolean possiblyEquals(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        Boolean[][][] dp = new Boolean[l1 + 1][l2 + 1][2000]; 
        return dfs(0, 0, 0, s1.toCharArray(), s2.toCharArray(), dp);
    }

    private boolean dfs(int i, int j, int diff, char[] s1, char[] s2, Boolean[][][] dp) {
        if (i == s1.length && j == s2.length) {
            return diff == 0;
        }

        if (dp[i][j][diff + 1000] != null) return dp[i][j][diff + 1000];

        if (i < s1.length && j < s2.length && diff == 0 && s1[i] == s2[j]) {
            if (dfs(i + 1, j + 1, 0, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        if (i < s1.length && !isDigit(s1[i]) && diff > 0) {
            if (dfs(i + 1, j, diff - 1, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        if (j < s2.length && !isDigit(s2[j]) && diff < 0) {
            if (dfs(i, j + 1, diff + 1, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // Try digits in s1
        for (int k = i, val = 0; k < s1.length && isDigit(s1[k]); ++k) {
            val = val * 10 + (s1[k] - '0');
            if (dfs(k + 1, j, diff - val, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        // Try digits in s2
        for (int k = j, val = 0; k < s2.length && isDigit(s2[k]); ++k) {
            val = val * 10 + (s2[k] - '0');
            if (dfs(i, k + 1, diff + val, s1, s2, dp)) {
                return dp[i][j][diff + 1000] = true;
            }
        }

        return dp[i][j][diff + 1000] = false;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
