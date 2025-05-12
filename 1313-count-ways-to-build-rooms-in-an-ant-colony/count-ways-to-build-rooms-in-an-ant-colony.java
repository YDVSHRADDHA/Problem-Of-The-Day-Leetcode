class Solution {
       static final int MOD = 1_000_000_007;
    long[] fact, invFact;
    List<Integer>[] tree;
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;

        // Precompute factorials and inverse factorials
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; ++i)
            fact[i] = fact[i - 1] * i % MOD;
        invFact[n] = modInverse(fact[n]);
        for (int i = n - 1; i >= 0; --i)
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        // Build the tree
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) tree[prevRoom[i]].add(i);

        // DFS to compute result
        return (int) dfs(0)[1];
    }

    // returns int[]{subtreeSize, numWays}
    private long[] dfs(int node) {
        long totalSize = 0, res = 1;

        for (int child : tree[node]) {
            long[] cur = dfs(child);
            long sz = cur[0], ways = cur[1];
            res = res * ways % MOD;
            res = res * invFact[(int) sz] % MOD;
            totalSize += sz;
        }

        res = res * fact[(int) totalSize] % MOD;
        return new long[]{totalSize + 1, res};
    }

    // Modular Inverse
    private long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    private long pow(long x, long y) {
        long res = 1;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            y >>= 1;
        }
        return res;
    }
}
