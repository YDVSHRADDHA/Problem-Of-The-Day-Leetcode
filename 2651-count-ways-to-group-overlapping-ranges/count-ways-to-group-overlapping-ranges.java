import java.util.Arrays;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countWays(int[][] ranges) {
        // Step 1: Sort intervals by start value
        Arrays.sort(ranges, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Merge overlapping intervals and count connected components
        int components = 0;
        int prevEnd = -1;

        for (int[] range : ranges) {
            if (range[0] > prevEnd) { // New connected component
                components++;
            }
            prevEnd = Math.max(prevEnd, range[1]); // Update farthest end
        }

        // Step 3: Compute 2^components % MOD using Fast Exponentiation
        return fastPower(2, components, MOD);
    }

    // Binary Exponentiation for fast modular power calculation
    private int fastPower(int base, int exp, int mod) {
        long result = 1;
        long b = base;

        while (exp > 0) {
            if ((exp & 1) == 1) { // If exponent is odd, multiply base
                result = (result * b) % mod;
            }
            b = (b * b) % mod; // Square base
            exp >>= 1; // Right shift exponent (divide by 2)
        }
        return (int) result;
    }
}
