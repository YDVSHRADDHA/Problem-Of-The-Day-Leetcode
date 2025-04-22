import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX = 20000;
    private static long[] fact = new long[MAX + 1];
    private static long[] invFact = new long[MAX + 1];

    public int[] waysToFillArray(int[][] queries) {
        List<Integer> result = new ArrayList<>();
        precomputeFactorials();

        for (int[] q : queries) {
            int n = q[0];
            int k = q[1];

            Map<Integer, Integer> primeCount = primeFactors(k);
            long ways = 1;
            for (int count : primeCount.values()) {
                ways = (ways * nCr(count + n - 1, count)) % MOD;
            }
            result.add((int) ways);
        }

        // Convert List<Integer> to int[]
        int[] resArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resArr[i] = result.get(i);
        }
        return resArr;
    }

    private Map<Integer, Integer> primeFactors(int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i * i <= k; i++) {
            while (k % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                k /= i;
            }
        }
        if (k > 1) map.put(k, map.getOrDefault(k, 0) + 1);
        return map;
    }

    private void precomputeFactorials() {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[MAX] = modInverse(fact[MAX]);
        for (int i = MAX - 1; i > 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    private long nCr(int n, int r) {
        if (r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
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
