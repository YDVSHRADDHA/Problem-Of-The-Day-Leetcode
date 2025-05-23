// Approach 1: Pure Recursion (❌ Not Efficient)
// Time Complexity: Exponential O(3^n)

// Space Complexity: O(n) stack frames

// ❌ Avoid for n > 15 
// class Solution {
//     public int tribonacci(int n) {
//         if (n == 0) return 0;
//         if (n == 1 || n == 2) return 1;
//         return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
//     }
// }


//  Approach 2: Recursion + Memoization (Top-Down DP)
class Solution {
    public int tribonacci(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }

    private int helper(int n, int[] dp) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (dp[n] != -1) return dp[n];
        dp[n] = helper(n - 1, dp) + helper(n - 2, dp) + helper(n - 3, dp);
        return dp[n];
    }
}
