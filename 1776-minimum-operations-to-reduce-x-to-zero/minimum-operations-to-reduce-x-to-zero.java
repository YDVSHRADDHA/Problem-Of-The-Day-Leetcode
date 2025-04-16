public class Solution {
    public int minOperations(int[] nums, int x) {
        int target = Arrays.stream(nums).sum() - x;
        if (target < 0) return -1;

        int left = 0, curr = 0, maxLen = -1;

        for (int right = 0; right < nums.length; right++) {
            curr += nums[right];

            while (curr > target) {
                curr -= nums[left++];
            }

            if (curr == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}
