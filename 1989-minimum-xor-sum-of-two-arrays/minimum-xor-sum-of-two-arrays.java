import java.util.Arrays;

public class Solution {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // dp[mask] will store the minimum XOR sum for the current state (mask)
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return helper(nums1, nums2, 0, 0, dp);
    }
    
    private int helper(int[] nums1, int[] nums2, int i, int mask, int[] dp) {
        int n = nums1.length;
        // Base case: if all elements in nums1 are paired, return 0
        if (i == n) return 0;
        
        // If we've already computed this state, return the result
        if (dp[mask] != -1) return dp[mask];
        
        int min = Integer.MAX_VALUE;
        // Try to pair nums1[i] with any unused element in nums2
        for (int j = 0; j < n; j++) {
            if ((mask & (1 << j)) == 0) { // nums2[j] is not used yet
                int current = (nums1[i] ^ nums2[j]) + helper(nums1, nums2, i + 1, mask | (1 << j), dp);
                min = Math.min(min, current);
            }
        }
        
        dp[mask] = min;
        return min;
    }
    
    // Example usage:
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1a = {1, 2};
        int[] nums2a = {2, 3};
        System.out.println("Minimum XOR Sum for Example 1: " + sol.minimumXORSum(nums1a, nums2a)); // Output: 2

        int[] nums1b = {1, 0, 3};
        int[] nums2b = {5, 3, 4};
        System.out.println("Minimum XOR Sum for Example 2: " + sol.minimumXORSum(nums1b, nums2b)); // Output: 8
    }
}
