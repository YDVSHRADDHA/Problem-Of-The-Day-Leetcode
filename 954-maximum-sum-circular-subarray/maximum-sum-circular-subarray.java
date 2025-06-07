public class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int maxSum = nums[0];
        int curMax = 0;
        int minSum = nums[0];
        int curMin = 0;

        for (int num : nums) {
            total += num;

            // Kadane’s for max subarray sum
            curMax = Math.max(curMax + num, num);
            maxSum = Math.max(maxSum, curMax);

            // Kadane’s for min subarray sum
            curMin = Math.min(curMin + num, num);
            minSum = Math.min(minSum, curMin);
        }

        // If all numbers are negative, maxSum is the result
        if (maxSum < 0) return maxSum;

        // Otherwise, return max of (normal subarray) vs (circular subarray)
        return Math.max(maxSum, total - minSum);
    }
}
