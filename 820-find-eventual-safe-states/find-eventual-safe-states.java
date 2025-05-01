class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = unvisited, 1 = visiting, 2 = safe
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, color)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean dfs(int node, int[][] graph, int[] color) {
        if (color[node] != 0) {
            return color[node] == 2;
        }

        color[node] = 1; // mark as visiting

        for (int neighbor : graph[node]) {
            if (!dfs(neighbor, graph, color)) {
                return false; // cycle detected
            }
        }

        color[node] = 2; // mark as safe
        return true;
    }
}
