class Solution {
    public int subsetXORSum(int[] nums) {
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int index, int xor) {
        if (index == nums.length) {
            return xor;
        }

        // Include current element
        int include = helper(nums, index + 1, xor ^ nums[index]);

        // Exclude current element
        int exclude = helper(nums, index + 1, xor);

        return include + exclude;
    }
}
