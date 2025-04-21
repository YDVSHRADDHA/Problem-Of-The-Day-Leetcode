class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;

        // Find min and max value in nums
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        long result = Long.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            long costMid = calculateCost(nums, cost, mid);
            long costMidPlus = calculateCost(nums, cost, mid + 1);

            result = Math.min(costMid, costMidPlus);

            if (costMid < costMidPlus) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private long calculateCost(int[] nums, int[] cost, int target) {
        long total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += Math.abs((long) nums[i] - target) * cost[i];
        }
        return total;
    }
}
