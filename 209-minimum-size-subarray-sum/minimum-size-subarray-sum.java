public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int start = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int end = 0; end < n; end++) {
            sum += nums[end];

            // Shrink window while sum >= target
            while (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
