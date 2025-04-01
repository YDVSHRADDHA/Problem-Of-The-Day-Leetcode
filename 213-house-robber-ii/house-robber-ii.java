class Solution {
  public int rob(int[] nums) {
    // If there's only one house, rob it
    if (nums.length == 1) return nums[0];

    // Solve the problem in two subproblems:
    // 1. Exclude the first house and solve for the rest (from index 1 to n-1)
    // 2. Exclude the last house and solve for the rest (from index 0 to n-2)
    return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
}

// Helper function to solve the problem when we know which houses to consider (range from start to end)
private int robHelper(int[] nums, int start, int end) {
    int prev1 = 0, prev2 = 0;  // prev1 stores the max money robbed till i-1, prev2 stores max till i-2
    
    for (int i = start; i <= end; i++) {
        int temp = prev1;
        prev1 = Math.max(prev1, prev2 + nums[i]);  // Either rob this house (prev2 + nums[i]) or skip it (prev1)
        prev2 = temp;  // Update prev2 for the next iteration
    }
    return prev1;  // The max money robbed from start to end
}

}