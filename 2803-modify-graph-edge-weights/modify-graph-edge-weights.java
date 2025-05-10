class Solution {

    // Constant representing a large number for INF (used to simulate "infinite" distance)
    private static final int INF = (int) 2e9;

    public int[][] modifiedGraphEdges(
        int n,
        int[][] edges,
        int source,
        int destination,
        int target
    ) {
        // Step 1: Run Dijkstra to compute the current shortest distance
        long currentShortestDistance = runDijkstra(edges, n, source, destination);

        // If the current shortest path is less than target, we can never stretch it to target using only -1 edges
        if (currentShortestDistance < target) return new int[0][0];

        // Check if the current shortest path is already equal to the target
        boolean matchesTarget = (currentShortestDistance == target);

        // Step 2: Go through each edge and try modifying -1 weights to reach target distance
        for (int[] edge : edges) {
            // Skip edges with defined positive weight
            if (edge[2] > 0) continue;

            // If already matching target, assign a large weight to prevent affecting path
            // Else, assign 1 to test its potential use to reach target
            edge[2] = matchesTarget ? INF : 1;

            // If not already matching target, try to modify this edge
            if (!matchesTarget) {
                // Run Dijkstra again with this edge weight set to 1
                long newDistance = runDijkstra(edges, n, source, destination);

                // If new shortest path is within or equal to target, adjust this edge to make path exactly match target
                if (newDistance <= target) {
                    matchesTarget = true;

                    // Increase edge weight to make up the exact difference to reach target
                    edge[2] += target - newDistance;
                }
            }
        }

        // If we were able to get the path equal to target, return the updated edges
        // Else, return an empty array (not possible to reach target)
        return matchesTarget ? edges : new int[0][0];
    }

    // Helper method: Dijkstra’s algorithm to compute shortest path from source to destination
    private long runDijkstra(
        int[][] edges,
        int n,
        int source,
        int destination
    ) {
        // Create adjacency matrix to hold weights
        long[][] adjMatrix = new long[n][n];
        long[] minDistance = new long[n]; // Array to store shortest distances from source
        boolean[] visited = new boolean[n]; // Track visited nodes

        // Set all distances to INF
        Arrays.fill(minDistance, INF);
        for (long[] row : adjMatrix) {
            Arrays.fill(row, INF);
        }

        // Source to itself is 0 distance
        minDistance[source] = 0;

        // Fill in the known edge weights from the edges list (skip -1 edges)
        for (int[] edge : edges) {
            if (edge[2] != -1) {
                adjMatrix[edge[0]][edge[1]] = edge[2];
                adjMatrix[edge[1]][edge[0]] = edge[2];
            }
        }

        // Classic Dijkstra loop (using manual selection of nearest node)
        for (int i = 0; i < n; ++i) {
            // Pick the unvisited node with the smallest distance
            int nearestUnvisitedNode = -1;
            for (int j = 0; j < n; ++j) {
                if (!visited[j] && (nearestUnvisitedNode == -1 || minDistance[j] < minDistance[nearestUnvisitedNode])) {
                    nearestUnvisitedNode = j;
                }
            }

            // If all reachable nodes are visited, break
            if (nearestUnvisitedNode == -1) break;

            // Mark this node as visited
            visited[nearestUnvisitedNode] = true;

            // Update distances for its neighbors
            for (int v = 0; v < n; ++v) {
                minDistance[v] = Math.min(
                    minDistance[v],
                    minDistance[nearestUnvisitedNode] + adjMatrix[nearestUnvisitedNode][v]
                );
            }
        }

        // Return the shortest distance to the destination
        return minDistance[destination];
    }
}
