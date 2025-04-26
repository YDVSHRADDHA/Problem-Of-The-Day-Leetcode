class Solution {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        int start = 0;
        long currSum = 0;
        long count = 0;
        
        for (int end = 0; end < n; end++) {
            currSum += nums[end];
            
            while (currSum * (end - start + 1) >= k) {
                currSum -= nums[start];
                start++;
            }
            
            count += (end - start + 1);
        }
        
        return count;
    }
}
