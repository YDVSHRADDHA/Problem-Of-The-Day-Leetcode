class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];

        // Step 1: Multi-source BFS from all thieves
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);

            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                    vis[i][j] = true;
                }
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !vis[nx][ny]) {
                    vis[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        // Step 2: Maximum bottleneck path (Max Heap)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]
        );

        boolean[][] used = new boolean[n][n];
        pq.offer(new int[]{dist[0][0], 0, 0});
        used[0][0] = true;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int safe = cur[0];
            int x = cur[1];
            int y = cur[2];

            // Reached destination
            if (x == n - 1 && y == n - 1) {
                return safe;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !used[nx][ny]) {
                    used[nx][ny] = true;

                    // Bottleneck update
                    int newSafe = Math.min(safe, dist[nx][ny]);

                    pq.offer(new int[]{newSafe, nx, ny});
                }
            }
        }

        return 0;
    }
}