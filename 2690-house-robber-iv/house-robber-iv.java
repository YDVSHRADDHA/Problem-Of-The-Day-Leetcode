import java.util.Arrays;

class Solution {
    public int minCapability(int[] nums, int k) {
        // Binary search for the minimum capability
        int left = Arrays.stream(nums).min().getAsInt(); // Minimum value in nums
        int right = Arrays.stream(nums).max().getAsInt(); // Maximum value in nums
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canRob(nums, k, mid)) {
                right = mid; // Try a lower maximum capability
            } else {
                left = mid + 1; // Allow for a higher maximum capability
            }
        }
        
        return left; // Left will be the minimum capability found
    }
    
    // Helper function to check if it's possible to rob at least k houses with a max value of `maxValue`
    private boolean canRob(int[] nums, int k, int maxValue) {
        int count = 0;
        int i = 0;
        
        while (i < nums.length) {
            if (nums[i] <= maxValue) {
                count++; // Rob this house
                i++; // Skip the next house since adjacent houses can't be robbed
            }
            i++;
        }
        
        return count >= k; // Check if we've robbed at least `k` houses
    }
}