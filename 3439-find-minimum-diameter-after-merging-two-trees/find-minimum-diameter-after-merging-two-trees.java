import java.util.*;

class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = getDiameter(edges1);
        int d2 = getDiameter(edges2);

        // 1 + ceil(d1 / 2) + ceil(d2 / 2)
        int mergedDiameter = 1 + (d1 + 1) / 2 + (d2 + 1) / 2;

        return Math.max(d1, Math.max(d2, mergedDiameter));
    }

    // Get diameter of a tree using two BFS
    private int getDiameter(int[][] edges) {
        int n = edges.length + 1;

        // Build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // First BFS to find the farthest node from node 0
        int farthestNode = bfs(graph, 0)[0];

        // Second BFS from that farthest node gives the diameter
        int diameter = bfs(graph, farthestNode)[1];
        return diameter;
    }

    // BFS returns [farthestNode, distance]
    private int[] bfs(List<List<Integer>> graph, int start) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        visited[start] = true;

        int[] result = new int[]{start, 0};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int dist = curr[1];

            result = curr;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, dist + 1});
                }
            }
        }

        return result;
    }
}
