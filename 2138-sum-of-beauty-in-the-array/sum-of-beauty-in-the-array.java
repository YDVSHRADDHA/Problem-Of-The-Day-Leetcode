class Solution {
    public int sumOfBeauties(int[] nums) {
             int n = nums.length;
        int[] prefixMax = new int[n]; 
        int[] suffixMin = new int[n];

        // Compute prefix max
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        // Compute suffix min
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int totalBeauty = 0;

        // Compute beauty for each i in range [1, n-2]
        for (int i = 1; i < n - 1; i++) {
            if (prefixMax[i - 1] < nums[i] && nums[i] < suffixMin[i + 1]) {
                totalBeauty += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                totalBeauty += 1;
            }
        }

        return totalBeauty;
    
    }
}