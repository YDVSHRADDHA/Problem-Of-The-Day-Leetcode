class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] cost = new int[m][n];
        for (int[] row : cost) Arrays.fill(row, Integer.MAX_VALUE);
        
        dq.offerFirst(new int[]{0, 0, 0}); // row, col, cost
        cost[0][0] = 0;
        
        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0], c = cur[1], curCost = cur[2];
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dirs[d][0];
                int nc = c + dirs[d][1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int newCost = curCost + (grid[r][c] == d + 1 ? 0 : 1);
                    if (newCost < cost[nr][nc]) {
                        cost[nr][nc] = newCost;
                        if (grid[r][c] == d + 1)
                            dq.offerFirst(new int[]{nr, nc, newCost});
                        else
                            dq.offerLast(new int[]{nr, nc, newCost});
                    }
                }
            }
        }
        
        return cost[m - 1][n - 1];
    }
}
