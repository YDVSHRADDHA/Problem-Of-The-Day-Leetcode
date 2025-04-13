class Solution {
    static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long evenCount = (n + 1) / 2; // even indices (0-based)
        long oddCount = n / 2;        // odd indices

        long evenPow = power(5, evenCount);
        long oddPow = power(4, oddCount);

        return (int)((evenPow * oddPow) % MOD);
    }

    // Fast exponentiation (modular)
    private long power(long base, long exp) {
        long result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) { // if exp is odd
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
