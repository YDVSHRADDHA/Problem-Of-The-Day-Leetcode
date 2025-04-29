import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        
        // Min-heap: stores [elevation, row, col]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Visited matrix to avoid re-visiting
        boolean[][] visited = new boolean[n][n];
        
        // Start from (0, 0)
        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        
        // Directions for 4-connected grid
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        
        int maxTime = 0;
        
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int elevation = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            // Update max time needed so far
            maxTime = Math.max(maxTime, elevation);
            
            // If reached end
            if (r == n - 1 && c == n - 1) {
                return maxTime;
            }
            
            // Check all 4 directions
            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                // Check bounds and if not visited
                if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visited[newR][newC]) {
                    visited[newR][newC] = true;
                    minHeap.offer(new int[]{grid[newR][newC], newR, newC});
                }
            }
        }
        
        return -1; // Shouldn't reach here
    }
}
