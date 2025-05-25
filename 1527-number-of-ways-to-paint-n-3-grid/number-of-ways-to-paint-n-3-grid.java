public class Solution {
    public int numOfWays(int n) {
        long MOD = 1_000_000_007;
        long typeA = 6;  // abc
        long typeB = 6;  // aba

        for (int i = 2; i <= n; i++) {
            long newA = (typeA * 2 + typeB * 2) % MOD;
            long newB = (typeA * 2 + typeB * 3) % MOD;
            typeA = newA;
            typeB = newB;
        }

        return (int)((typeA + typeB) % MOD);
    }
}
