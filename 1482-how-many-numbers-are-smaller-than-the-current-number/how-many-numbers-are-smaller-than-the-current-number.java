class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] freq = new int[101]; // Since nums[i] <= 100

        // Count frequency of each number
        for (int num : nums) {
            freq[num]++;
        }

        // Build prefix sum of frequencies
        for (int i = 1; i < 101; i++) {
            freq[i] += freq[i - 1];
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // If nums[i] is 0, no smaller numbers
            result[i] = nums[i] == 0 ? 0 : freq[nums[i] - 1];
        }
        return result;
    }
}
