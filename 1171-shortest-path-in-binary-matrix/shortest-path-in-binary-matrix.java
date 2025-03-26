import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1; // No path possible

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, 
                              {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1}); // (row, col, path_length)
        grid[0][0] = 1; // Mark as visited

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], length = current[2];

            if (r == n - 1 && c == n - 1) return length; // Reached destination

            for (int[] dir : directions) {
                int newRow = r + dir[0], newCol = c + dir[1];

                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < n 
                    && grid[newRow][newCol] == 0) {
                    
                    queue.offer(new int[]{newRow, newCol, length + 1});
                    grid[newRow][newCol] = 1; // Mark as visited
                }
            }
        }

        return -1; // No path found
    }
}
