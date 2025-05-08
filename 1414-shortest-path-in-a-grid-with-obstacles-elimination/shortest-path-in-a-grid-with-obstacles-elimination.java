class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return 0;  // already at the destination

        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][k + 1];
        q.offer(new int[]{0, 0, 0, 0});  // x, y, steps, obstacles eliminated
        visited[0][0][0] = true;

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1], steps = curr[2], eliminated = curr[3];

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int newEliminated = eliminated + grid[nx][ny];
                    if (newEliminated <= k && !visited[nx][ny][newEliminated]) {
                        if (nx == m - 1 && ny == n - 1) return steps + 1;
                        visited[nx][ny][newEliminated] = true;
                        q.offer(new int[]{nx, ny, steps + 1, newEliminated});
                    }
                }
            }
        }

        return -1;
    }
}
