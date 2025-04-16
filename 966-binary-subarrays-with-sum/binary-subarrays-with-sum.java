public class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        prefixSumFreq.put(0, 1); // Base case: sum = 0 appears once

        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            count += prefixSumFreq.getOrDefault(sum - goal, 0);
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
