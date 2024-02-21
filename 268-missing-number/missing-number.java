class Solution {
    public int missingNumber(int[] nums) {
        int indeX = 0, numX = 0, n = nums.length;

        for (int i = 1; i <= n; i++) {
            indeX ^= i;
            numX ^= nums[i - 1];
        }
        return indeX ^ numX;
    }
}