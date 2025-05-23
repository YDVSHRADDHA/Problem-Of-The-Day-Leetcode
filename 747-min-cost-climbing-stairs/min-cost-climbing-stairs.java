// class Solution {
  
//     public int minCostClimbingStairs(int[] cost) {
//         int n = cost.length;
//         int[] dp = new int[n + 1]; // dp[i] = min cost to reach step i

//         for (int i = 2; i <= n; i++) {
//             dp[i] = Math.min(
//                 dp[i - 1] + cost[i - 1],
//                 dp[i - 2] + cost[i - 2]
//             );
//         }

//         return dp[n];
//     }
// }


class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int prev2 = 0, prev1 = 0;
        for (int i = 2; i <= cost.length; i++) {
            int curr = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
