import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left

        // Step 1: Initialize BFS queue with all zeros and mark ones as unvisited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j}); // Add 0s to queue
                } else {
                    mat[i][j] = -1; // Mark 1s as unvisited
                }
            }
        }

        // Step 2: Perform BFS traversal
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // If the cell is within bounds and unvisited
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && mat[newRow][newCol] == -1) {
                    mat[newRow][newCol] = mat[row][col] + 1; // Update distance
                    queue.offer(new int[]{newRow, newCol}); // Add to queue
                }
            }
        }

        return mat;
    }
}
