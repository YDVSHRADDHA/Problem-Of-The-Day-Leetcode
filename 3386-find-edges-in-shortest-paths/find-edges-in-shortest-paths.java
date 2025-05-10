class Solution {
    
    private static final int INF = (int) 1e9;
    public boolean[] findAnswer(int n, int[][] edges) {
        // Step 1: Run Dijkstra from source (0) to get shortest distances to all nodes
        int[] distFromSource = dijkstra(n, edges, 0);
        
        // Step 2: Run Dijkstra from target (n-1) to get shortest distances from target to all nodes
        int[] distFromTarget = dijkstra(n, edges, n - 1);
        
        // Step 3: Calculate the overall shortest distance from source to target
        int shortestPath = distFromSource[n - 1];
        
        // Step 4: Initialize the result array to keep track of edges that are part of the shortest paths
        boolean[] result = new boolean[edges.length];
        
        // Step 5: Check each edge if it's part of the shortest path
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];
            
            // Check if the edge is part of the shortest path in either direction
            if (distFromSource[u] + weight + distFromTarget[v] == shortestPath ||
                distFromSource[v] + weight + distFromTarget[u] == shortestPath) {
                result[i] = true;
            } else {
                result[i] = false;
            }
        }
        
        // Step 6: Return the result array
        return result;
    }
    
    // Dijkstra's algorithm to compute shortest path distances from the source node
    private int[] dijkstra(int n, int[][] edges, int start) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        // Build adjacency list from the edges
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        
        // Dijkstra's algorithm setup
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});
        
        // Run Dijkstra's algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int d = current[1];
            
            // If we already found a shorter path, skip
            if (d > dist[node]) continue;
            
            for (int[] neighbor : adj.get(node)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                
                // Relax the edge
                if (dist[node] + weight < dist[nextNode]) {
                    dist[nextNode] = dist[node] + weight;
                    pq.add(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        
        return dist;
    }
}
