class Solution {
     
    static final int MOD = 1_000_000_007;
    long[] fact, invFact;

    public int countAnagrams(String s) {
        String[] words = s.split(" ");
        int maxLen = s.length();

        // Precompute factorials and inverse factorials
        fact = new long[maxLen + 1];
        invFact = new long[maxLen + 1];
        fact[0] = invFact[0] = 1;

        for (int i = 1; i <= maxLen; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
            invFact[i] = modInverse(fact[i]);
        }

        long result = 1;

        for (String word : words) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            long ways = fact[word.length()];
            for (int count : freq) {
                if (count > 0) {
                    ways = (ways * invFact[count]) % MOD;
                }
            }

            result = (result * ways) % MOD;
        }

        return (int) result;
    }

    private long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    private long pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
    }
}
