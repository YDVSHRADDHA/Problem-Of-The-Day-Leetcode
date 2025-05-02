import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];
        
        // Build graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }

        // Color frequency table: dp[node][color]
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        // Start with nodes having 0 indegree
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int visited = 0;
        int maxColorCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            int colorIndex = colors.charAt(node) - 'a';
            dp[node][colorIndex]++;
            maxColorCount = Math.max(maxColorCount, dp[node][colorIndex]);

            for (int neighbor : graph.get(node)) {
                // Update color frequencies
                for (int c = 0; c < 26; c++) {
                    dp[neighbor][c] = Math.max(dp[neighbor][c], dp[node][c]);
                }
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return visited == n ? maxColorCount : -1;
    }
}
