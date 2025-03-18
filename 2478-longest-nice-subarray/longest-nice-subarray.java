class Solution {
    public int longestNiceSubarray(int[] nums) {
        int left = 0, orMask = 0, maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            while ((orMask & nums[right]) != 0) { // Conflict detected
                orMask ^= nums[left]; // Remove left element
                left++;
            }
            orMask |= nums[right]; // Include new right element
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}

// Efficient Java Solution (O(N) Time Complexity