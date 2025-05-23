// Approach 1: Recursion (TLE for n > 30)
// class Solution {
//     public int climbStairs(int n) {
//         if (n <= 2) return n;
//         return climbStairs(n - 1) + climbStairs(n - 2);
//     }
// }

// ❌ Time: O(2^n) — Not good for large n.

//  Approach 2: Recursion + Memoization

// class Solution {
//     public int climbStairs(int n) {
//         int[] dp = new int[n + 1];
//         Arrays.fill(dp, -1);
//         return solve(n, dp);
//     }

//     private int solve(int n, int[] dp) {
//         if (n <= 2) return n;
//         if (dp[n] != -1) return dp[n];
//         return dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
//     }
// }
// Time: O(n)

// Space: O(n) stack + memo

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
