class Solution {
    // Main function to find the redundant edge
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize graph
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Try adding edges one by one
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            boolean[] visited = new boolean[n + 1];

            // Before adding edge, check if u and v are already connected
            if (dfs(u, v, -1, graph, visited)) {
                // If they are connected, adding this edge creates a cycle
                return edge;
            }

            // Else, safely add the edge
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return new int[0]; // Should never reach here for valid input
    }

    // DFS to check if there's a path between current and target node
    private boolean dfs(int current, int target, int parent, List<List<Integer>> graph, boolean[] visited) {
        if (current == target) return true;
        visited[current] = true;

        for (int neighbor : graph.get(current)) {
            if (neighbor == parent) continue; // Don't go back to parent
            if (!visited[neighbor]) {
                if (dfs(neighbor, target, current, graph, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
