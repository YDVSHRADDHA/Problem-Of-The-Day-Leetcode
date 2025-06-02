  class Solution {
    static final int MOD = 1_000_000_007;
    Integer[][] dp;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
 
        int n = locations.length;
        dp = new Integer[n][fuel + 1];
        return dfs(locations, start, finish, fuel);
    }

    private int dfs(int[] locations, int current, int finish, int fuelLeft) {
        if (fuelLeft < 0) return 0;  // No fuel, can't proceed

        if (dp[current][fuelLeft] != null) return dp[current][fuelLeft];

        int count = (current == finish) ? 1 : 0;  // Count route if we're at finish

        for (int next = 0; next < locations.length; next++) {
            if (next == current) continue;
            int cost = Math.abs(locations[current] - locations[next]);
            if (fuelLeft >= cost) {
                count = (count + dfs(locations, next, finish, fuelLeft - cost)) % MOD;
            }
        }

        return dp[current][fuelLeft] = count;
    }
}
