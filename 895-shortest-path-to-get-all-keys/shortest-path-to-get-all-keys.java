
class Solution {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][64];
        int allKeys = 0;

        // Locate start and count keys
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    queue.offer(new int[]{i, j, 0, 0}); // x, y, keys, steps
                    visited[i][j][0] = true;
                } else if (c >= 'a' && c <= 'f') {
                    allKeys |= (1 << (c - 'a')); // set that key bit
                }
            }
        }

        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], keys = cur[2], steps = cur[3];

            if (keys == allKeys) return steps; // got all keys!

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                char cell = grid[nx].charAt(ny);
                int newKeys = keys;

                if (cell == '#') continue;
                if (cell >= 'A' && cell <= 'F' && ((keys >> (cell - 'A')) & 1) == 0) continue; // lock but no key
                if (cell >= 'a' && cell <= 'f') newKeys |= (1 << (cell - 'a')); // collect key

                if (!visited[nx][ny][newKeys]) {
                    visited[nx][ny][newKeys] = true;
                    queue.offer(new int[]{nx, ny, newKeys, steps + 1});
                }
            }
        }

        return -1;
    }
}
