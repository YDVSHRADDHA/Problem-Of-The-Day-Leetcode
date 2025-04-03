public class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;  // If there are less than 3 elements, no valid triplet exists

        long maxValue = 0;  // Store the maximum value of the triplet expression
        
        int[] maxRight = new int[n];  // Array to store the maximum value from the right side
        maxRight[n - 1] = nums[n - 1]; // Initialize the last element

        // Step 1: Fill maxRight array (Right to Left)
        for (int k = n - 2; k >= 0; k--) {
            maxRight[k] = Math.max(maxRight[k + 1], nums[k]);  // Store the max value seen so far from the right
        }
        
        int maxLeft = nums[0];  // Store the max value seen so far from the left

        // Step 2: Iterate over j (middle element) and compute the maximum triplet value
        for (int j = 1; j < n - 1; j++) {
            if (maxLeft > nums[j]) {  // Only consider valid cases where nums[i] > nums[j]
                maxValue = Math.max(maxValue, (long) (maxLeft - nums[j]) * maxRight[j + 1]);  
            }
            maxLeft = Math.max(maxLeft, nums[j]);  // Update maxLeft for future iterations
        }
        
        return maxValue;  // Return the maximum triplet value found
    }
}
