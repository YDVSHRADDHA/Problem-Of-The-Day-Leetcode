class Solution {
    public int minCut(String s) {
    
    int n = s.length();
    boolean[][] isPal = new boolean[n][n];
    int[] dp = new int[n];

    for (int end = 0; end < n; end++) {
        int minCut = end;
        for (int start = 0; start <= end; start++) {
            if (s.charAt(start) == s.charAt(end) &&
                (end - start <= 2 || isPal[start + 1][end - 1])) {
                
                isPal[start][end] = true;
                minCut = (start == 0) ? 0 : Math.min(minCut, dp[start - 1] + 1);
            }
        }
        dp[end] = minCut;
    }

    return dp[n - 1];
}
  
}