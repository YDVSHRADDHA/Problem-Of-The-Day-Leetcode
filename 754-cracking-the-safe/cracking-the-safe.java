import java.util.*;

public class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        Set<String> visited = new HashSet<>();
        String start = "0".repeat(n - 1);
        dfs(start, visited, sb, n, k);
        sb.append(start); // append the starting prefix
        return sb.toString();
    }

    private void dfs(String node, Set<String> visited, StringBuilder sb, int n, int k) {
        for (int i = 0; i < k; i++) {
            String next = node + i;
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(next.substring(1), visited, sb, n, k);
                sb.append(i);
            }
        }
    }
}
