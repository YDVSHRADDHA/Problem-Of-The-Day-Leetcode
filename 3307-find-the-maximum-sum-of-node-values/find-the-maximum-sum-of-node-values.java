class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;

        // dp[i][0] -> max sum from index i to n-1 with an even number of XOR operations
        // dp[i][1] -> max sum from index i to n-1 with an odd number of XOR operations
        long[][] dp = new long[n + 1][2];

        // Base case:
        // When we've processed all elements (index == n), an odd number of XORs gives 0 sum
        dp[n][1] = 0;
        // An even number of XORs at the end is invalid (we want odd count in total), so set to -infinity
        dp[n][0] = Integer.MIN_VALUE;

        // Iterate backwards through the nums array
        for (int index = n - 1; index >= 0; index--) {
            for (int isEven = 0; isEven <= 1; isEven++) {
                // Option 1: perform XOR on this node (changes parity)
                // Take value as nums[index] ^ k and add to the opposite parity result
                long performOperation = dp[index + 1][isEven ^ 1] + (nums[index] ^ k);

                // Option 2: do not perform XOR (keeps parity same)
                // Take value as-is and add to same parity result
                long dontPerformOperation = dp[index + 1][isEven] + nums[index];

                // Take the max of the two options
                dp[index][isEven] = Math.max(performOperation, dontPerformOperation);
            }
        }

        // Final answer: maximum sum with an odd number of XOR operations (which flips an even count of nodes)
        return dp[0][1];
    }
}
