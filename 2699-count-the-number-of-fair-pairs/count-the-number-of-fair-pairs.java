import java.util.Arrays;

class Solution {

    public long countFairPairs(int[] nums, int lower, int upper) {
        // Sort the array to use the two-pointer technique
        Arrays.sort(nums);
        // Count pairs with sum less than or equal to upper, and subtract the count of pairs with sum less than lower
        return countPairs(nums, upper + 1) - countPairs(nums, lower);
    }

    // Count pairs whose sum is less than `value`
    private long countPairs(int[] nums, int value) {
        int left = 0, right = nums.length - 1;
        long result = 0;
        
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < value) {
                // If sum is less than `value`, all pairs from left to right will work
                result += (right - left);
                left++;  // Move the left pointer to the right to try the next element
            } else {
                // If sum is not less than `value`, move the right pointer to the left
                right--;
            }
        }
        
        return result;
    }
}
