import java.util.*;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        // Sort the array to ensure the divisibility property holds in order.
        Arrays.sort(nums);
        int n = nums.length;
        
        // dp[i] stores the size of the largest divisible subset ending with nums[i]
        int[] dp = new int[n];
        // prev[i] stores the index of the previous element in the subset ending at nums[i]
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int max = 0, maxIndex = 0;
        
        // Build the dp and prev arrays.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If nums[i] can be divided by nums[j], consider extending the subset ending at j
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            // Keep track of the largest subset found so far.
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }
        
        // Reconstruct the largest divisible subset
        while (maxIndex != -1) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        
        Collections.reverse(result);
        return result;
    }
    
     
}
