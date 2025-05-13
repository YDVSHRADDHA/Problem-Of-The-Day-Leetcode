class Solution {
    static final int MOD = 1000000007;
    static final int BASE = 131;

    public int deleteString(String s) {
        int n = s.length();
        long[] hash = new long[n + 1];
        long[] pow = new long[n + 1];

        // Precompute hashes and powers
        pow[0] = 1;
        for (int i = 0; i < n; i++) {
            hash[i + 1] = (hash[i] * BASE + s.charAt(i)) % MOD;
            pow[i + 1] = (pow[i] * BASE) % MOD;
        }

        // Function to get hash of substring s[l..r)
        java.util.function.BiFunction<Integer, Integer, Long> getHash = (l, r) -> {
            return (hash[r] - hash[l] * pow[r - l] % MOD + MOD) % MOD;
        };

        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1; // at least delete the entire remaining string
            for (int j = 1; i + 2 * j <= n; j++) {
                if (getHash.apply(i, i + j).equals(getHash.apply(i + j, i + 2 * j))) {
                    dp[i] = Math.max(dp[i], 1 + dp[i + j]);
                }
            }
        }

        return dp[0];
    }
}
