class Solution {
    int MOD = 1000000007;
    Integer[][][] memo;
    public int dieSimulator(int n, int[] rollMax) {
 
        memo = new Integer[n + 1][7][16]; // 7 for face (0 to 6), 16 for max rollMax[i]
        return dfs(n, -1, 0, rollMax);
    }

    private int dfs(int rollsLeft, int lastFace, int count, int[] rollMax) {
        if (rollsLeft == 0) return 1;

        if (lastFace != -1 && memo[rollsLeft][lastFace][count] != null)
            return memo[rollsLeft][lastFace][count];

        int total = 0;
        for (int face = 0; face < 6; face++) {
            if (face == lastFace) {
                if (count < rollMax[face]) {
                    total = (total + dfs(rollsLeft - 1, face, count + 1, rollMax)) % MOD;
                }
            } else {
                total = (total + dfs(rollsLeft - 1, face, 1, rollMax)) % MOD;
            }
        }

        if (lastFace != -1)
            memo[rollsLeft][lastFace][count] = total;

        return total;
    }
}
