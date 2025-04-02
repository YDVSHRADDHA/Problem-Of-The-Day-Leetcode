class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0; // Edge case, not required per constraints

        // Step 1: Compute prefix max (max value before index j)
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        // Step 2: Compute suffix max (max value after index j)
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = nums[n - 1];
        for (int k = n - 2; k >= 0; k--) {
            suffixMax[k] = Math.max(suffixMax[k + 1], nums[k]);
        }

        // Step 3: Compute maximum triplet value
        long maxValue = 0;
        for (int j = 1; j < n - 1; j++) {
            long maxI = prefixMax[j - 1];  // Max nums[i] for i < j
            long maxK = suffixMax[j + 1];  // Max nums[k] for k > j

            long value = (maxI - nums[j]) * maxK;
            maxValue = Math.max(maxValue, value);
        }

        return maxValue;
    }
}
