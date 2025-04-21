import java.util.Arrays;

class Solution {
    static final int MOD = 1000000007;

    public int sumOfPower(int[] nums) {
        int n = nums.length;
        long result = 0;

        // Sort the array to simplify calculations
        Arrays.sort(nums);

        // Iterate over the array and calculate the contribution of each element
        long minSum = 0;
        for (int i = 0; i < n; i++) {
            // For each element nums[i], calculate its contribution as the maximum
            long currentMax = (long) nums[i] * nums[i] % MOD;
            currentMax = currentMax * (minSum + nums[i]) % MOD;

            // Update the minSum for subsequent elements
            minSum = minSum * 2 % MOD + nums[i];

            // Add the contribution to the result
            result = (result + currentMax) % MOD;
        }

        return (int) result;
    }
}

public class Main {
    public static void main(String[] args) {
        // Sample input for testing
        int[] param_1 = {2, 1, 4};
        
        // Call the method sumOfPower from Solution class
        int ret = new Solution().sumOfPower(param_1);
        
        // Output the result
        System.out.println(ret);  // Expected output: 141
    }
}
