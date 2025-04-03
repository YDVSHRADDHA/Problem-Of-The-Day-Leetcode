class Solution {
    public int minimumSum(int[] nums) {
               int n = nums.length;
        if (n < 3) return -1;
        
        // Prefix min array: Smallest element before index j
        int[] prefixMin = new int[n];
        prefixMin[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], nums[i - 1]);
        }
        
        // Suffix min array: Smallest element after index j
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i + 1]);
        }

        int minSum = Integer.MAX_VALUE;

        // Check for valid triplet
        for (int j = 1; j < n - 1; j++) {
            if (prefixMin[j] < nums[j] && suffixMin[j] < nums[j]) {
                minSum = Math.min(minSum, prefixMin[j] + nums[j] + suffixMin[j]);
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    
    }
}