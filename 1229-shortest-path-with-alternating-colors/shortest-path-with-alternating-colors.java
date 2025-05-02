 
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Build graph with color coding: 0 = red, 1 = blue
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] red : redEdges) graph[red[0]].add(new int[]{red[1], 0});
        for (int[] blue : blueEdges) graph[blue[0]].add(new int[]{blue[1], 1});

        int[][] dist = new int[n][2]; // dist[i][0]: red last, dist[i][1]: blue last
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
        dist[0][0] = dist[0][1] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0}); // node 0 with last red
        queue.offer(new int[]{0, 1}); // node 0 with last blue

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], color = curr[1];
            for (int[] next : graph[node]) {
                int neighbor = next[0], edgeColor = next[1];
                if (edgeColor != color && dist[neighbor][edgeColor] == Integer.MAX_VALUE) {
                    dist[neighbor][edgeColor] = dist[node][color] + 1;
                    queue.offer(new int[]{neighbor, edgeColor});
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int min = Math.min(dist[i][0], dist[i][1]);
            res[i] = min == Integer.MAX_VALUE ? -1 : min;
        }

        return res;
    }
}
