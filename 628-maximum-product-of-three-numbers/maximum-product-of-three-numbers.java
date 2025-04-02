import java.util.Arrays;

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums); // Sort the array
        int n = nums.length;

        // Case 1: Product of the three largest numbers
        int case1 = nums[n - 1] * nums[n - 2] * nums[n - 3];

        // Case 2: Product of the two smallest numbers and the largest number
        int case2 = nums[0] * nums[1] * nums[n - 1];

        return Math.max(case1, case2);
    }
}
