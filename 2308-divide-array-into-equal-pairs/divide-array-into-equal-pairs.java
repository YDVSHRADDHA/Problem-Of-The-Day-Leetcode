class Solution {
    public boolean divideArray(int[] nums) {
     int[] freq = new int[501];  // Since nums[i] is between 1 and 500

        for (int num : nums) {
            freq[num]++;  // Count occurrences
        }

        for (int count : freq) {
            if ((count & 1) == 1) { // If count is odd, return false
                return false;
            }
        }

        return true;    
    }
}