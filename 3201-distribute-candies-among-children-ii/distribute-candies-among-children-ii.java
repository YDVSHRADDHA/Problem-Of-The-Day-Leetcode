public class Solution {
    public long distributeCandies(int n, int limit) {
        return countSolutions(n, limit);
    }

    private long comb(long n, long k) {
        if (k < 0 || k > n) return 0;
        long res = 1;
        for (long i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    private long countSolutions(int n, int limit) {
        long total = comb(n + 2, 2);  // Unbounded solution count

        // Inclusion-Exclusion for variables exceeding limit
        for (int i = 0; i < 3; i++) {
            long over1 = n - (limit + 1);
            if (over1 >= 0) total -= comb(over1 + 2, 2);
        }

        for (int i = 0; i < 3; i++) {
            long over2 = n - 2 * (limit + 1);
            if (over2 >= 0) total += comb(over2 + 2, 2);
        }

        long over3 = n - 3 * (limit + 1);
        if (over3 >= 0) total -= comb(over3 + 2, 2);

        return total;
    }
}
