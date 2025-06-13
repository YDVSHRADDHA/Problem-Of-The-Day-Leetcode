public class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Case 1: whole array is the only window
        if (k == n) {
            int max = -1;
            for (int num : nums) max = Math.max(max, num);
            return max;
        }

        // Build frequency counter
        int[] freq = new int[51];
        for (int num : nums) freq[num]++;

        // Case 2: each element is its own window
        if (k == 1) {
            int ans = -1;
            for (int num : nums) {
                if (freq[num] == 1) {
                    ans = Math.max(ans, num);
                }
            }
            return ans;
        }

        // Case 3: only ends can be in exactly one window
        int ans0 = freq[nums[0]] == 1 ? nums[0] : -1;
        int ans1 = freq[nums[n-1]] == 1 ? nums[n-1] : -1;
        return Math.max(ans0, ans1);
    }
}
