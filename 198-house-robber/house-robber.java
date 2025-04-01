class Solution {
   public int rob(int[] nums) {
    int n = nums.length;
    
    // Base cases: If no houses, return 0. If there's only one house, return its value.
    if (n == 0) return 0;
    if (n == 1) return nums[0];

    int[] dp = new int[n];  // DP array to store the maximum money robbed up to each house
    dp[0] = nums[0];  // The maximum money robbed from the first house is just the value of the first house
    dp[1] = Math.max(nums[0], nums[1]);  // For the second house, either rob the first or second house, whichever is higher

    // Loop through the houses starting from index 2
    for (int i = 2; i < n; i++) {
        // Calculate the maximum money we can rob at house i:
        // 1. Either we skip this house and take the max money from the previous house (dp[i-1])
        // 2. Or we rob this house, adding the money from house i to the maximum money robbed up to house i-2 (dp[i-2] + nums[i])
        dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
    }

    return dp[n-1];  // Return the maximum money robbed after considering all houses
}

}
