import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Integer> memo = new HashMap<>();

    public int waysToReachStair(int k) {
        return dfs(1, 0, true, k);
    }

    private int dfs(int pos, int jump, boolean canGoDown, int k) {
        // Pruning: stop going too high
        if (pos > k + 10 || jump > 60) return 0;

        String key = pos + "," + jump + "," + canGoDown;
        if (memo.containsKey(key)) return memo.get(key);

        int count = (pos == k) ? 1 : 0;

        // Move Up
        count += dfs(pos + (1 << jump), jump + 1, true, k);

        // Move Down (if allowed)
        if (canGoDown && pos > 0) {
            count += dfs(pos - 1, jump, false, k);
        }

        memo.put(key, count);
        return count;
    }
}
