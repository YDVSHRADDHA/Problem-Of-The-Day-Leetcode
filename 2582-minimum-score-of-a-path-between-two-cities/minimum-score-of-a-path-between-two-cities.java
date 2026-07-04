class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0], v = road[1], w = road[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        boolean[] vis = new boolean[n + 1];
        return dfs(1, graph, vis);
    }

    private int dfs(int node, List<int[]>[] graph, boolean[] vis) {
        vis[node] = true;
        int ans = Integer.MAX_VALUE;

        for (int[] edge : graph[node]) {
            int nei = edge[0];
            int wt = edge[1];

            ans = Math.min(ans, wt);

            if (!vis[nei]) {
                ans = Math.min(ans, dfs(nei, graph, vis));
            }
        }

        return ans;
    }
}