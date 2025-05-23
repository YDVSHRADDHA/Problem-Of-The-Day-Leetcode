class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
 
        int n = coins.length;

        // Build the adjacency list
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        // Step 1: Remove all leaves without coins
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                graph.get(neighbor).remove(node);
                degree[neighbor]--;
                if (degree[neighbor] == 1 && coins[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
            degree[node] = 0;
        }

        // Step 2: Remove leaves two times
        for (int round = 0; round < 2; round++) {
            Queue<Integer> leaves = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (degree[i] == 1) {
                    leaves.offer(i);
                }
            }

            while (!leaves.isEmpty()) {
                int node = leaves.poll();
                for (int neighbor : graph.get(node)) {
                    graph.get(neighbor).remove(node);
                    degree[neighbor]--;
                }
                degree[node] = 0;
            }
        }

        // Count the remaining edges
        int remainingEdges = 0;
        for (int i = 0; i < n; i++) {
            remainingEdges += degree[i];
        }

        // Each edge is counted twice (u->v and v->u)
        return remainingEdges;
    }
}
