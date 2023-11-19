class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] maxEndingHere = new int[n];
     maxEndingHere[0] = nums[0];

      int maxSoFar =nums[0];

      for(int i=1; i< nums.length;i++){
        
    //  calculating the maximum subarray sum ending at the current index.

      maxEndingHere[i] = Math.max(nums[i], maxEndingHere[i - 1] + nums[i]);

   // Update the global maximum subarray sum
            maxSoFar = Math.max(maxSoFar, maxEndingHere[i]);
        } 

      return maxSoFar;
    }
}