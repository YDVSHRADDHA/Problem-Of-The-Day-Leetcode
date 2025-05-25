public class Solution {
    private static final int MOD = 1_000_000_007;

    public int knightDialer(int n) {
        int[][] moves = {
            {4, 6},    // from 0
            {6, 8},    // from 1
            {7, 9},    // from 2
            {4, 8},    // from 3
            {0, 3, 9}, // from 4
            {},        // from 5 (not valid)
            {0, 1, 7}, // from 6
            {2, 6},    // from 7
            {1, 3},    // from 8
            {2, 4}     // from 9
        };

        // dp[i][j] = number of distinct ways to reach digit j at step i
        long[] prev = new long[10];
        Arrays.fill(prev, 1); // For n = 1, all digits have one way

        for (int step = 2; step <= n; step++) {
            long[] curr = new long[10];
            for (int digit = 0; digit <= 9; digit++) {
                for (int nei : moves[digit]) {
                    curr[digit] = (curr[digit] + prev[nei]) % MOD;
                }
            }
            prev = curr;
        }

        long total = 0;
        for (long count : prev) {
            total = (total + count) % MOD;
        }

        return (int) total;
    }
}
