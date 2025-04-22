import java.util.*;

public class Solution {
    private static final int MOD = 1_000_000_007;
    private static long[] fact, invFact;

    public static int idealArrays(int n, int maxValue) {
        int max = n + 100; // Enough for combination calculation
        initFactorials(max);

        // Sieve for prime factorization
        int[] spf = new int[maxValue + 1];
        for (int i = 2; i <= maxValue; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= maxValue; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }

        long result = 0;
        for (int val = 1; val <= maxValue; val++) {
            Map<Integer, Integer> primeCount = new HashMap<>();
            int x = val;
            while (x > 1) {
                int p = spf[x];
                primeCount.put(p, primeCount.getOrDefault(p, 0) + 1);
                x /= p;
            }

            long ways = 1;
            for (int count : primeCount.values()) {
                ways = (ways * nCr(n + count - 1, count)) % MOD;
            }
            result = (result + ways) % MOD;
        }

        return (int) result;
    }

    private static void initFactorials(int max) {
        fact = new long[max + 1];
        invFact = new long[max + 1];
        fact[0] = invFact[0] = 1;

        for (int i = 1; i <= max; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        invFact[max] = modInverse(fact[max]);
        for (int i = max - 1; i > 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    private static long nCr(int n, int r) {
        if (r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    private static long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    private static long pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
    }
}
