import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // Initialize queues with border cells
        for (int i = 0; i < m; i++) {
            pacificQueue.add(new int[]{i, 0}); // Left column (Pacific)
            atlanticQueue.add(new int[]{i, n - 1}); // Right column (Atlantic)
            pacific[i][0] = true;
            atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            pacificQueue.add(new int[]{0, j}); // Top row (Pacific)
            atlanticQueue.add(new int[]{m - 1, j}); // Bottom row (Atlantic)
            pacific[0][j] = true;
            atlantic[m - 1][j] = true;
        }

        // BFS for both oceans
        bfs(heights, pacificQueue, pacific);
        bfs(heights, atlanticQueue, atlantic);

        // Collect result where both oceans are reachable
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] ocean) {
        int m = heights.length, n = heights[0].length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] d : directions) {
                int newR = r + d[0], newC = c + d[1];
                if (newR >= 0 && newC >= 0 && newR < m && newC < n 
                    && !ocean[newR][newC] // Not visited
                    && heights[newR][newC] >= heights[r][c]) { // Water can flow
                    ocean[newR][newC] = true;
                    queue.add(new int[]{newR, newC});
                }
            }
        }
    }
}
