class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
  
    int[][] dist = new int[n][n];
    int INF = 10001; // Since max threshold = 10^4

    // Initialize
    for (int i = 0; i < n; i++)
        Arrays.fill(dist[i], INF);
    
    for (int i = 0; i < n; i++)
        dist[i][i] = 0;

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], w = edge[2];
        dist[u][v] = w;
        dist[v][u] = w;
    }

    // Floyd-Warshall
    for (int k = 0; k < n; k++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (dist[i][k] + dist[k][j] < dist[i][j])
                    dist[i][j] = dist[i][k] + dist[k][j];

    // Find the city with minimum reachable count
    int minCount = n, result = -1;
    for (int i = 0; i < n; i++) {
        int count = 0;
        for (int j = 0; j < n; j++) {
            if (i != j && dist[i][j] <= distanceThreshold)
                count++;
        }
        if (count <= minCount) {
            minCount = count;
            result = i; // prefer larger index
        }
    }
    return result;
}
}
