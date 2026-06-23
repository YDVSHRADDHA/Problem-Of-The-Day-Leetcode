class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1_000_000_007;
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        // Base case: length = 2
        for (int i = 0; i < m; i++) {
            up[i] = i;               // smaller elements before it
            down[i] = (m - 1 - i);  // larger elements before it
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m];
            long[] newDown = new long[m];

            // prefix sums of down
            long prefix = 0;
            for (int i = 0; i < m; i++) {
                newUp[i] = prefix;
                prefix = (prefix + down[i]) % MOD;
            }

            // suffix sums of up
            long suffix = 0;
            for (int i = m - 1; i >= 0; i--) {
                newDown[i] = suffix;
                suffix = (suffix + up[i]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}