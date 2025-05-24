import java.util.*;

class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        // Build adjacency list for the undirected tree
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        // Mark restricted nodes for quick lookup
        boolean[] isRestricted = new boolean[n];
        for (int r : restricted) {
            isRestricted[r] = true;
        }
        
        // BFS from node 0, skipping restricted nodes
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor] && !isRestricted[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        
        return count;
    }
}
