class Solution {
    private int time = 0;
    private List<List<Integer>> result = new ArrayList<>();
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        // Build graph
        for (List<Integer> conn : connections) {
            int u = conn.get(0), v = conn.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        int[] timeIn = new int[n];      // When we first visit
        int[] low = new int[n];         // Lowest time we can reach from here
        Arrays.fill(timeIn, -1);        // Unvisited

        dfs(0, -1, graph, timeIn, low);
        return result;
    }

    private void dfs(int node, int parent, List<List<Integer>> graph, int[] timeIn, int[] low) {
        timeIn[node] = low[node] = time++;
        
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) continue; // Don’t go back to parent

            if (timeIn[neighbor] == -1) {
                dfs(neighbor, node, graph, timeIn, low);
                low[node] = Math.min(low[node], low[neighbor]);

                // If lowest point neighbor can go is after us → no other way → it's critical
                if (low[neighbor] > timeIn[node]) {
                    result.add(Arrays.asList(node, neighbor));
                }
            } else {
                // Update low if neighbor already visited
                low[node] = Math.min(low[node], timeIn[neighbor]);
            }
        }
    }
}
