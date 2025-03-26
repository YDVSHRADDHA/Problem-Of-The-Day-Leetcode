import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int findMaxFish(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int maxFish = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) { // Start BFS from each water cell
                    maxFish = Math.max(maxFish, bfs(grid, i, j));
                }
            }
        }
        return maxFish;
    }

    private int bfs(int[][] grid, int r, int c) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        int fish = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];

            if (grid[x][y] == 0) continue; // Skip if already visited

            fish += grid[x][y];
            grid[x][y] = 0; // Mark as visited

            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < grid.length && ny < grid[0].length && grid[nx][ny] > 0) {
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return fish;
    }
}
