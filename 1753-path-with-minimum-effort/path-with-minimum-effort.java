class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int left = 0, right = 1_000_000, ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean[][] visited = new boolean[m][n];

            if (canReach(heights, 0, 0, visited, mid)) {
                ans = mid;
                right = mid - 1; // try to minimize more
            } else {
                left = mid + 1; // need to allow more effort
            }
        }

        return ans;
    }

    // DFS to check if (m-1, n-1) can be reached within `maxEffort`
    private boolean canReach(int[][] heights, int x, int y, boolean[][] visited, int maxEffort) {
        int m = heights.length, n = heights[0].length;

        if (x == m - 1 && y == n - 1) return true;

        visited[x][y] = true;

        for (int[] d : dirs) {
            int nx = x + d[0], ny = y + d[1];

            if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                int diff = Math.abs(heights[nx][ny] - heights[x][y]);
                if (diff <= maxEffort) {
                    if (canReach(heights, nx, ny, visited, maxEffort)) return true;
                }
            }
        }

        return false;
    }
}
