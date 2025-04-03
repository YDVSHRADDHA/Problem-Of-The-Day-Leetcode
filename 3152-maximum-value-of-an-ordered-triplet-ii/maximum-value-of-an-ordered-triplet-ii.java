public class Solution {
    public long maximumTripletValue(int[] nums) {

          long maxProduct = 0;  // Stores the maximum triplet value found so far
        int maxDiff = 0;      // Stores the maximum difference (nums[i] - nums[j]) found so far
        int maxNum = 0;       // Stores the maximum nums[i] encountered so far

        // Iterate through each number in the array
        for (int num : nums) {
            // Step 1: Calculate the possible triplet value using (maxDiff * num)
            maxProduct = Math.max(maxProduct, (long) maxDiff * num);

            // Step 2: Update maxNum to keep track of the largest nums[i] encountered
            maxNum = Math.max(maxNum, num);

            // Step 3: Update maxDiff to keep track of the maximum difference (nums[i] - nums[j])
            maxDiff = Math.max(maxDiff, maxNum - num);
        }

        // If maxProduct is negative, return 0 as required
        return maxProduct > 0 ? maxProduct : 0;




        // int n = nums.length;
        // if (n < 3) return 0;  // If there are less than 3 elements, no valid triplet exists

        // long maxValue = 0;  // Store the maximum value of the triplet expression
        
        // int[] maxRight = new int[n];  // Array to store the maximum value from the right side
        // maxRight[n - 1] = nums[n - 1]; // Initialize the last element

        // // Step 1: Fill maxRight array (Right to Left)
        // for (int k = n - 2; k >= 0; k--) {
        //     maxRight[k] = Math.max(maxRight[k + 1], nums[k]);  // Store the max value seen so far from the right
        // }
        
        // int maxLeft = nums[0];  // Store the max value seen so far from the left

        // // Step 2: Iterate over j (middle element) and compute the maximum triplet value
        // for (int j = 1; j < n - 1; j++) {
        //     if (maxLeft > nums[j]) {  // Only consider valid cases where nums[i] > nums[j]
        //         maxValue = Math.max(maxValue, (long) (maxLeft - nums[j]) * maxRight[j + 1]);  
        //     }
        //     maxLeft = Math.max(maxLeft, nums[j]);  // Update maxLeft for future iterations
        // }
        
        // return maxValue;  // Return the maximum triplet value found
    }
}
