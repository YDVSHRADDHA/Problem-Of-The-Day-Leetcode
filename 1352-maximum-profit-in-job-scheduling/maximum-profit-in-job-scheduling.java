import java.util.*;

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        // Create jobs array: [start, end, profit]
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        
        // Sort jobs by end time
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[1], b[1]));
        
        // dp[i] = max profit including jobs up to i-th (sorted by end time)
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        
        for (int i = 1; i < n; i++) {
            int currProfit = jobs[i][2];
            
            // Binary search to find last job that ends before jobs[i] starts
            int l = 0, r = i - 1;
            int idx = -1;  // index of compatible job
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (jobs[mid][1] <= jobs[i][0]) {
                    idx = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            
            if (idx != -1) {
                currProfit += dp[idx];
            }
            
            dp[i] = Math.max(dp[i - 1], currProfit);
        }
        
        return dp[n - 1];
    }
}
