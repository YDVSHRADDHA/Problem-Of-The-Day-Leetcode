import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0, minutes = 0;
        
        // Step 1: Find all rotten oranges and count fresh ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // Add rotten oranges to queue
                } else if (grid[i][j] == 1) {
                    freshCount++; // Count fresh oranges
                }
            }
        }

        // If there are no fresh oranges, return 0 immediately
        if (freshCount == 0) return 0;

        // Step 2: Perform BFS to spread rot
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 4 possible directions
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rotted = false; // Track if any orange rotted in this minute

            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];

                for (int[] dir : directions) {
                    int newX = x + dir[0], newY = y + dir[1];

                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2; // Make fresh orange rotten
                        queue.offer(new int[]{newX, newY}); // Add newly rotten orange to queue
                        freshCount--; // Decrease fresh count
                        rotted = true;
                    }
                }
            }

            if (rotted) minutes++; // Increase time only if any orange rotted
        }

        return freshCount == 0 ? minutes : -1; // If all fresh are rotted, return minutes else -1
    }
}